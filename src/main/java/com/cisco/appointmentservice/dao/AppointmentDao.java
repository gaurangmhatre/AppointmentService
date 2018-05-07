package com.cisco.appointmentservice.dao;

import com.cisco.appointmentservice.dao.beans.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AppointmentDao extends CrudRepository<Appointment, Long> {

    /*@Query("SELECT a FROM Appointment a WHERE ((:start BETWEEN a.start AND a.end) OR (:end BETWEEN a.start AND a.end)) AND a.id in (SELECT ua.appointmentId FROM UserAppointment ua WHERE ua.userId = :userId)")
    List<Appointment> findOverlap(@Param("start") Timestamp start, @Param("end") Timestamp end, @Param("userId") Long userId);
    */
    @Query("SELECT a FROM Appointment a LEFT JOIN UserAppointment ua ON a.id = ua.appointmentId WHERE ua.userId = :userId AND ((:start BETWEEN a.start AND a.end) OR (:end BETWEEN a.start AND a.end))")
    List<Appointment> findOverlap(@Param("start") Timestamp start, @Param("end") Timestamp end, @Param("userId") Long userId);

    @Query("SELECT a FROM Appointment a LEFT JOIN UserAppointment ua ON a.id = ua.appointmentId WHERE ua.userId = :userId AND (((:start BETWEEN a.start AND a.end) OR (:end BETWEEN a.start AND a.end)) AND a.id <> :appToIgnore)")
    List<Appointment> findOverlapWithException(@Param("start") Timestamp start, @Param("end") Timestamp end, @Param("userId") Long userId, @Param("appToIgnore")Long appToIgnore);

    @Query("SELECT a FROM Appointment a WHERE a.start BETWEEN :startTime AND :endTime")
    List<Appointment> getAppointmentToNotify(@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
}
