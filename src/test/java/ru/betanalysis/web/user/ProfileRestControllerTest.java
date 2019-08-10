package ru.betanalysis.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.betanalysis.model.Role;
import ru.betanalysis.model.User;
import ru.betanalysis.to.UserTo;
import ru.betanalysis.util.UserUtil;
import ru.betanalysis.web.AbstractControllerTest;
import ru.betanalysis.web.json.JsonUtil;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.betanalysis.web.user.ProfileRestController.REST_URL;
import static ru.betanalysis.web.user.UserTestData.*;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN);
    }

    @Test
    void testUpdate() throws Exception {
        User updated = new User(USER_ID, "user", "email@mail.com", "password", "secondName",
                "firstName", "phoneNumber", new Date(), Role.ROLE_USER);

        UserTo updatedTo = new UserTo(USER_ID, "user","email@mail.com", "password");
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(userService.getByEmail("email@mail.com"), UserUtil.updateFromTo(new User(USER), updatedTo));
    }
}
