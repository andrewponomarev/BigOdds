package ru.betanalysis.model;

import java.time.LocalDateTime;

/**
 * Банковская транзакция - пополнение/снятие
 */
public class BankTransaction {

    private final Bank bank;

    private final double value;

    private final LocalDateTime dateTime;

    public BankTransaction(Bank bank, double value, LocalDateTime dateTime) {
        this.bank = bank;
        this.value = value;
        this.dateTime = dateTime;
    }
}
