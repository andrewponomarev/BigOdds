package ru.betanalysis.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.betanalysis.Profiles;
import ru.betanalysis.service.AbstractUserServiceTest;

@ActiveProfiles(Profiles.JDBC)
    public class JdbcUserServiceTest extends AbstractUserServiceTest {
}
