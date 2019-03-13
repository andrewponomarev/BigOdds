package ru.betanalysis.model;


/**
 * Букмекерское событие
 */
public class BookmakerEvent {

    /**
     * Спортивное событие
     */
    private final SportEvent sportEvent;

    /**
     * Тип ставки
     */
    private final BetType betType;

    /**
     * Результат события
     */
    private final BetResult result;

    /**
     * Коэффициент
     */
    private final double coefficient;

    /**
     * Букмекер
     */
    private final Bookmaker bookmaker;

    public BookmakerEvent(SportEvent sportEvent, BetType betType, BetResult result, double coefficient, Bookmaker bookmaker) {
        this.sportEvent = sportEvent;
        this.betType = betType;
        this.result = result;
        this.coefficient = coefficient;
        this.bookmaker = bookmaker;
    }

    public SportEvent getSportEvent() {
        return sportEvent;
    }

    public BetType getBetType() {
        return betType;
    }

    public BetResult getResult() {
        return result;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public Bookmaker getBookmaker() {
        return bookmaker;
    }

    @Override
    public String toString() {
        return "BookmakerEvent{" +
                "sportEvent=" + sportEvent +
                ", betType=" + betType +
                ", result=" + result +
                ", coefficient=" + coefficient +
                ", bookmaker=" + bookmaker +
                '}';
    }
}
