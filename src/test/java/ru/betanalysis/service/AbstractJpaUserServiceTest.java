package ru.betanalysis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.betanalysis.model.Role;
import ru.betanalysis.model.User;
import ru.betanalysis.repository.JpaUtil;

import javax.validation.ConstraintViolationException;
import java.util.Date;

abstract public class AbstractJpaUserServiceTest extends AbstractUserServiceTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected JpaUtil jpaUtil;

    @BeforeEach
    void setUp() throws Exception {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void testValidation() throws Exception {
        validateRootCause(() -> service.create(
                new User(10000000, null, "email@m.com", "password",
                        "secondName", "firstName", "phoneNumber",
                        new Date(), Role.ROLE_USER)),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(
                new User(10000000, "n", "email@m.com", "password",
                        "secondName", "firstName", "phoneNumber",
                        new Date(), Role.ROLE_USER)),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(
                new User(10000000, "name", "  ", "password",
                        "secondName", "firstName", "phoneNumber",
                        new Date(), Role.ROLE_USER)),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(
                new User(10000000, "name", "email@m.com", "",
                        "secondName", "firstName", "phoneNumber",
                        new Date(), Role.ROLE_USER)),
                ConstraintViolationException.class);
    }
}
