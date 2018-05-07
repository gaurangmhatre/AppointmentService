package com.cisco.appointmentservice.controller;

import io.swagger.annotations.*;
import io.swagger.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(value = "user", description = "the user API")
@RequestMapping("/user")
public interface UserApi {

    @ApiOperation(value = "Add a new User", nickname = "addUser", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid input") })
    @RequestMapping(value = "",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Object> createUser(@ApiParam(value = "User that needs to be created" ,required=true )  @Valid @RequestBody User body);

    @ApiOperation(value = "Find user ID", nickname = "getUser", notes = "Returns a single user", response = User.class, tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found User", response = User.class),
            @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Object> getUser(@ApiParam(value = "ID of user to return",required=true) @PathVariable("id") Long id);

    @ApiOperation(value = "Update an existing User", nickname = "updateUser", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Object> updateUser(@ApiParam(value = "User that needs to be updated" ,required=true )  @Valid @RequestBody User body);

    @ApiOperation(value = "Deletes an user", nickname = "deleteUser", notes = "", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation"),
            @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<Object> deleteUser(@ApiParam(value = "User id to delete",required=true) @PathVariable("id") Long id);

}
