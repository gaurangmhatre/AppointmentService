package com.cisco.appointmentservice.service;

import com.cisco.appointmentservice.exception.BusinessException;
import io.swagger.model.Appointment;

import java.time.ZoneId;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment, ZoneId zoneId) throws BusinessException;
    Appointment getAppointment(Long id, ZoneId zoneId) throws BusinessException;
    Appointment updateAppointment(Appointment appointment, ZoneId zoneId) throws BusinessException;
    void cancelAppointment(Long id, String host) throws BusinessException;
    //Appointment updateAppointmentStatus(Appointment appointment, User requester) throws BusinessException;

}
