package com.cisco.appointmentservice.controller;

import com.cisco.appointmentservice.exception.BusinessException;
import com.cisco.appointmentservice.service.UserService;
import com.cisco.appointmentservice.util.ServiceUtil;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(name = "id") Long id) {
        try {
            User user = userService.getUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getCode()).body(ServiceUtil.buildErrorResponse(be.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ServiceUtil.buildErrorResponse(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            ServiceUtil.validateEmail(user.getEmail());
            ServiceUtil.validateUserPref(user.getNotification());
            User newUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getCode()).body(ServiceUtil.buildErrorResponse(be.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ServiceUtil.buildErrorResponse(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        try {
            ServiceUtil.validateEmail(user.getEmail());
            ServiceUtil.validateUserPref(user.getNotification());
            User newUser = userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getCode()).body(ServiceUtil.buildErrorResponse(be.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ServiceUtil.buildErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(name = "id") Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getCode()).body(ServiceUtil.buildErrorResponse(be.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ServiceUtil.buildErrorResponse(e.getMessage()));
        }
    }

    /*@PostMapping("/test")
    public ResponseEntity<Object> testTime(@RequestBody TimeTest timeTest) {
        LocalDateTime utc = DateUtil.getLocalDateTimeAtUTC(timeTest.getLocalDateTime(), timeTest.getZone());
        System.out.println("Local dateTime: " + timeTest.getLocalDateTime());
        System.out.println("Local dateTime UTC " + utc);
        System.out.println("Timestamp UTC " + DateUtil.getTimeStamp(utc));

        System.out.println("ZonedDateTime " + DateUtil.getZonedDateTime(timeTest.getLocalDateTime(), timeTest.getZone()));
        System.out.println("ZonedDateTime UTC " +DateUtil.getZonedDateTimeAtUTC(DateUtil.getZonedDateTime(timeTest.getLocalDateTime(), timeTest.getZone())));


        System.out.println("Now LocalDateTime " + LocalDateTime.now());
        System.out.println("Now LocalDateTime Timestamp " + Timestamp.valueOf(LocalDateTime.now()));
        System.out.println("Now LocalDateTime UTC" + LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")));
        System.out.println("Now LocalDateTime UTC Timestamp" + Timestamp.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()));

        return ResponseEntity.ok().body(timeTest);
    }*/
}

