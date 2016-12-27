package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by yerlan on 12/27/16.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUo() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = getTestMeal();
        Meal meal = service.save(newMeal, ADMIN_ID);
        newMeal.setId(meal.getId());
        MATCHER.assertEquals(meal, newMeal);
    }

    @Test
    public void testGet() throws Exception {
        Meal testMeal = getTestMeal();
        Meal meal = service.save(testMeal, ADMIN_ID);
        int newId = meal.getId();
        testMeal.setId(newId);
        MATCHER.assertEquals(testMeal, service.get(newId, ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testGetForWrongUserId() throws Exception {
        Meal testMeal = getTestMeal();
        Meal meal = service.save(testMeal, ADMIN_ID);
        int newId = meal.getId();
        Meal test = service.get(newId, 0);
    }

    @Test
    public void testDelete() throws Exception {
        Meal testMeal = getTestMeal();
        Meal meal = service.save(testMeal, ADMIN_ID);
        service.delete(meal.getId(), ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testWrongDelete() throws Exception {
        service.delete(0, 0);
    }


    @Test
    public void testGetBetween() throws Exception {
        Collection<Meal> meals = service.getBetweenDates(FROM_DATE, TO_DATE, ADMIN_ID);
        MATCHER.assertCollectionEquals(meals, MEAL_COLLECTION);
    }


    @Test
    public void testBetweenDateTimes() throws Exception {
        Collection<Meal> meals = service.getBetweenDateTimes(FROM_TIME, TO_TIME, ADMIN_ID);
        MATCHER.assertCollectionEquals(meals, MEAL_COLLECTION);
    }

    @Test
    public void testGetAllQty() throws Exception {
        Collection<Meal> meals = service.getAll(ADMIN_ID);
        MATCHER.assertCollectionEquals(meals, MEAL_COLLECTION);
    }

    @Test
    public void testUpdate() {
        Meal testMeal = getTestMeal();
        int id = service.save(testMeal, ADMIN_ID).getId();
        testMeal.setDescription("Измененной тестовое описание");
        Meal meal = service.update(testMeal, ADMIN_ID);
        MATCHER.assertEquals(meal, testMeal);
    }

    @Test(expected = NotFoundException.class)
    public void testWrongUpdate() {
        Meal testMeal = getTestMeal();
        int id = service.save(testMeal, ADMIN_ID).getId();
        testMeal.setDescription("Измененной тестовое описание");
        Meal meal = service.update(testMeal, USER_ID);
    }

}