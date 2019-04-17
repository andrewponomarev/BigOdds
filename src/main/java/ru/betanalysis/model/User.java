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
    private String email;

    /**
     * пароль
     */
    private String password;

    /**
     * Фамилия
     */
    private String secondName;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Номер телефона
     */
    private String phoneNumber;

    /**
     * Дата рождения
     */
    private LocalDateTime dateTime;

    /**
     * Активен ли пользователь
     */
    private boolean enabled = true;

    /**
     * Дата регистрации
     */
    private Date registered = new Date();

    /**
     * Роли
     */
    private Set<Role> roles;

    public User() {
    }

    public User(User other) {
        this.email = other.email;
        this.password = other.password;
        this.secondName = other.secondName;
        this.firstName = other.firstName;
        this.phoneNumber = other.phoneNumber;
        this.dateTime = other.dateTime;
        this.enabled = other.enabled;
        this.roles = other.roles;
    }

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
