package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {

    Meal save(Meal Meal);

    void delete(int id);

    Meal get(int id);

//    List<Meal> getAll();

    List<Meal> getByUserId(int userId);
}
