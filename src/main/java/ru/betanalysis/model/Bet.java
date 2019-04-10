package ru.betanalysis.model;

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
    private final String event;

    /**
     * Размер ставки
     *
     */
    private final double value;

    /**
     * Валюта, в которой сделана ставка
     */
    private final String currency;

    /**
     * Сумма выигрыша/проигрыша
     */
    private final double returnSum;

    /**
     * Чистая прибыль
     */
    private final double netProfit;

    /**
     * Коэффициент
     */
    private final double coefficient;

    /**
     * Дата/время
     */
    private final LocalDateTime dateTime;

    /**
     * Признак экспресса
     */
    private final boolean isExpress;


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
