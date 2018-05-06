package com.cisco.appointmentservice.dao;

import com.cisco.appointmentservice.dao.beans.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentDao extends CrudRepository<Appointment, Long>{
}
