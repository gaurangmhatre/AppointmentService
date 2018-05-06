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
    public static final BusinessException INVALID_EMAIL = new BusinessException("Invalid Email", 400);
    public static final BusinessException INVALID_NOTIFICATION_PREF = new BusinessException("Invalid notification preference, valid values are ['ALL', 'SMS', 'EMAIL']", 400);
}
