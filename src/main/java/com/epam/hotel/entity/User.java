package com.epam.hotel.entity;

import java.util.Objects;

/**
 * The entity of user table.
 */
public class User {
    private String firstName;
    private String lastName;
    private byte password;
    private String userType;

    public User(String firstName, String lastName, byte password, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userType = userType;
    }

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

    public byte getPassword() {
        return password;
    }

    public void setPassword(byte password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return password == user.password &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                userType.equals(user.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, password, userType);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password=" + password +
                ", userType='" + userType + '\'' +
                '}';
    }
}
