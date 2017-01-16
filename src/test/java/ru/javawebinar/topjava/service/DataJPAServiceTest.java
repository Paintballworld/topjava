package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.UserWMeal;

import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_MODEL_MATCHER;


/**
 * Created by yerlan on 14/01/17.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.DATA_JPA})
public class DataJPAServiceTest extends AllServicesTest {

    @Test
    public void testGetMealWUSer() {
        Meal mealWUser = mealService.getMealWithUser(ADMIN_MEAL1.getId());
        Meal newMeal = ADMIN_MEAL1;
        newMeal.setUser(ADMIN);
        MATCHER.assertEquals(mealWUser, newMeal);
    }

    @Test
    public void testGetUserWMeal() {
        UserWMeal userWMeal = userService.getUserWMeal(ADMIN_ID);
        USER_MODEL_MATCHER.assertEquals(ADMIN, userWMeal.extractUser());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL2, ADMIN_MEAL1), userWMeal.getMeals());
    }
}
