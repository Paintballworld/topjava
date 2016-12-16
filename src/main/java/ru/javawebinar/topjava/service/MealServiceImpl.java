package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException {
        if (!repository.delete(userId, id)) {
            throw new NotFoundException("Attempt to DELETE meal that is not belong to user#" + userId + " or doesn't exist");
        }
    }

    @Override
    public Meal get(int userId, int id) throws NotFoundException {
        Meal result = repository.get(userId, id);
        if (result == null) {
            throw new NotFoundException("Attempt to GET meal that is not belong to user#" + userId + " or doesn't exist");
        }
        return result;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public void update(int userId, Meal meal) throws NotFoundException{
        if (repository.get(userId, meal.getId()) == null)
            throw new NotFoundException("Attempt to UPDATE meal that is not belong to user#" + userId + " or doesn't exist");
        meal.setUserId(userId);
        repository.save(userId, meal);
    }
}
