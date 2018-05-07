package com.cisco.appointmentservice;

import com.cisco.appointmentservice.dao.AppointmentDao;
import com.cisco.appointmentservice.dao.UserDao;
import com.cisco.appointmentservice.dao.beans.Appointment;
import com.cisco.appointmentservice.dao.beans.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class AppointmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentServiceApplication.class, args);
	}

	@Bean
    CommandLineRunner runner(UserDao userDao, AppointmentDao appointmentDao) {
	    return args -> {
	        User user = new User();
	        user.setEmail("sushantvairagade@outlook.com");
			user.setPhone("+14086685748");
			user.setName("Sushant");
			user.setUserPref("ALL");
            user = userDao.save(user);

			User user1 = new User();
			user1.setEmail("sushantvairagade1@outlook.com");
			user1.setPhone("+14086685748");
			user1.setName("Sushant");
			user1.setUserPref("ALL");
			user1 = userDao.save(user1);


			Appointment appointment = new Appointment();
			appointment.setHost(user);
			appointment.setStart(Timestamp.valueOf(LocalDateTime.now()));
			appointment.setEnd(Timestamp.valueOf(LocalDateTime.now().plusMinutes(30)));
			List<User> users = new ArrayList<>();
			users.add(user);
			users.add(user1);
			appointment.setParticipants(users);
			appointmentDao.save(appointment);

		};
    }
}