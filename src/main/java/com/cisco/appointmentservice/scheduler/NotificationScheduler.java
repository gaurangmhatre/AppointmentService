package com.cisco.appointmentservice.scheduler;

import com.cisco.appointmentservice.dao.AppointmentDao;
import com.cisco.appointmentservice.dao.beans.Appointment;
import com.cisco.appointmentservice.dao.beans.User;
import com.cisco.appointmentservice.service.NotificationService;
import com.cisco.appointmentservice.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
@PropertySource("classpath:application.properties")
public class NotificationScheduler {
    @Value("${span}")
    private int span;

    @Autowired
    @Qualifier("SMSNotificationService")
    private NotificationService smsNotificationService;

    @Autowired
    @Qualifier("emailNotificationService")
    private NotificationService emailNotificationService;

    @Autowired
    AppointmentDao appointmentDao;

    @Scheduled(fixedDelay = 60000)
    public void sendNotifications() {
        LocalDateTime localDateTimeAfterSpan = LocalDateTime.now().plusMinutes(span);
        Timestamp start = DateUtil.getTimeStamp(DateUtil.getLocalDateTimeAtUTC(localDateTimeAfterSpan, ZoneId.systemDefault()));
        Timestamp end = DateUtil.getTimeStamp(DateUtil.getLocalDateTimeAtUTC(localDateTimeAfterSpan.plusMinutes(1), ZoneId.systemDefault()));

        System.out.println("sendNotifications start: "+ start);
        System.out.println("sendNotifications end: "+ end);

        List<Appointment> appointments = appointmentDao.getAppointmentToNotify(start, end);

        for (Appointment appointment: appointments) {
            for(User user: appointment.getParticipants()) {
                switch (user.getUserPref()) {
                    case "SMS":
                        smsNotificationService.notifyUser(user, appointment);
                        break;
                    case "EMAIL":
                        emailNotificationService.notifyUser(user, appointment);
                        break;
                    case "ALL":
                        smsNotificationService.notifyUser(user, appointment);
                        emailNotificationService.notifyUser(user, appointment);
                }
            }
        }

    }
}
