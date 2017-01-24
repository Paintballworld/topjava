package ru.javawebinar.topjava.repository;

/**
 * Created by yerlan on 22/01/17.
 */
public class JpaUtilMock implements JpaUtil {
    @Override
    public void clear2ndLevelHibernateCache() {
        // Do nothing, cause there is No 2-nd level Hibernate cache in jdbc implementation
    }
}
