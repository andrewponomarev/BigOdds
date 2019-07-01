package ru.betanalysis.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Set;


/**
 * Ставка
 *
 */
@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Bet.DELETE, query = "DELETE FROM Bet b WHERE b.id=:id AND b.user.id=:userId"),
        @NamedQuery(name = Bet.ALL_SORTED, query = "SELECT b FROM Bet b WHERE b.user.id=:userId ORDER BY b.dateTime DESC"),
        @NamedQuery(name = Bet.BETWEEN, query = "SELECT b FROM Bet b WHERE b.user.id=:userId AND " +
                "b.dateTime BETWEEN :startDate AND :endDate ORDER BY b.dateTime DESC")
})
@Entity
@Table(name = "bets", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "bets_unique_user_datetime_idx")})
public class Bet extends AbstractBaseEntity {

//    /**
//     * Cписок событий
//     */
//    private final Set<BookmakerEvent> events;

    public static final String DELETE = "Bet.delete";
    public static final String ALL_SORTED = "Bet.getAllSorted";
    public static final String BETWEEN = "Bet.getBetween";

    /**
     * Событие
     */
    @Column(name="event", nullable = false)
    @NotBlank
    private String event;

    /**
     * Размер ставки
     *
     */
    @Column(name="value", nullable = false)
    @NotNull
    private double value;

    /**
     * Валюта, в которой сделана ставка
     */
    @Column(name="currency", nullable = false)
    @NotBlank
    private String currency;

    /**
     * Сумма выигрыша/проигрыша
     */
    @Column(name="return_sum")
    private double returnSum;

    /**
     * Чистая прибыль
     */
    @Column(name="net_profit")
    private double netProfit;

    /**
     * Коэффициент
     */
    @Column(name="coefficient", nullable = false)
    @NotNull
    private double coefficient;

    /**
     * Дата/время
     */
    @Column(name="date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    /**
     * Признак экспресса
     */
    @Column(name="is_express")
    private boolean isExpress;

    /**
     * Пользователь
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Bet() {
    }

    public Bet(String event, double value, String currency, double returnSum, double netProfit,
               double coefficient, LocalDateTime dateTime, boolean isExpress) {
        this(null, event, value, currency, returnSum, netProfit, coefficient, dateTime, isExpress);
    }

    public Bet(Integer id, String event, double value, String currency, double returnSum, double netProfit,
               double coefficient, LocalDateTime dateTime, boolean isExpress) {
        super(id);
        this.event = event;
        this.value = value;
        this.currency = currency;
        this.returnSum = returnSum;
        this.netProfit = netProfit;
        this.coefficient = coefficient;
        this.dateTime = dateTime;
        this.isExpress = isExpress;
    }

    public String getEvent() {
        return event;
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public double getReturnSum() {
        return returnSum;
    }

    public double getNetProfit() {
        return netProfit;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isExpress() {
        return isExpress;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setReturnSum(double returnSum) {
        this.returnSum = returnSum;
    }

    public void setNetProfit(double netProfit) {
        this.netProfit = netProfit;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setExpress(boolean express) {
        isExpress = express;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", event=" + event +
                ", value=" + value +
                ", currency=" + currency +
                ", returnSum=" + returnSum +
                ", netProfit=" + netProfit +
                ", coefficient=" + coefficient +
                ", dateTime=" + dateTime +
                ", isExpress=" + isExpress +
                '}';
    }
}
