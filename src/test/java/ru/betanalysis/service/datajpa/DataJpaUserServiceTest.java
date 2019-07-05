package ru.betanalysis.service.datajpa;


import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.betanalysis.Profiles;
import ru.betanalysis.model.User;
import ru.betanalysis.service.AbstractUserServiceTest;
import ru.betanalysis.util.exception.NotFoundException;
import ru.betanalysis.web.user.BetTestData;

import static ru.betanalysis.web.user.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void testGetWithMeals() throws Exception {
        User user = service.getWithMeals(USER_ID);
        assertMatch(user, USER);
        BetTestData.assertMatch(user.getBets(), BetTestData.BETS);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithMealsNotFound() throws Exception {
        service.getWithMeals(1);
    }

}
