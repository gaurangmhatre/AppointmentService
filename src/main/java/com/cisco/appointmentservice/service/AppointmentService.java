package com.cisco.appointmentservice.service;

import com.cisco.appointmentservice.exception.BusinessException;
import com.cisco.appointmentservice.dao.beans.Appointment;
import com.cisco.appointmentservice.dao.beans.User;

import java.util.Date;
import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment, User host, List<User> participant, boolean allowOverlap) throws BusinessException;
    void cancelAppointment(Appointment appointment, User host) throws BusinessException;
    Appointment udpateAppointmentStatus(Appointment appointment, User requester) throws BusinessException;
    Appointment udpateAppointmentSchedule(Appointment appointment, User requester, Date from, Date to);

}
