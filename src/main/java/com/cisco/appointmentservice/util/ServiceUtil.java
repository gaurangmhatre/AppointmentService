package com.cisco.appointmentservice.util;

import com.cisco.appointmentservice.exception.BusinessException;
import com.cisco.appointmentservice.dao.beans.NotificationPreference;

import java.util.HashMap;
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
            if(userPref != null) {
                NotificationPreference pref = NotificationPreference.valueOf(userPref);
            }
        } catch (Exception e) {
            throw INVALID_NOTIFICATION_PREF;
        }
    }


}
