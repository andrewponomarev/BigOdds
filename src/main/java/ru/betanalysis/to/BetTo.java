package ru.betanalysis.to;

import java.time.LocalDateTime;
import java.util.Objects;

public class BetTo {

    private final String event;

    private final double value;

    private final double coefficient;

    private final LocalDateTime dateTime;

    private final boolean express;

    public BetTo(String event, double value, double coefficient, LocalDateTime dateTime, boolean express) {
        this.event = event;
        this.value = value;
        this.coefficient = coefficient;
        this.dateTime = dateTime;
        this.express = express;
    }

    public String getEvent() {
        return event;
    }

    public double getValue() {
        return value;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isExpress() {
        return express;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetTo betTo = (BetTo) o;
        return Double.compare(betTo.value, value) == 0 &&
                Double.compare(betTo.coefficient, coefficient) == 0 &&
                express == betTo.express &&
                Objects.equals(event, betTo.event) &&
                Objects.equals(dateTime, betTo.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, value, coefficient, dateTime, express);
    }

    @Override
    public String toString() {
        return "BetTo{" +
                "event='" + event + '\'' +
                ", value=" + value +
                ", coefficient=" + coefficient +
                ", dateTime=" + dateTime +
                ", express=" + express +
                '}';
    }
}
