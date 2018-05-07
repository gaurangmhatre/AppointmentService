package com.cisco.appointmentservice.dao.beans;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "user_appointment")
@IdClass(UserAppointmentId.class)
public class UserAppointment {

    @Id
    @Column
    private Long userId;

    @Id
    @Column
    private Long appointmentId;

    @Id
    @Column
    @ColumnDefault("\'REQUESTED\'")
    private String status;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
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
