package ru.betanalysis.model;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

/**
 * Пользователь
 */
public class User extends AbstractNamedEntity  {

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

    /**
     * Активен ли пользователь
     */
    private boolean enabled = true;

    /**
     * Дата регистрации
     */
    private final Date registered = new Date();

    /**
     * Роли
     */
    private Set<Role> roles;

    public User(Integer id, String name, String email, String password, String secondName,
                String firstName, String phoneNumber, LocalDateTime dateTime, Role role, Role... roles) {
        this(id, name, email, password, secondName, firstName, phoneNumber, dateTime,
                true, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, String secondName,
                String firstName, String phoneNumber, LocalDateTime dateTime,
                boolean enabled, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.secondName = secondName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.dateTime = dateTime;
        this.enabled = enabled;
        this.roles = roles;
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
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", secondName='" + secondName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateTime=" + dateTime +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
