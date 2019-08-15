package ru.betanalysis.service.datajpa;


import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.betanalysis.Profiles;
import ru.betanalysis.model.User;
import ru.betanalysis.service.AbstractJpaUserServiceTest;
import ru.betanalysis.util.exception.NotFoundException;
import ru.betanalysis.web.user.BetTestData;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.betanalysis.web.user.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {

    @Test
    void testGetWithBets() throws Exception {
        User user = service.getWithBets(USER_ID);
        assertMatch(user, USER);
        BetTestData.assertMatch(user.getBets(), BetTestData.BETS);
    }

    @Test
    void testGetWithBetsNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.getWithBets(1));
    }

}
