package com.cisco.appointmentservice.util;

import com.cisco.appointmentservice.dao.beans.User;
import com.cisco.appointmentservice.exception.BusinessException;
import com.cisco.appointmentservice.dao.beans.NotificationPreference;
import io.swagger.model.Appointment;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cisco.appointmentservice.exception.BusinessException.*;

public class ServiceUtil {

    public static Map<String, String> buildErrorResponse(String cause) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "FAILURE");
        response.put("cause", cause);
        return response;
    }

    public static void validateEmail(String email) throws BusinessException {
        if(!StringUtil.validateEmail(email)) {
            throw INVALID_EMAIL;
        }
    }

    public static void validateUserPref(String userPref) throws BusinessException {
        try {
            if(userPref != null)
               NotificationPreference.valueOf(userPref);
        } catch (Exception e) {
            throw INVALID_NOTIFICATION_PREF;
        }
    }


    public static void validateAppointment(Appointment appointment) throws BusinessException {
        if(appointment.getFrom() == null || appointment.getTo() == null || appointment.getHost() == null || appointment.getParticipants() == null) {
            throw INVALID_APPOINTMENT_REQUEST;
        } else {
            validateEmail(appointment.getHost());
            if(appointment.getFrom().isAfter(appointment.getTo()) || appointment.getFrom().isEqual(appointment.getTo())) {
                throw INVALID_APPOINTMENT_DATES;
            }
            if (appointment.getParticipants().size() < 2) {
                throw MIN_PARTICIPANTS_REQUIRED;
            }
            for (String participant: appointment.getParticipants()) {
                validateEmail(participant);
            }
        }
    }

    public static void validateAppointmentForUpdate(Appointment appointment) throws BusinessException {
        if (appointment.getId() == null) {
            throw APPOINTMENT_ID_NOT_PROVIDED;
        }
        validateAppointment(appointment);
    }

    public static ZoneId getZoneId(String xZone) throws BusinessException {
        try {
            return ZoneId.of(xZone);
        } catch (DateTimeException dte) {
            throw INVALID_ZONE_VALUE;
        }
    }

    public static List<String> getParticipants(List<User> participants) {
        if(participants == null) return null;
        List<String> emails = new ArrayList<>();
        participants.forEach(participant -> emails.add(participant.getEmail()));
        return emails;
    }
}
