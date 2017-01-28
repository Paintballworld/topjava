package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.Month;
import java.util.Arrays;

import static java.time.LocalDateTime.of;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.web.MealWithExceedTestData.*;
import static ru.javawebinar.topjava.web.meal.MealRestController.REST_URL;
/**
 * Created by yerlan on 28/01/17.
 */
public class MealRestControllerTest extends AbstractControllerTest{

    @Autowired
    private MealService mealService;

    /*
    Part 1: Testing results must return MealWithExceed Entity
    */


    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + "/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_W_EXCEED.contentListMatcher(MEAL_WE_6, MEAL_WE_5, MEAL_WE_4, MEAL_WE_3, MEAL_WE_2, MEAL_WE_1));
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "/by?startDateTime=2015-05-30T03:15:30&endDateTime=2015-05-30T23:59:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_W_EXCEED.contentListMatcher(MEAL_WE_3, MEAL_WE_2, MEAL_WE_1));
        mockMvc.perform(get(REST_URL + "/by?startDateTime=2015-05-31T03:15:30&endDateTime=2015-05-31T23:59:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_W_EXCEED.contentListMatcher(MEAL_WE_6, MEAL_WE_5, MEAL_WE_4));


    }

    /*
    Part 2: Testing results must return Meal Entity
    */

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get(REST_URL + "/" + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentMatcher(MEAL1));
        mockMvc.perform(get(REST_URL + "/" + MEAL2.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentMatcher(MEAL2));
        mockMvc.perform(get(REST_URL + "/" + MEAL3.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentMatcher(MEAL3));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + "/" + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testPost() throws Exception {
        Meal expected = new Meal(null, of(2017, Month.JANUARY, 1, 15, 0), "Тестовая еда", 5000);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Meal returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(expected, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), mealService.getAll(USER_ID));
    }

    @Test
    public void testPut() throws Exception {
        Meal updated = new Meal(MEAL1);
        updated.setDescription("Тестовая еда (Update)");
        updated.setCalories(5000);
        ResultActions resultActions = mockMvc.perform(put(REST_URL + "/" + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, mealService.get(MEAL1_ID, USER_ID));
    }






}