package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.Month;
import java.util.Objects;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * Created by yerlan on 28/01/17.
 */
public class MealWithExceedTestData {

    private static final int MEAL1_ID = START_SEQ + 2;

    public static final MealWithExceed MEAL_WE_1 = new MealWithExceed(MEAL1_ID, of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, false);
    public static final MealWithExceed MEAL_WE_2 = new MealWithExceed(MEAL1_ID + 1, of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, false);
    public static final MealWithExceed MEAL_WE_3 = new MealWithExceed(MEAL1_ID + 2, of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, false);
    public static final MealWithExceed MEAL_WE_4 = new MealWithExceed(MEAL1_ID + 3, of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500, true);
    public static final MealWithExceed MEAL_WE_5 = new MealWithExceed(MEAL1_ID + 4, of(2015, Month.MAY, 31, 13, 0), "Обед", 1000, true);
    public static final MealWithExceed MEAL_WE_6 = new MealWithExceed(MEAL1_ID + 5, of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, true);

    public static final ModelMatcher<MealWithExceed> MATCHER_W_EXCEED = ModelMatcher.of(MealWithExceed.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())));

}
