package ru.betanalysis.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.betanalysis.model.Bet;
import ru.betanalysis.service.AbstractBetServiceTest;
import ru.betanalysis.util.exception.NotFoundException;
import ru.betanalysis.web.user.UserTestData;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.betanalysis.Profiles.DATAJPA;
import static ru.betanalysis.web.user.BetTestData.*;
import static ru.betanalysis.web.user.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaBetServiceTest extends AbstractBetServiceTest {

    @Test
    public void testGetWithUser() throws Exception {
        Bet adminBet = service.getWithUser(ADMIN_BET_ID, ADMIN_ID);
        assertMatch(adminBet, ADMIN_BET1);
        UserTestData.assertMatch(adminBet.getUser(), UserTestData.ADMIN);
    }

    @Test
    public void testGetWithUserNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.getWithUser(BET1_ID, ADMIN_ID));
    }
}
