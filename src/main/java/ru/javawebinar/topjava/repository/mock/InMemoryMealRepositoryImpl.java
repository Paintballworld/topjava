package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.KotinSikariException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    // Integer Key shows Id of user, whose meal is operated (ex. stored)
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::saveForNullUser);
    }

    private void saveForNullUser(Meal meal) {
        save(0, meal);
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.getAndIncrement());
            meal.setUserId(userId);
        }
        Map<Integer, Meal> tempList = new ConcurrentHashMap<>();
        if (repository.containsKey(userId)) {
            tempList = repository.get(userId);
        }
        tempList.put(meal.getId(), meal);
        repository.put(userId, tempList);
        return meal;
    }

    @Override
    public boolean delete(int userId, int id) {
        if (!repository.containsKey(userId)
                || !repository.get(userId).containsKey(id))
            return false;
        repository.get(userId).remove(id);
        return true;
    }

    @Override
    public Meal get(int userId, int id) {
        if (!repository.containsKey(userId)) return null;
        if (!repository.get(userId).containsKey(id)) return null;
        return repository.get(userId).get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        if (repository.get(userId) == null || repository.get(userId).isEmpty()) {
            throw new KotinSikariException("Empty list requery");
        }
        return repository
                .get(userId)
                .values()
                .stream()
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());
    }
}

