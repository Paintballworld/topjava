package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

/**
 * Created by yerlan on 12/16/16.
 */
public abstract class AbstractMealController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    @Autowired
    private User authorizedUser;

//    public List<Meal> getAll() {
//        LOG.info("getAll");
//        return service.getAll();
//    }

    public Meal get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public Meal create(Meal meal) {
        meal.setId(null);
        meal.setUserID(authorizedUser.getId());
        LOG.info("create " + meal);
        return service.save(meal);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(Meal meal, int id) {
        meal.setId(id);
        LOG.info("update " + meal);
        service.update(meal);
    }

    public List<Meal> getAll() {
        LOG.info("getByUser" + authorizedUser.getId());
        return service.getByUser(authorizedUser);
    }


}
