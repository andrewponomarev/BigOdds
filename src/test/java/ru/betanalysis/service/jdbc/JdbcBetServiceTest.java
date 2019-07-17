package ru.betanalysis.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.betanalysis.service.AbstractBetServiceTest;

import static ru.betanalysis.Profiles.JDBC;

@ActiveProfiles(JDBC)
class JdbcBetServiceTest extends AbstractBetServiceTest {
}
