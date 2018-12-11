package com.roncoo.eshop.inventory.vo;

public class Response {

    public static final String SUCCESS = "success";

    public static final String FAIL = "fail";

    private String status;

    private String message;

    public Response(String status) {
        this.status = status;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}