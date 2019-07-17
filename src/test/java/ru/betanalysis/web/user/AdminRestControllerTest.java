package ru.betanalysis.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.betanalysis.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.betanalysis.web.json.JsonUtil.writeIgnoreProps;
import static ru.betanalysis.web.user.UserTestData.ADMIN;
import static ru.betanalysis.web.user.UserTestData.ADMIN_ID;

class AdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(writeIgnoreProps(ADMIN, "registered")));
    }
}