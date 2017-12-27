package com.demoproject.mydemo.Models.Forms;

public class FooterContactFormModel {
    String name;
    String email;
    String message;
    boolean sendingMessageFlag = true;


    public FooterContactFormModel() {
    }

    public FooterContactFormModel(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public boolean issendingMessageFlag() {
        return sendingMessageFlag;
    }

    public void setsendingMessageFlag(boolean sendingMessageFlag) {
        this.sendingMessageFlag = sendingMessageFlag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
