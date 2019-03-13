package ru.betanalysis.model;


import java.util.Set;

/**
 * Пользователь
 */
public class User {

    /**
     * Спиоск банков пользователя
     */
    private final Set<Bank> banks;

    public User(Set<Bank> banks) {
        this.banks = banks;
    }

    //todo: добавить везде toString()
}
