package com.cisco.appointmentservice;

import io.swagger.model.Appointment;
import io.swagger.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static User getUser(String email, String name, String pref, String phone) {
        User user = new User();
        user.setEmail(email);
        user.setNotification(pref);
        user.setPhone(phone);
        user.setName(name);
        return user;
    }

    public static Appointment getAppointment(LocalDateTime from, LocalDateTime to, User host, List<User> participants, boolean allowOverlap) {
        Appointment appointment = new Appointment();
        appointment.setFrom(from);
        appointment.setTo(to);
        if (host != null)
            appointment.setHost(host.getEmail());
        if(participants != null) {
            List<String> participantEmail = new ArrayList<>();
            participants.forEach(participant -> participantEmail.add(participant.getEmail()));
            appointment.setParticipants(participantEmail);
        }
        appointment.setAllowOverlap(allowOverlap);
        return appointment;
    }

}
