package ru.betanalysis.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.betanalysis.ActiveDbProfileResolver;
import ru.betanalysis.Profiles;
import ru.betanalysis.TimingExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.betanalysis.util.ValidationUtil.getRootCause;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@ExtendWith(TimingExtension.class)
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
abstract class AbstractServiceTest {

    @Autowired
    protected Environment env;

    static {
        // needed only for java.util.logging (postgres driver)
        //SLF4JBridgeHandler.install();
    }

    boolean isJpaBased() {
//        return Arrays.stream(env.getActiveProfiles()).noneMatch(Profiles.JDBC::equals);
        return env.acceptsProfiles(org.springframework.core.env.Profiles.of(Profiles.JPA, Profiles.DATAJPA));
    }

    //  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
    <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> exceptionClass) {
        assertThrows(exceptionClass, () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw getRootCause(e);
            }
        });
    }
}
