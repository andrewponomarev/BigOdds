package ru.betanalysis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.betanalysis.Profiles;
import ru.betanalysis.model.Role;
import ru.betanalysis.model.User;
import ru.betanalysis.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.betanalysis.web.user.UserTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(Profiles.ACTIVE_DB)
public class UserServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        //SLF4JBridgeHandler.install();
    }

    @Autowired
    private UserService service;

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "user", "email2@mail.ru", "password",
                "secondName", "firstName", "phoneNumber",
                LocalDateTime.now(), Role.ROLE_USER);
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(newUser, created);
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception {
        service.create(new User(null, "user", "email@mail.com", "password",
                "secondName", "firstName", "phoneNumber",
                LocalDateTime.now(), Role.ROLE_USER));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("email@mail.com");
        assertMatch(user, USER);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        service.update(new User(updated));
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }
}