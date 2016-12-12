package ru.javawebinar.topjava.data;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by me on 12/12/16.
 */
public class Data {

    private static CopyOnWriteArrayList<Meal> allData = new CopyOnWriteArrayList<>();

    private static AtomicLong idCounter = new AtomicLong(0);


    public static List<Meal> getAllMeals() {
        return allData;
    }

    public static Meal getById(long id) {
        for (Meal each : allData) {
            if (each.getId() == id)
                return each;
        }
        return null;
    }

    public static void deleteById(long id) {
        for (Meal each : allData) {
            if (each.getId() == id)
                allData.remove(each);
        }
    }

    public static void updateById(long id, LocalDateTime dateTime, String description, int calories) {
        deleteById(id);
        add(dateTime, description, calories);
    }

    public static Meal add(LocalDateTime dateTime, String description, int calories) {
        Meal temp = new Meal(idCounter.getAndIncrement(), dateTime, description, calories);
        allData.add(temp);
        return temp;
    }

}
