package com.cisco.appointmentservice.controller;

import com.cisco.appointmentservice.exception.BusinessException;
import com.cisco.appointmentservice.service.AppointmentService;
import com.cisco.appointmentservice.util.ServiceUtil;
import io.swagger.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;

@RestController
public class AppointmentController implements AppointmentApi {
    @Autowired
    private AppointmentService appointmentService;

    @Override
    public ResponseEntity<Object> createAppointment(@RequestBody Appointment appointment, @RequestHeader(value="X-zone") String xZone) {
        try {
            ServiceUtil.validateAppointment(appointment);
            ZoneId zoneId = ServiceUtil.getZoneId(xZone);
            Appointment newAppointment = appointmentService.createAppointment(appointment, zoneId);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getCode()).body(ServiceUtil.buildErrorResponse(be.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ServiceUtil.buildErrorResponse(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<Object> updateAppointment(@RequestBody Appointment appointment, @RequestHeader(value="X-zone") String xZone) {
        try {
            ServiceUtil.validateAppointmentForUpdate(appointment);
            ZoneId zoneId = ServiceUtil.getZoneId(xZone);
            Appointment newAppointment = appointmentService.updateAppointment(appointment, zoneId);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getCode()).body(ServiceUtil.buildErrorResponse(be.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ServiceUtil.buildErrorResponse(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<Object> getAppointment(@PathVariable("id") Long id, @RequestHeader(value="X-zone") String xZone) {
        try {
            ZoneId zoneId = ServiceUtil.getZoneId(xZone);
            Appointment appointment = appointmentService.getAppointment(id, zoneId);
            return ResponseEntity.status(HttpStatus.OK).body(appointment);
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getCode()).body(ServiceUtil.buildErrorResponse(be.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ServiceUtil.buildErrorResponse(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<Object> cancelAppointment(@PathVariable("id") Long id, @RequestParam(value = "user") String user) {
        try {
            ServiceUtil.validateEmail(user);
            appointmentService.cancelAppointment(id, user);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getCode()).body(ServiceUtil.buildErrorResponse(be.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ServiceUtil.buildErrorResponse(e.getMessage()));
        }
    }
}
