package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * GKislin
 * 11.01.2015.
 */
public class MealWithExceed  implements Comparable<MealWithExceed>{

    private final String dateTimeString;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("E, dd.MM.yyyy HH:mm");

    private final long id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public long getId() {
        return id;
    }

    public MealWithExceed(long id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.id = id;

        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.dateTimeString = dateTime.format(DATE_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }

    public String getDateTimeString() {
        return dateTimeString;
    }

    @Override
    public int compareTo(MealWithExceed o) {
        return this.getDateTime().compareTo(o.getDateTime());
    }
}
