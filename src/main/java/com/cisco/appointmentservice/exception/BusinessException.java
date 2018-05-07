package com.cisco.appointmentservice.exception;

public class BusinessException extends Exception {

    private int code;

    private BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }


    public static final BusinessException FAILED_TO_CREATE_USER = new BusinessException("Failed to create user", 500);
    public static final BusinessException FAILED_TO_FETCH_USER = new BusinessException("Failed to get user", 500);
    public static final BusinessException FAILED_TO_DELETE_USER = new BusinessException("Failed to remove user", 500);
    public static final BusinessException FAILED_TO_UPDATE_USER = new BusinessException("Failed to update user", 500);
    public static final BusinessException USER_ALREADY_EXISTS = new BusinessException("User already exists with provided email address", 500);
    public static final BusinessException NO_SUCH_USER_EXISTS = new BusinessException("No Such User exists", 404);
    public static final BusinessException NO_SUCH_APPOINTMENT_EXISTS = new BusinessException("No such appointment exists", 404);
    public static final BusinessException INVALID_EMAIL = new BusinessException("Invalid Email", 400);
    public static final BusinessException INVALID_NOTIFICATION_PREF = new BusinessException("Invalid notification preference, valid values are ['ALL', 'SMS', 'EMAIL']", 400);
    public static final BusinessException INVALID_APPOINTMENT_REQUEST = new BusinessException("Invalid appointment object", 400);
    public static final BusinessException INVALID_APPOINTMENT_DATES = new BusinessException("Please provide valid appointment duration (to and from)", 400);
    public static final BusinessException MIN_PARTICIPANTS_REQUIRED = new BusinessException("There should at least be 2 participants in an appointment", 400);
    public static final BusinessException INVALID_ZONE_VALUE = new BusinessException("Invalid zone value provided", 400);
    public static final BusinessException HOST_NOT_FOUND = new BusinessException("Host not found", 404);
    public static final BusinessException PARTICIPANT_NOT_FOUND = new BusinessException("Participant not found, please validate participant list", 404);
    public static final BusinessException OVERLAPPING_APPOINTMENTS = new BusinessException("Overlapping appointment found, please consider different time", 400);
    public static final BusinessException NOT_ALLOWED = new BusinessException("User is not allowed to perform this operation", 403);
    public static final BusinessException APPOINTMENT_ID_NOT_PROVIDED = new BusinessException("Need appointment id for update", 400);
    public static final BusinessException CANT_UPDATE_APPOINTMENT_TO_THE_PAST = new BusinessException("Can't start the appointment to start in the past", 400);
    public static final BusinessException CANT_CREATE_APPOINTMENT_TO_THE_PAST = new BusinessException("Can't create new appointment to start in the past", 400);
    public static final BusinessException CANT_CANCEL_APPOINTMENT_IN_PAST = new BusinessException("Can't cancel an appointment in the past", 400);
    public static final BusinessException CANT_UPDATE_APPOINTMENT_IN_PAST = new BusinessException("Can't update an appointment that started in the past", 400);




}
