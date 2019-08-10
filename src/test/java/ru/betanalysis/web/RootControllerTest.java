package ru.betanalysis.web;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RootControllerTest extends AbstractControllerTest {

    @Test
    void testUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
    }

    @Test
    void testBets() throws Exception {
        mockMvc.perform(get("/bets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("bets"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/bets.jsp"));
    }
}
