package com.cisco.appointmentservice.service;

import com.cisco.appointmentservice.exception.BusinessException;
import io.swagger.model.User;

public interface UserService {
    User getUser(Long id) throws BusinessException;
    User getUser(String email) throws BusinessException;
    User createUser(User user) throws BusinessException;
    User updateUser(User user) throws BusinessException;
    void deleteUser(Long id) throws BusinessException;
}