package com.cwitter.dto;

/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 16/7/16
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationResponseDto {
    String message;
    boolean success;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
