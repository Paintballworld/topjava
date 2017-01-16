package ru.javawebinar.topjava.to;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;

/**
 * Created by yerlan on 16/01/17.
 */
public class UserWMeal extends User {

    public UserWMeal(User u, Collection<Meal> meal) {
        super(u);
        this.meals = meal;
    }

    public User extractUser(){
        return new User(getId(), getName(), getEmail(), getPassword(), getCaloriesPerDay(), isEnabled(), getRoles());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString()).append("\n");
        for (Meal meal : meals) {
            result.append("\t" + meal + "\n");
        }
        return result.toString();
    }

    private Collection<Meal> meals;

    public Collection<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Collection<Meal> meal) {
        this.meals = meal;
    }
}
