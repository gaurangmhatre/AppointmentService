package com.cisco.appointmentservice.service.impl;

import com.cisco.appointmentservice.dao.beans.Appointment;
import com.cisco.appointmentservice.dao.beans.User;
import com.cisco.appointmentservice.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class SMSNotificationService implements NotificationService {

    @Override
    public void notifyUser(User user, Appointment appointment) {
        System.out.println("SMS: " + user.getName() + " for " + appointment.getId());
    }
}
