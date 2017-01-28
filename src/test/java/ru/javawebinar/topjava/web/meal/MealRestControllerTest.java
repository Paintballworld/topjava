package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by yerlan on 28/01/17.
 */
public class MealRestControllerTest extends AbstractControllerTest{

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/rest/meals/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/rest/meals.jsp"))
                .andExpect(model().attribute("mealWithExceedList", hasSize(6)))
                .andExpect(MEAL1_RESULT_MATCHER)
                .andExpect(MEAL2_RESULT_MATHCER)
                .andExpect(MEAL3_RESULT_MATHCER)
                .andExpect(MEAL4_RESULT_MATHCER)
                .andExpect(MEAL5_RESULT_MATHCER)
                .andExpect(MEAL6_RESULT_MATHCER);
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/rest/meals/100002"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/rest/meals.jsp"))
                .andExpect(model().attribute("mealWithExceedList", hasSize(1)))
                .andExpect(MEAL1_RESULT_MATCHER);
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get("/rest/meals/by"))
                .andDo(print());
    }



}