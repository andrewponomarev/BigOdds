package ru.betanalysis.service.datajpa;


import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.betanalysis.Profiles;
import ru.betanalysis.model.User;
import ru.betanalysis.service.AbstractJpaUserServiceTest;
import ru.betanalysis.util.exception.NotFoundException;
import ru.betanalysis.web.user.BetTestData;

import static ru.betanalysis.web.user.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {

    @Test
    public void testGetWithBets() throws Exception {
        User user = service.getWithBets(USER_ID);
        assertMatch(user, USER);
        BetTestData.assertMatch(user.getBets(), BetTestData.BETS);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithBetsNotFound() throws Exception {
        service.getWithBets(1);
    }

}
