package com.vfoexchange.restServer.dto;

public class EnquiringUserDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String comments;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String toString() {
        return new StringBuilder().append("firstName: ").append(firstName).append(", lastName: ")
                .append(lastName).append(", phone: ").append(phone).append(", email: ")
                .append(email).append(", comments/questions/bestTimeToCall: ").append(comments)
                .toString();
    }
}

