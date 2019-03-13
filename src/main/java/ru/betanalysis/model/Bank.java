package ru.betanalysis.model;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Set;

/**
 * Банк
 *
 */
public class Bank {

    /**
     * Список пользователей, которым принадлежит банк
     */
    private final Set<User> users;

    /**
     * Сумма на счету
     */
    private final double amount;

    /**
     * Валюта
     */
    private final Currency currency;

    /**
     * Время в которое банк имел данную сумму
     */
    private final LocalDateTime dateTime;

    /**
     * Метка, означающая, что от данной суммы берется процент
     */
    private final boolean percentable;

    public Bank(Set<User> users, double amount, Currency currency, LocalDateTime dateTime, boolean percentable) {
        this.users = users;
        this.amount = amount;
        this.currency = currency;
        this.dateTime = dateTime;
        this.percentable = percentable;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "users=" + users +
                ", amount=" + amount +
                ", currency=" + currency +
                ", dateTime=" + dateTime +
                ", percentable=" + percentable +
                '}';
    }
}
