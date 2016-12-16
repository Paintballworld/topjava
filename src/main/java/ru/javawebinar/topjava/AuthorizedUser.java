package ru.javawebinar.topjava;

import ru.javawebinar.topjava.util.MealsUtil;

/**
 * GKislin
 * 06.03.2015.
 */
public class AuthorizedUser {

    public void setId(int newId) {
        id = newId;
    }

    private static int id = 0;

    public static int id() {
        return id;
    }

    public static int getCaloriesPerDay() {
        return MealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
