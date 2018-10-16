package io.empirebank.jee.common;

public class ApiError {

    private int status_code;
    private String message;

    public ApiError() {
    }

    public ApiError(int status_code, String message) {
        this.status_code = status_code;
        this.message = message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
