package ru.javawebinar.topjava.data;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yerlan on 12/12/16.
 */
public class Data {

    private static Map<Long, Meal> allMeals = new ConcurrentHashMap<>();

    private static final AtomicLong counter = new AtomicLong(0);

    static
    {
        // filling our Data with some initial values:
        put(getNewMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        put(getNewMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        put(getNewMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        put(getNewMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        put(getNewMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        put(getNewMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));

    }

    public static Meal getNewMeal(LocalDateTime dateTime, String description, int calories) {
        Meal temp = new Meal(counter.getAndIncrement(), dateTime, description,calories);
        return temp;
    }

    public static List<Meal> getAllMeals() {
        return new ArrayList<>(allMeals.values());
    }

    public static void put(Meal meal) {

        allMeals.put(meal.getId(), meal);
    }

    public static Meal get(long id) {
        Meal result = allMeals.get(id);
        return result;
    }

    public static void remove(long id) {
        if (allMeals.containsKey(id)) {
            allMeals.remove(id);
        }
    }

    public static void update(long id, Meal meal) {
        Meal temp = new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories());
        allMeals.put(id, temp);
    }


    public static void main(String[] args) {
        System.out.println(allMeals.size());
        getAllMeals().stream().forEach(System.out::println);
    }
}
