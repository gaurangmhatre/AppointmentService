package com.cisco.appointmentservice.mapstruct;

import io.swagger.model.Appointment;

import java.time.ZoneId;

public abstract class AppointmentMapperDecorator implements AppointmentMapper {

    private final AppointmentMapper delegate;

    public AppointmentMapperDecorator(AppointmentMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public Appointment getAppointment(com.cisco.appointmentservice.dao.beans.Appointment appointmentBean, ZoneId zoneId) {
        Appointment appointment = delegate.getAppointment(appointmentBean, zoneId);
        appointment.setAllowOverlap(null);
        return appointment;
    }
}
