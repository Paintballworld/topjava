package ru.javawebinar.topjava.web;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;
import static ru.javawebinar.topjava.web.RootController.ROOT_URL;

/**
 * GKislin
 * 10.04.2015.
 */
public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get(ROOT_URL + "/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is(USER.getName()))
                        )
                )));
    }

    @Test
    public void testMeals() throws Exception {
        mockMvc.perform(get(ROOT_URL + "/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meals"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("meals", hasSize(6)))
                .andExpect(MEAL1_RESULT_MATCHER)
                .andExpect(MEAL2_RESULT_MATCHER)
                .andExpect(MEAL3_RESULT_MATCHER)
                .andExpect(MEAL4_RESULT_MATCHER)
                .andExpect(MEAL5_RESULT_MATCHER)
                .andExpect(MEAL6_RESULT_MATCHER);
    }
}