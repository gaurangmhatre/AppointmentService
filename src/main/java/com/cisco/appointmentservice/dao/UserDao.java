package com.cisco.appointmentservice.dao;

import com.cisco.appointmentservice.dao.beans.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
