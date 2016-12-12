package ru.javawebinar.topjava.data;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by me on 12/12/16.
 */
public interface MealData {

    List<Meal> getAllMeals();

    Meal getById(long id);

    void deleteById(long id);

    void updateById(long id);

    Meal add(LocalDateTime dateTime, String description, int calories);
}
