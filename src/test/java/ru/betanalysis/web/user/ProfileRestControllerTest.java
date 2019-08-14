package ru.betanalysis.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.betanalysis.model.Role;
import ru.betanalysis.model.User;
import ru.betanalysis.to.UserTo;
import ru.betanalysis.util.UserUtil;
import ru.betanalysis.web.AbstractControllerTest;
import ru.betanalysis.web.json.JsonUtil;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.betanalysis.util.exception.ErrorType.VALIDATION_ERROR;
import static ru.betanalysis.web.TestUtil.readFromJson;
import static ru.betanalysis.web.TestUtil.userHttpBasic;
import static ru.betanalysis.web.user.ProfileRestController.REST_URL;
import static ru.betanalysis.web.user.UserTestData.*;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER));
    }

    @Test
    void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN);
    }

    @Test
    void testUpdate() throws Exception {
        User updated = new User(USER_ID, "user", "email@mail.com", "password", "secondName",
                "firstName", "phoneNumber", new Date(), Role.ROLE_USER);

        UserTo updatedTo = new UserTo(USER_ID, "user","email@mail.com", "password");
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(userService.getByEmail("email@mail.com"), UserUtil.updateFromTo(new User(USER), updatedTo));
    }

    @Test
    void testRegister() throws Exception {
        UserTo createdTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword");

        ResultActions action = mockMvc.perform(post(REST_URL + "/register").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdTo)))
                .andDo(print())
                .andExpect(status().isCreated());
        User returned = readFromJson(action, User.class);

        User created = UserUtil.createNewFromTo(createdTo);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(userService.getByEmail("newemail@ya.ru"), created);
    }

    @Test
    void testUpdateInvalid() throws Exception {
        UserTo updatedTo = new UserTo(null, null, null, "newPassword");

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.type").value(VALIDATION_ERROR.name()))
                .andDo(print());
    }

//    @Test
//    @Transactional(propagation = Propagation.NEVER)
//    void testDuplicate() throws Exception {
//        UserTo updatedTo = new UserTo(null, "newName", "admin@mail.com", "newPassword");
//
//        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(USER))
//                .content(JsonUtil.writeValue(updatedTo)))
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(errorType(VALIDATION_ERROR))
//                .andExpect(detailMessage(EXCEPTION_DUPLICATE_EMAIL))
//                .andDo(print());
//    }
}
