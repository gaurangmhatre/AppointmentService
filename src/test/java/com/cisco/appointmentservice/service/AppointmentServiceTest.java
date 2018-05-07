package com.cisco.appointmentservice.service;

import com.cisco.appointmentservice.TestUtil;
import com.cisco.appointmentservice.exception.BusinessException;
import io.swagger.model.Appointment;
import io.swagger.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.cisco.appointmentservice.exception.BusinessException.*;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    private User foo;
    private User bar;
    private User joe;

    @Before
    public void init() {
        foo = TestUtil.getUser("foo@outlook.com", "Foo", "EMAIL", "+14081112345");
        bar = TestUtil.getUser("bar@cisco.com", "Bar", "SMS", "+14081112345");
        joe = TestUtil.getUser("joe@cisco.com", "Joe", "ALL", "+14081112345");

        try {
            foo = userService.createUser(foo);
            bar = userService.createUser(bar);
            joe = userService.createUser(joe);
        } catch (BusinessException e) {
            //Fail
            assertTrue(false);
        }
    }

    @Test
    public void testCreateAppointment() {
        List<User> participants = new ArrayList<>();
        participants.add(foo);
        participants.add(bar);
        participants.add(joe);

        LocalDateTime start = LocalDateTime.now().plusMinutes(1);
        LocalDateTime end = LocalDateTime.now().plusMinutes(6);
        Appointment appointment = TestUtil.getAppointment(start,
                                        end,
                                        foo,
                                        participants,
                                        false);
        try {
            appointment = appointmentService.createAppointment(appointment, ZoneId.systemDefault());
            assertThat("Appointment created successfully", appointment.getId(), is(notNullValue()));

            //Fetch from DB
            Appointment appointmentStored = appointmentService.getAppointment(appointment.getId(), ZoneId.systemDefault());
            assertThat("Appointment fetched successfully", appointmentStored.getId(), equalTo(appointment.getId()));
            assertThat("Appointment Start date set successfully", appointmentStored.getFrom(), equalTo(appointment.getFrom()));
            assertThat("Appointment To date set successfully", appointmentStored.getTo(), equalTo(appointment.getTo()));
            assertThat("Appointment Host set successfully", appointmentStored.getHost(), equalTo(appointment.getHost()));
            assertThat("Appointment Participants set successfully", appointmentStored.getParticipants(), equalTo(appointment.getParticipants()));
        } catch (BusinessException e) {
            assertThat("Failed to create appointment", false);
        } finally {
            if(appointment != null && appointment.getId() != null) {
                try {
                    appointmentService.cancelAppointment(appointment.getId(), foo.getEmail());
                } catch (BusinessException e) {
                    assertThat("Failed to cancel appointment after creation", false);
                }
            }
        }
    }

    @Test
    public void testCreateAppointmentInvalidRequest() {
        List<User> participants = new ArrayList<>();
        participants.add(foo);
        participants.add(bar);
        participants.add(joe);

        LocalDateTime start = LocalDateTime.now().plusMinutes(1);
        LocalDateTime end = LocalDateTime.now().plusMinutes(6);

        //Test for no host
        Appointment appointment = TestUtil.getAppointment(start,
                end,
                null,
                participants,
                false);
        try {
            appointmentService.createAppointment(appointment, ZoneId.systemDefault());
            assertThat("Appointment should not be created", false);
        } catch (BusinessException e) {
            assertThat("Appointment should not be created", e, is(equalTo(INVALID_APPOINTMENT_REQUEST)));
        }

        //Test for no start
        appointment = TestUtil.getAppointment(null,
                end,
                foo,
                participants,
                false);
        try {
            appointmentService.createAppointment(appointment, ZoneId.systemDefault());
            assertThat("Appointment should not be created", false);
        } catch (BusinessException e) {
            assertThat("Appointment should not be created", e, is(equalTo(INVALID_APPOINTMENT_REQUEST)));
        }

        //Test for no end
        appointment = TestUtil.getAppointment(start,
                null,
                foo,
                participants,
                false);
        try {
            appointmentService.createAppointment(appointment, ZoneId.systemDefault());
            assertThat("Appointment should not be created", false);
        } catch (BusinessException e) {
            assertThat("Appointment should not be created", e, is(equalTo(INVALID_APPOINTMENT_REQUEST)));
        }

        //Test for no participants
        appointment = TestUtil.getAppointment(start,
                end,
                foo,
                null,
                false);
        try {
            appointmentService.createAppointment(appointment, ZoneId.systemDefault());
            assertThat("Appointment should not be created", false);
        } catch (BusinessException e) {
            assertThat("Appointment should not be created", e, is(equalTo(MIN_PARTICIPANTS_REQUIRED)));
        }
    }

    @Test
    public void testCreateAppointmentInvalidStartEndDates() {
        List<User> participants = new ArrayList<>();
        participants.add(foo);
        participants.add(bar);
        participants.add(joe);

        //Test start is after end
        LocalDateTime start = LocalDateTime.now().plusMinutes(6);
        LocalDateTime end = LocalDateTime.now().plusMinutes(1);
        Appointment appointment = TestUtil.getAppointment(start,
                end,
                foo,
                participants,
                false);
        try {
            appointmentService.createAppointment(appointment, ZoneId.systemDefault());
            assertThat("Appointment should not be created", false);
        } catch (BusinessException e) {
            assertThat("Appointment should not be created", e, is(equalTo(INVALID_APPOINTMENT_DATES)));
        }

        //Test start is equal to end
        start = LocalDateTime.now().plusMinutes(1);
        end = LocalDateTime.now().plusMinutes(1);
        appointment = TestUtil.getAppointment(start,
                end,
                foo,
                participants,
                false);
        try {
            appointmentService.createAppointment(appointment, ZoneId.systemDefault());
            assertThat("Appointment should not be created", false);
        } catch (BusinessException e) {
            assertThat("Appointment should not be created", e, is(equalTo(INVALID_APPOINTMENT_DATES)));
        }
    }

    @Test
    public void testCreateAppointmentNoOrOneParticipant() {
        List<User> participants = new ArrayList<>();
        LocalDateTime start = LocalDateTime.now().plusMinutes(1);
        LocalDateTime end = LocalDateTime.now().plusMinutes(6);
        Appointment appointment = TestUtil.getAppointment(start,
                end,
                foo,
                participants,
                false);
        try {
            appointmentService.createAppointment(appointment, ZoneId.systemDefault());
            assertThat("Appointment should not be created", false);
        } catch (BusinessException e) {
            assertThat("Appointment should not be created", e, is(equalTo(MIN_PARTICIPANTS_REQUIRED)));
        }

        participants.add(foo);
        appointment = TestUtil.getAppointment(start,
                end,
                foo,
                participants,
                false);
        try {
            appointmentService.createAppointment(appointment, ZoneId.systemDefault());
            assertThat("Appointment should not be created", false);
        } catch (BusinessException e) {
            assertThat("Appointment should not be created", e, is(equalTo(MIN_PARTICIPANTS_REQUIRED)));
        }
    }

    @Test
    public void testCreateAppointmentInPast() {
        List<User> participants = new ArrayList<>();
        participants.add(foo);
        participants.add(bar);
        participants.add(joe);

        LocalDateTime start = LocalDateTime.now().minusMinutes(1);
        LocalDateTime end = LocalDateTime.now().plusMinutes(6);
        Appointment appointment = TestUtil.getAppointment(start,
                end,
                foo,
                participants,
                false);
        try {
            appointment = appointmentService.createAppointment(appointment, ZoneId.systemDefault());
            assertThat("Appointment should not be created", false);
        } catch (BusinessException e) {
            assertThat("Appointment should not be created", e, is(equalTo(CANT_CREATE_APPOINTMENT_TO_THE_PAST)));
        } finally {
            if(appointment != null && appointment.getId() != null) {
                try {
                    appointmentService.cancelAppointment(appointment.getId(), foo.getEmail());
                } catch (BusinessException e) {
                    assertThat("Failed to cancel appointment after creation", false);
                }
            }
        }
    }

    @Test
    public void testCreateAppointmentWithOverlap() {
        List<User> participants = new ArrayList<>();
        participants.add(foo);
        participants.add(bar);
        participants.add(joe);

        LocalDateTime start = LocalDateTime.now().plusMinutes(1);
        LocalDateTime end = LocalDateTime.now().plusMinutes(6);
        Appointment firstAppointment = TestUtil.getAppointment(start,
                end,
                foo,
                participants,
                false);
        try {
            firstAppointment = appointmentService.createAppointment(firstAppointment, ZoneId.systemDefault());
            assertThat("Appointment should created successfully", true);
        } catch (BusinessException e) {
            assertThat("Appointment creation should not fail", false);
        }

        Appointment secondAppointment = TestUtil.getAppointment(start,
                end,
                foo,
                participants,
                true);

        try {
            secondAppointment = appointmentService.createAppointment(secondAppointment, ZoneId.systemDefault());
            assertThat("Second Appointment should created successfully", true);
        } catch (BusinessException e) {
            assertThat("Second Appointment creation should not fail", false);
        }

        //Overlaps not allowed
        Appointment thirdAppointment = TestUtil.getAppointment(start,
                end,
                foo,
                participants,
                false);
        try {
            appointmentService.createAppointment(thirdAppointment, ZoneId.systemDefault());
            assertThat("Third Appointment should not be created successfully", false);
        } catch (BusinessException e) {
            assertThat("Third Appointment creation should fail", e, is(equalTo(OVERLAPPING_APPOINTMENTS)));
        } finally {
            if(firstAppointment != null && firstAppointment.getId() != null) {
                try {
                    appointmentService.cancelAppointment(firstAppointment.getId(), foo.getEmail());
                } catch (BusinessException e) {
                    assertThat("Failed to cancel appointment after creation", false);
                }
            }

            if(secondAppointment != null && secondAppointment.getId() != null) {
                try {
                    appointmentService.cancelAppointment(secondAppointment.getId(), foo.getEmail());
                } catch (BusinessException e) {
                    assertThat("Failed to cancel appointment after creation", false);
                }
            }
        }
    }

    @After
    public void cleanUp() {
        try {
            userService.deleteUser(foo.getId());
            userService.deleteUser(bar.getId());
            userService.deleteUser(joe.getId());
        } catch (BusinessException e) {
            //Fail
            assertTrue(false);
        }
    }
}
