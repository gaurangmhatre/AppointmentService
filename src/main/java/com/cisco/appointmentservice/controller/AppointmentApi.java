package com.cisco.appointmentservice.controller;

import io.swagger.annotations.*;
import io.swagger.model.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(value = "appointment", description = "The appointment API")
@RequestMapping("/appointment")
public interface AppointmentApi {

    @ApiOperation(value = "Create a new Appointment", nickname = "createAppointment", notes = "", tags={ "appointment", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid input") })
    @RequestMapping(value = "",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Object> createAppointment(@ApiParam(value = "Appointment object that needs to be created" ,required=true )  @Valid @RequestBody Appointment body, @ApiParam(value = "Local time zone of the requester" ,required=true) @RequestHeader(value="X-zone", required=true) String xZone);

    @ApiOperation(value = "Get Appointment", nickname = "getAppointment", notes = "", response = Appointment.class, tags={ "appointment", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Appointment.class),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Appointment not found") })
    @RequestMapping(value = "/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Object> getAppointment(@ApiParam(value = "Appointment id for object that needs to be created",required=true) @PathVariable("id") Long id, @ApiParam(value = "Local time zone of the requester" ,required=true) @RequestHeader(value="X-zone", required=true) String xZone);

    @ApiOperation(value = "Update an existing Appointment", nickname = "updateAppointment", notes = "", tags={ "appointment", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid input") })
    @RequestMapping(value = "",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Object> updateAppointment(@ApiParam(value = "Appointment object that needs to be updated" ,required=true )  @Valid @RequestBody Appointment body,@ApiParam(value = "Local time zone of the requester" ,required=true) @RequestHeader(value="X-zone", required=true) String xZone);

    @ApiOperation(value = "Cancel an Appointment", nickname = "cancelAppointment", notes = "", tags={ "appointment", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Meeting not found") })
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity<Object> cancelAppointment(@ApiParam(value = "Appointment id for object that needs to be created",required=true) @PathVariable("id") Long id,@NotNull @ApiParam(value = "Email address of the requester", required = true) @Valid @RequestParam(value = "user", required = true) String user);



}
