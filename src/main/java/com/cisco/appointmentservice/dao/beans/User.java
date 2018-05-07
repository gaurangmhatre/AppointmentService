package com.cisco.appointmentservice.dao.beans;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String phone;

    @Column(nullable = false)
    private String name;

    @Column(name="pref")
    private String userPref;

    @OneToMany(mappedBy="host", fetch=FetchType.LAZY)
    private List<Appointment> ownedAppointments;

    @ManyToMany(mappedBy ="participants", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public User() {}

    public User(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPref() {
        return userPref;
    }

    public void setUserPref(String userPref) {
        this.userPref = userPref;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Appointment> getOwnedAppointments() {
        return ownedAppointments;
    }

    public void setOwnedAppointments(List<Appointment> ownedAppointments) {
        this.ownedAppointments = ownedAppointments;
    }

    @Override
    public String toString() {
        return getEmail();
    }
}
