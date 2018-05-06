package com.cisco.appointmentservice.dao.beans;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Appointment {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="USER_ID", referencedColumnName="ID")
    private User host;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="user_appointment",
            joinColumns= {@JoinColumn(name="appointmentId", referencedColumnName="Id")},
            inverseJoinColumns= {@JoinColumn(name="userId", referencedColumnName="Id")})
    private List<User> participants;

    @Column
    private Timestamp start;

    @Column
    private Timestamp end;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
