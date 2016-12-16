package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class Meal extends NamedEntity{

    private int userId;

    private final LocalDateTime dateTime;

    private final int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, null, dateTime, description, calories);
    }

    public Meal(Integer userId, Integer id, LocalDateTime dateTime, String description, int calories) {
        this.userId = userId;
        this.id = id;
        this.dateTime = dateTime;
        this.name = description;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserID(int userID) { this.userId = userID; }

    public int getUserId() { return userId; }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
