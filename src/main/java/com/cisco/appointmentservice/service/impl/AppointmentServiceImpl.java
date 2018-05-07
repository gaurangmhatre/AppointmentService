package com.cisco.appointmentservice.service.impl;

import com.cisco.appointmentservice.dao.AppointmentDao;
import com.cisco.appointmentservice.dao.UserDao;
import com.cisco.appointmentservice.dao.beans.User;
import com.cisco.appointmentservice.mapstruct.AppointmentMapper;
import com.cisco.appointmentservice.util.DateUtil;
import com.cisco.appointmentservice.util.ServiceUtil;
import io.swagger.model.Appointment;
import com.cisco.appointmentservice.exception.BusinessException;
import com.cisco.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.cisco.appointmentservice.exception.BusinessException.*;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AppointmentDao appointmentDao;

    @Override
    public Appointment createAppointment(Appointment appointment, ZoneId zoneId) throws BusinessException {
        ServiceUtil.validateAppointment(appointment);
        User host = userDao.findByEmail(appointment.getHost());
        if(host == null) {
            throw HOST_NOT_FOUND;
        }

        LocalDateTime startUtc = DateUtil.getLocalDateTimeAtUTC(appointment.getFrom(), zoneId);
        LocalDateTime endUtc = DateUtil.getLocalDateTimeAtUTC(appointment.getTo(), zoneId);
        LocalDateTime currentUtc = DateUtil.getLocalDateTimeAtUTC(LocalDateTime.now(), ZoneId.systemDefault());

        if(currentUtc.isAfter(startUtc)) {
            throw CANT_CREATE_APPOINTMENT_TO_THE_PAST;
        }

        List<User> participants = new ArrayList<>();
        for (String participantEmail: appointment.getParticipants()) {
            User participant = userDao.findByEmail(participantEmail);
            if(participant == null) {
                throw PARTICIPANT_NOT_FOUND;
            }
            if(!appointment.isAllowOverlap()) {
                List<com.cisco.appointmentservice.dao.beans.Appointment> overlaps;
                if (appointment.getId() != null) {
                    overlaps = appointmentDao.findOverlapWithException(DateUtil.getTimeStamp(startUtc), DateUtil.getTimeStamp(endUtc), participant.getId(), appointment.getId());
                } else {
                    overlaps = appointmentDao.findOverlap(DateUtil.getTimeStamp(startUtc), DateUtil.getTimeStamp(endUtc), participant.getId());
                }
                if (overlaps.size() > 0) {
                    throw OVERLAPPING_APPOINTMENTS;
                }
            }
            participants.add(participant);
        }
        com.cisco.appointmentservice.dao.beans.Appointment appointmentBean = new com.cisco.appointmentservice.dao.beans.Appointment();
        appointmentBean.setStart(DateUtil.getTimeStamp(startUtc));
        appointmentBean.setEnd(DateUtil.getTimeStamp(endUtc));
        appointmentBean.setHost(host);
        appointmentBean.setParticipants(participants);
        if(appointment.getId() != null) {
            appointmentBean.setId(appointment.getId());
        }
        appointmentBean = appointmentDao.save(appointmentBean);
        appointment.setId(appointmentBean.getId());

        return appointment;
    }

    @Override
    public Appointment getAppointment(Long id, ZoneId zoneId) throws BusinessException {
        Optional<com.cisco.appointmentservice.dao.beans.Appointment> appointmentResult = appointmentDao.findById(id);
        if(appointmentResult.isPresent()) {
            com.cisco.appointmentservice.dao.beans.Appointment appointmentBean = appointmentResult.get();
            return AppointmentMapper.INSTANCE.getAppointment(appointmentBean, zoneId);
        } else {
            throw NO_SUCH_APPOINTMENT_EXISTS;
        }
    }

    @Override
    public Appointment updateAppointment(Appointment appointment, ZoneId zoneId) throws BusinessException {
        ServiceUtil.validateAppointmentForUpdate(appointment);
        Optional<com.cisco.appointmentservice.dao.beans.Appointment> appointmentResult = appointmentDao.findById(appointment.getId());
        if(appointmentResult.isPresent()) {
            com.cisco.appointmentservice.dao.beans.Appointment appointmentBean = appointmentResult.get();
            if(appointmentBean.getHost().getEmail().equals(appointment.getHost())) {
                if(appointment.getFrom().isAfter(DateUtil.getZonedDateTime(LocalDateTime.now(), zoneId).toLocalDateTime())) {
                    LocalDateTime currentUtc = DateUtil.getLocalDateTimeAtUTC(LocalDateTime.now(), ZoneId.systemDefault());
                    LocalDateTime appointmentDate = appointmentBean.getStart().toLocalDateTime();
                    if(appointmentDate.isBefore(currentUtc)) {
                        throw CANT_UPDATE_APPOINTMENT_IN_PAST;
                    }
                    return createAppointment(appointment, zoneId);
                } else {
                    throw CANT_UPDATE_APPOINTMENT_TO_THE_PAST;
                }
            } else {
                throw NOT_ALLOWED;
            }
        } else {
            throw NO_SUCH_APPOINTMENT_EXISTS;
        }
    }

    @Override
    public void cancelAppointment(Long id, String host) throws BusinessException {
        ServiceUtil.validateEmail(host);
        Optional<com.cisco.appointmentservice.dao.beans.Appointment> appointmentResult = appointmentDao.findById(id);
        if(appointmentResult.isPresent()) {
            com.cisco.appointmentservice.dao.beans.Appointment appointmentBean = appointmentResult.get();
            LocalDateTime appointmentDate = appointmentBean.getStart().toLocalDateTime();
            LocalDateTime currentUtc = DateUtil.getLocalDateTimeAtUTC(LocalDateTime.now(), ZoneId.systemDefault());
            if(appointmentDate.isBefore(currentUtc)) {
                throw CANT_CANCEL_APPOINTMENT_IN_PAST;
            }
            if(appointmentBean.getHost().getEmail().equals(host)) {
                appointmentDao.delete(appointmentBean);
            } else {
                throw NOT_ALLOWED;
            }
        } else {
            throw NO_SUCH_APPOINTMENT_EXISTS;
        }
    }
}
