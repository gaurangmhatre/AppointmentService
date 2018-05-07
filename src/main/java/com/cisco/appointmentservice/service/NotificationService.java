package com.cisco.appointmentservice.service;

import com.cisco.appointmentservice.dao.beans.Appointment;
import com.cisco.appointmentservice.dao.beans.User;

public interface NotificationService {

    void notifyUser(User user, Appointment appointment);

}
