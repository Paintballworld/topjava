package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ + 1;

    public static final LocalDate FROM_DATE = LocalDate.of(2010, Month.JANUARY, 1);
    public static final LocalDate TO_DATE = LocalDate.of(2017, Month.JANUARY, 1);

    public static final LocalDateTime FROM_TIME = LocalDateTime.of(FROM_DATE, LocalTime.MIN);
    public static final LocalDateTime TO_TIME = LocalDateTime.of(TO_DATE, LocalTime.MAX);

    public static final ModelMatcher<Meal> MATCHER_DEFAULT = new ModelMatcher<>();
    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (expected.toString().equalsIgnoreCase(actual.toString()))
    );

    public static final Collection<Meal> MEAL_COLLECTION = Arrays.asList(
            new Meal(100004, LocalDateTime.of(2016, 12, 24, 18, 00, 00), "Ужин",500),
            new Meal(100003, LocalDateTime.of(2016, 12, 24, 12, 00, 00), "Обед для админа", 750),
            new Meal(100002, LocalDateTime.of(2016, 12, 24, 06, 00, 00), "Завтрак для админа", 500)
    );

    public static Meal getTestMeal() {
        return new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Тестовая еда", 3000);
    }



}
