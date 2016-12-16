package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

/**
 * Created by yerlan on 12/16/16.
 */
public abstract class AbstractMealContoller {

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

//    public User getByMail(String email) {
//        LOG.info("getByEmail " + email);
//        return service.getByEmail(email);
//    }

}
