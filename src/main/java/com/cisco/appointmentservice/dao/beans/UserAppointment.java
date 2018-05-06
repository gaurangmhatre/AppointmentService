package com.cisco.appointmentservice.dao.beans;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "user_appointment")
@IdClass(UserAppointmentId.class)
public class UserAppointment {

    @Id
    @Column
    private String userId;

    @Id
    @Column
    private String appointmentId;

    @Id
    @Column
    @ColumnDefault("\'REQUESTED\'")
    private String status;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*@EmbeddedId
    private UserAppointmentId userAppointmentId;

    public UserAppointmentId getUserAppointmentId() {
        return userAppointmentId;
    }

    public void setUserAppointmentId(UserAppointmentId userAppointmentId) {
        this.userAppointmentId = userAppointmentId;
    }*/
}
