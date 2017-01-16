package ru.javawebinar.topjava.to;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;

/**
 * Created by yerlan on 16/01/17.
 */
public class UserWMeal extends User {


    private Collection<Meal> meal;

    public Collection<Meal> getMeal() {
        return meal;
    }

    public void setMeal(Collection<Meal> meal) {
        this.meal = meal;
    }
}
