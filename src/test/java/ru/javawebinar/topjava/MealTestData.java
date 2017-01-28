package ru.javawebinar.topjava;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.of;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = ModelMatcher.of(Meal.class);

    public static final int MEAL1_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 8;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, of(2015, Month.MAY, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final Meal ADMIN_MEAL1 = new Meal(ADMIN_MEAL_ID, of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal ADMIN_MEAL2 = new Meal(ADMIN_MEAL_ID + 1, of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static final List<Meal> MEALS = Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static Meal getCreated() {
        return new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Созданный ужин", 300);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, MEAL1.getDateTime(), "Обновленный завтрак", 200);
    }

    public static ResultMatcher MEAL1_RESULT_MATCHER = model().attribute("meals", hasItem(
            allOf(
                    hasProperty("id", is(MEAL1.getId())),
                    hasProperty("description", is(MEAL1.getDescription())),
                    hasProperty("calories", is(MEAL1.getCalories())),
                    hasProperty("dateTime", is(MEAL1.getDateTime()))
            )));
    public static ResultMatcher MEAL2_RESULT_MATCHER = model().attribute("meals", hasItem(
            allOf(
                    hasProperty("id", is(MEAL2.getId())),
                    hasProperty("description", is(MEAL2.getDescription())),
                    hasProperty("calories", is(MEAL2.getCalories())),
                    hasProperty("dateTime", is(MEAL2.getDateTime()))
            )));
    public static ResultMatcher MEAL3_RESULT_MATCHER = model().attribute("meals", hasItem(
            allOf(
                    hasProperty("id", is(MEAL3.getId())),
                    hasProperty("description", is(MEAL3.getDescription())),
                    hasProperty("calories", is(MEAL3.getCalories())),
                    hasProperty("dateTime", is(MEAL3.getDateTime()))
            )));
    public static ResultMatcher MEAL4_RESULT_MATCHER = model().attribute("meals", hasItem(
            allOf(
                    hasProperty("id", is(MEAL4.getId())),
                    hasProperty("description", is(MEAL4.getDescription())),
                    hasProperty("calories", is(MEAL4.getCalories())),
                    hasProperty("dateTime", is(MEAL4.getDateTime()))
            )));
    public static ResultMatcher MEAL5_RESULT_MATCHER = model().attribute("meals", hasItem(
            allOf(
                    hasProperty("id", is(MEAL5.getId())),
                    hasProperty("description", is(MEAL5.getDescription())),
                    hasProperty("calories", is(MEAL5.getCalories())),
                    hasProperty("dateTime", is(MEAL5.getDateTime()))
            )));
    public static ResultMatcher MEAL6_RESULT_MATCHER = model().attribute("meals", hasItem(
            allOf(
                    hasProperty("id", is(MEAL6.getId())),
                    hasProperty("description", is(MEAL6.getDescription())),
                    hasProperty("calories", is(MEAL6.getCalories())),
                    hasProperty("dateTime", is(MEAL6.getDateTime()))
            )));


}
