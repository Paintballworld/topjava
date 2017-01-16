package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;

import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;


/**
 * Created by yerlan on 14/01/17.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.DATA_JPA})
public class DataJPAServiceTest extends AllServicesTest {

    @Test
    public void testGetMealWUSer() {
        Meal mealWUser = mealService.getMealWithUser(MEAL1_ID);
        LOG.info("Got meal by id {}, user is {}", MEAL1_ID, mealWUser.getUser());
    }
}
