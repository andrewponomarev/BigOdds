package ru.betanalysis.web.bet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.betanalysis.model.Bet;
import ru.betanalysis.service.BetService;
import ru.betanalysis.web.AbstractControllerTest;
import ru.betanalysis.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.betanalysis.model.AbstractBaseEntity.START_SEQ;
import static ru.betanalysis.web.TestUtil.*;
import static ru.betanalysis.web.user.BetTestData.*;
import static ru.betanalysis.web.user.UserTestData.ADMIN;
import static ru.betanalysis.web.user.UserTestData.USER;

class BetRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = BetRestController.REST_URL + '/';

    @Autowired
    private BetService service;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + BET1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Bet.class), ADMIN_BET1));
    }

    @Test
    void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + BET1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + BET1_ID))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(START_SEQ), BET3, BET2);
    }

    @Test
    void testUpdate() throws Exception {
        Bet updated = getUpdated();

        mockMvc.perform(put(REST_URL + BET1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());

        assertMatch(service.get(BET1_ID, START_SEQ), updated);
    }

    @Test
    void testCreate() throws Exception {
        Bet created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Bet returned = readFromJson(action, Bet.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(START_SEQ), created, ADMIN_BET2, ADMIN_BET1);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(BETS));
    }


    @Test
    void testFilter() throws Exception {
        mockMvc.perform(get(REST_URL + "filter")
                .param("startDate", "2015-05-30").param("startTime", "07:00")
                .param("endDate", "2015-05-31").param("endTime", "11:00")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(BETS));
    }

    @Test
    void testFilterAll() throws Exception {
        mockMvc.perform(get(REST_URL + "filter?startDate=&endTime=")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(contentJson(BETS));
    }

}