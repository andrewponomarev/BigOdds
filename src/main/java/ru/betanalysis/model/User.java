package ru.betanalysis.model;


import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Пользователь
 */
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name, u.email"),
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity  {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String ALL_SORTED = "User.getAllSorted";

    /**
     * email;
     */
    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    /**
     * пароль
     */
    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    /**
     * Фамилия
     */
    @Column(name = "secondname", nullable = true)
    private String secondName;

    /**
     * Имя
     */
    @Column(name = "firstname", nullable = true)
    private String firstName;

    /**
     * Номер телефона
     */
    @Column(name = "phonenumber", nullable = true)
    private String phoneNumber;

    /**
     * Дата рождения
     */
    @Column(name = "datetime", nullable = true)
    private LocalDateTime dateTime;

    /**
     * Активен ли пользователь
     */
    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    /**
     * Дата регистрации
     */
    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    /**
     * Роли
     */
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("dateTime DESC")
    protected List<Bet> bets;

    public User() {
    }

    public User(User other) {
        super(other.getId(), other.getName());
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

    public List<Bet> getBets() {
        return bets;
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
