package ru.betanalysis.model;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Set;


/**
 * Ставка
 *
 */
public class Bet {

    /**
     * Cписок событий
     */
    private final Set<BookmakerEvent> event;

    /**
     * Размер ставки
     *
     */
    private final double value;

    /**
     * Валюта, в которой сделана ставка
     */
    private final Currency currency;

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


    public Bet(Set<BookmakerEvent> event, double value, Currency currency, double returnSum, double netProfit, double coefficient, LocalDateTime dateTime, boolean isExpress) {
        this.event = event;
        this.value = value;
        this.currency = currency;
        this.returnSum = returnSum;
        this.netProfit = netProfit;
        this.coefficient = coefficient;
        this.dateTime = dateTime;
        this.isExpress = isExpress;
    }

    public Set<BookmakerEvent> getEvent() {
        return event;
    }

    public double getValue() {
        return value;
    }

    public Currency getCurrency() {
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
                "event=" + event +
                ", value='" + value + '\'' +
                ", currency=" + currency +
                ", returnSum=" + returnSum +
                ", netProfit=" + netProfit +
                ", coefficient=" + coefficient +
                ", dateTime=" + dateTime +
                ", isExpress=" + isExpress +
                '}';
    }
}
