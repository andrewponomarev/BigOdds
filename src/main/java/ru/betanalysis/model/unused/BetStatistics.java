package ru.betanalysis.model.unused;


/**
 * Статистика по ставкам
 */
public class BetStatistics {

    /**
     * Чистая прибыль
     */
    private final double netProfit;

    /**
     * % с оборота (ROI)
     */
    private final double roi;

    /**
     * Средний размер ставки
     */
    private final double averageBetValue;

    /**
     * Маскимальный размер ставки
     */
    private final double maxBetValue;

    /**
     * Минимальный размер ставки
     */
    private final double minBetValue;

    /**
     * Средний коеффициент ставок
     */
    private final double averageBetCoefficient;

    /**
     * Оборот за все время %
     */
    private final double turnoverPercent;

    /**
     * Оборот за все время сумма
     */
    private final double turnoverValue;

    public BetStatistics(double netProfit, double roi, double averageBetValue, double maxBetValue,
                         double minBetValue, double averageBetCoefficient, double turnoverPercent,
                         double turnoverValue) {
        this.netProfit = netProfit;
        this.roi = roi;
        this.averageBetValue = averageBetValue;
        this.maxBetValue = maxBetValue;
        this.minBetValue = minBetValue;
        this.averageBetCoefficient = averageBetCoefficient;
        this.turnoverPercent = turnoverPercent;
        this.turnoverValue = turnoverValue;
    }

    public double getNetProfit() {
        return netProfit;
    }

    public double getRoi() {
        return roi;
    }

    public double getAverageBetValue() {
        return averageBetValue;
    }

    public double getMaxBetValue() {
        return maxBetValue;
    }

    public double getMinBetValue() {
        return minBetValue;
    }

    public double getAverageBetCoefficient() {
        return averageBetCoefficient;
    }

    public double getTurnoverPercent() {
        return turnoverPercent;
    }

    public double getTurnoverValue() {
        return turnoverValue;
    }

    @Override
    public String toString() {
        return "BetStatistics{" +
                "netProfit=" + netProfit +
                ", roi=" + roi +
                ", averageBetValue=" + averageBetValue +
                ", maxBetValue=" + maxBetValue +
                ", minBetValue=" + minBetValue +
                ", averageBetCoefficient=" + averageBetCoefficient +
                ", turnoverPercent=" + turnoverPercent +
                ", turnoverValue=" + turnoverValue +
                '}';
    }
}
