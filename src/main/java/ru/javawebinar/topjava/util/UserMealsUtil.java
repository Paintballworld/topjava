package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000)
        .stream()
        .forEach(System.out::println);
//        .toLocalDate();
//        .toLocalTime();


    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {


        List<UserMealWithExceed> result = new ArrayList<>();

        Map<LocalDate, Integer> preResult =
                mealList
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getDateTime().toLocalDate(),
                        p -> p.getCalories(),
                        (cal1, cal2) -> cal1 + cal2));

//        preResult.forEach((day, list) -> {
//            final boolean exceedes = list.stream().mapToInt(UserMeal::getCalories).sum() > caloriesPerDay;
//            list.stream().filter(p -> TimeUtil.isBetween(p.getDateTime().toLocalTime(), startTime, endTime))
//                .forEach(e -> {
//                    result.add(new UserMealWithExceed(e.getDateTime(), e.getDescription(), e.getCalories(), exceedes));
//                    }
//                );
//        });
        result = mealList
                .stream()
                .filter(p -> TimeUtil.isBetween(p.getDateTime().toLocalTime(), startTime, endTime))
                .map( (p) -> new UserMealWithExceed(p.getDateTime(), p.getDescription(), p.getCalories(),
                        (preResult.get(p.getDateTime().toLocalDate()) > caloriesPerDay)))
                .collect(Collectors.toList());

        return result;
    }
}
