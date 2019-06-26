package ru.betanalysis.model;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Set;


/**
 * Ставка
 *
 */
public class Bet extends AbstractBaseEntity {

//    /**
//     * Cписок событий
//     */
//    private final Set<BookmakerEvent> events;

    /**
     * Событие
     */
    private String event;

    /**
     * Размер ставки
     *
     */
    private double value;

    /**
     * Валюта, в которой сделана ставка
     */
    private String currency;

    /**
     * Сумма выигрыша/проигрыша
     */
    private double returnSum;

    /**
     * Чистая прибыль
     */
    private double netProfit;

    /**
     * Коэффициент
     */
    private double coefficient;

    /**
     * Дата/время
     */
    private LocalDateTime dateTime;

    /**
     * Признак экспресса
     */
    private boolean isExpress;

    /**
     * Пользователь
     */
    @ManyToOne(fetch = FetchType.LAZY)
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
