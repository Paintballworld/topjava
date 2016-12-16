package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    private int authorizedUserId = AuthorizedUser.id();

    public List<Meal> getAll() {
        LOG.info("getAll");
        return service.getAll(authorizedUserId);
    }

    public Meal get(int id) {
        LOG.info("get " + id);
        return service.get(authorizedUserId, id);
    }

    public Meal create(Meal meal) {
        meal.setId(null);
        LOG.info("create " + meal);
        return service.save(authorizedUserId, meal);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(authorizedUserId, id);
    }

    public void update(Meal meal, int id) {
        meal.setId(id);
        LOG.info("update " + meal);
        service.update(authorizedUserId, meal);
    }

}
