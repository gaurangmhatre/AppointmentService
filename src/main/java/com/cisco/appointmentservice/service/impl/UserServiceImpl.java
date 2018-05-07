package com.cisco.appointmentservice.service.impl;

import com.cisco.appointmentservice.dao.UserDao;
import com.cisco.appointmentservice.exception.BusinessException;
import com.cisco.appointmentservice.mapstruct.UserMapper;
import com.cisco.appointmentservice.service.UserService;
import com.cisco.appointmentservice.util.ServiceUtil;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.cisco.appointmentservice.exception.BusinessException.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUser(Long id) throws BusinessException {
        try {
            Optional<com.cisco.appointmentservice.dao.beans.User> user = userDao.findById(id);
            if (user.isPresent()) {
                return UserMapper.INSTANCE.getUserModel(user.get());
            }
            throw NO_SUCH_USER_EXISTS;
        } catch(BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw FAILED_TO_FETCH_USER;
        }
    }

    @Override
    public User getUser(String email) throws BusinessException {
        try {
            com.cisco.appointmentservice.dao.beans.User user = userDao.findByEmail(email);
            if (user!= null) {
                return UserMapper.INSTANCE.getUserModel(user);
            }
            throw NO_SUCH_USER_EXISTS;
        } catch(BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw FAILED_TO_FETCH_USER;
        }
    }

    @Override
    public User createUser(User user) throws BusinessException {
        ServiceUtil.validateEmail(user.getEmail());
        ServiceUtil.validateUserPref(user.getNotification());
        try {
            com.cisco.appointmentservice.dao.beans.User userBean = UserMapper.INSTANCE.getUserBean(user);
            userBean = userDao.save(userBean);
            return UserMapper.INSTANCE.getUserModel(userBean);
        } catch (Exception e) {
            if(e.getMessage().contains("ConstraintViolationException"))
                throw USER_ALREADY_EXISTS;
            throw FAILED_TO_CREATE_USER;
        }
    }

    @Override
    public User updateUser(User user) throws BusinessException {
        ServiceUtil.validateEmail(user.getEmail());
        ServiceUtil.validateUserPref(user.getNotification());

        try {
            if(userDao.existsById(user.getId())) {
                com.cisco.appointmentservice.dao.beans.User userBean = UserMapper.INSTANCE.getUserBean(user);
                userBean = userDao.save(userBean);
                return UserMapper.INSTANCE.getUserModel(userBean);
            } else {
                throw NO_SUCH_USER_EXISTS;
            }
        } catch(BusinessException be) {
            throw be;
        } catch (Exception e) {
            if(e.getMessage().contains("ConstraintViolationException"))
                throw USER_ALREADY_EXISTS;
            throw FAILED_TO_UPDATE_USER;
        }
    }

    @Override
    public void deleteUser(Long id) throws BusinessException {
        User user = getUser(id);
        com.cisco.appointmentservice.dao.beans.User userBean = UserMapper.INSTANCE.getUserBean(user);
        try {
            userDao.delete(userBean);
        } catch (Exception e) {
            throw FAILED_TO_DELETE_USER;
        }
    }
}
