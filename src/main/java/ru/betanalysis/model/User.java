package ru.betanalysis.model;


import java.time.LocalDateTime;
import java.util.Set;

/**
 * Пользователь
 */
public class User {

    /**
     * Спиоск банков пользователя
     */
    private final Set<Bank> banks;

    /**
     * email;
     */
    private final String email;

    /**
     * пароль
     */
    private final String password;

    /**
     * Фамилия
     */
    private final String secondName;

    /**
     * Имя
     */
    private final String firstName;

    /**
     * Номер телефона
     */
    private final String phoneNumber;

    /**
     * Дата рождения
     */
    private final LocalDateTime dateTime;


    public User(Set<Bank> banks, String email, String password,
                String secondName, String firstName, String phoneNumber, LocalDateTime dateTime) {
        this.banks = banks;
        this.email = email;
        this.password = password;
        this.secondName = secondName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.dateTime = dateTime;
    }

    public Set<Bank> getBanks() {
        return banks;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "banks=" + banks +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", secondName='" + secondName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
