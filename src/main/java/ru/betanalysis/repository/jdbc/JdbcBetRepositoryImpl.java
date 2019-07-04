package ru.betanalysis.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.betanalysis.Profiles;
import ru.betanalysis.model.Bet;
import ru.betanalysis.repository.BetRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public abstract class JdbcBetRepositoryImpl<T> implements BetRepository {

    private static final RowMapper<Bet> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Bet.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertBet;

    @Autowired
    public JdbcBetRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertBet = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("bets")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    protected abstract T toDbDateTime(LocalDateTime ldt);

    @Repository
    @Profile(Profiles.POSTGRES_DB)
    public static class Java8JdbcMealRepositoryImpl extends JdbcBetRepositoryImpl<LocalDateTime> {
        public Java8JdbcMealRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            super(jdbcTemplate, namedParameterJdbcTemplate);
        }

        @Override
        protected LocalDateTime toDbDateTime(LocalDateTime ldt) {
            return ldt;
        }
    }

    @Repository
    @Profile(Profiles.HSQL_DB)
    public static class TimestampJdbcMealRepositoryImpl extends JdbcBetRepositoryImpl<Timestamp> {
        public TimestampJdbcMealRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            super(jdbcTemplate, namedParameterJdbcTemplate);
        }

        @Override
        protected Timestamp toDbDateTime(LocalDateTime ldt) {
            return Timestamp.valueOf(ldt);
        }
    }

    @Override
    public Bet save(Bet bet, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", bet.getId())
                .addValue("value", bet.getValue())
                .addValue("date_time", bet.getDateTime())
                .addValue("coefficient", bet.getCoefficient())
                .addValue("currency", bet.getCurrency())
                .addValue("event", bet.getEvent())
                .addValue("net_profit", bet.getNetProfit())
                .addValue("return_sum", bet.getReturnSum())
                .addValue("is_express", bet.isExpress())
                .addValue("user_id", userId);

        if (bet.isNew()) {
            Number newId = insertBet.executeAndReturnKey(map);
            bet.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE bets" +
                            "   SET id=:id, value=:value, date_time=:date_time, " +
                            "coefficient=:coefficient, currency=:currency, event=:event," +
                            "net_profit=:net_profit, return_sum=:return_sum, is_express=:is_express"+
                            " WHERE id=:id AND user_id=:user_id"
                    , map) == 0) {
                return null;
            }
        }
        return bet;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM bets WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Bet get(int id, int userId) {
        List<Bet> bets = jdbcTemplate.query(
                "SELECT * FROM bets WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(bets);
    }

    @Override
    public List<Bet> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM bets WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<Bet> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM bets WHERE user_id=?  AND date_time BETWEEN  ? AND ? ORDER BY date_time DESC",
                ROW_MAPPER, userId, toDbDateTime(startDate), toDbDateTime(endDate));
    }
}
