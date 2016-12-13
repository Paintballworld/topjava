package ru.javawebinar.topjava.data;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yerlan on 12/12/16.
 */
public class Data {

    private static CopyOnWriteArrayList<Meal> allMeals = new CopyOnWriteArrayList<>();

    private static final AtomicLong counter = new AtomicLong(0);

    public static List<Meal> getAllMeals() {
        return allMeals;
    }

    public static void put(LocalDateTime dateTime, String description, int calories) {
        Meal temp = new Meal(counter.getAndIncrement(), dateTime, description,calories);
    }

    public static Meal getById(long id) {
        for (Meal each : allMeals) {
            if (each.getId() == id)
                return each;
        }
        return null;
    }

    public static void remove(long id) {
        Meal toRemove = getById(id);
        if (toRemove != null)
            allMeals.remove(toRemove);
    }

    public static void update(long id, LocalDateTime dateTime, String description, int calories) {
        remove(id);
        Meal temp = new Meal(counter.getAndIncrement(), dateTime, description,calories);
    }


}
