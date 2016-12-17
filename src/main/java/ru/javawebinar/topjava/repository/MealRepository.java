package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    Meal save(int userId, Meal Meal);

    // false if not found
    boolean delete(int userID, int id);

    // null if not found
    Meal get(int userID, int id);

    List<Meal> getAll(int userId);

    List<Meal> getBetweenDates(int userId, LocalDate startDate, LocalDate endDate);
}
