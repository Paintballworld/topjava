package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public void setService(MealService service) {
        this.service = service;
    }

    public List<MealWithExceed> getAllWithExceeded() {
        LOG.info("getAllWithExceeded for user" + AuthorizedUser.id() + " calories per day:" + AuthorizedUser.getCaloriesPerDay());
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay());
    }

    public List<MealWithExceed> getFilteredWithExceeded(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        if (startDate == null) startDate = LocalDate.MIN;
        if (endDate == null) endDate = LocalDate.now();
            /* в случае, если дата конца фильтра не задана, ищем по сей день */
        if (startTime == null) startTime = LocalTime.MIN;
        if (endTime == null) endTime = LocalTime.MAX;
        LOG.info("getWith for user" + AuthorizedUser.id() + " between Dates :" + startDate + "-" + endDate
                    + " and Between time:" + startTime + " - " + endTime);
        List<Meal> temp = service.getBetweenDates(AuthorizedUser.id(), startDate, endDate);
        return MealsUtil.getFilteredWithExceeded(temp, startTime, endTime, AuthorizedUser.getCaloriesPerDay());
    }

    public List<Meal> getAll() {
        LOG.info("getAll for user" + AuthorizedUser.id());
        return service.getAll(AuthorizedUser.id());
    }

    public Meal get(int id) {
        LOG.info("get " + id + " for user" + AuthorizedUser.id());
        return service.get(AuthorizedUser.id(), id);
    }

    public Meal create(Meal meal) {
        meal.setId(null);
        LOG.info("create " + meal);
        return service.save(AuthorizedUser.id(), meal);
    }

    public void delete(int id) {
        LOG.info("delete " + id + " for user" + AuthorizedUser.id());
        service.delete(AuthorizedUser.id(), id);
    }

    public void update(Meal meal, int id) {
        meal.setId(id);
        LOG.info("update " + meal + "  for user" + AuthorizedUser.id());
        service.update(AuthorizedUser.id(), meal);
    }

}
