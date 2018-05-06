package com.cisco.appointmentservice.dao.beans;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserAppointmentId implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String appointmentId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAppointmentId)) return false;

        UserAppointmentId that = (UserAppointmentId) o;

        if (!userId.equals(that.userId)) return false;
        return appointmentId.equals(that.appointmentId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + appointmentId.hashCode();
        return result;
    }
}
