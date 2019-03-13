package ru.betanalysis.model;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Спортивное событие
 */
public class SportEvent {

    /**
     * Список участников события
     */
    private final Set<Team> teamSet;

    /**
     * Вид спорта
     */
    private final SportType sportType;

    /**
     * Турнир
     */
    private final Tournament tournament;

    /**
     * Время начала события
     */
    private final LocalDateTime dateTime;

    public SportEvent(Set<Team> teamSet, SportType sportType, Tournament tournament, LocalDateTime dateTime) {
        this.teamSet = teamSet;
        this.sportType = sportType;
        this.tournament = tournament;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "SportEvent{" +
                "teamSet=" + teamSet +
                ", sportType=" + sportType +
                ", tournament=" + tournament +
                ", dateTime=" + dateTime +
                '}';
    }
}
