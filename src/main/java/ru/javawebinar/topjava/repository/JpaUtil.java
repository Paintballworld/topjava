package ru.javawebinar.topjava.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JpaUtil.class);

    private static int logCounter = 0;

    @PersistenceContext
    private EntityManager em;

    public void clear2ndLevelHibernateCache() {
        Session s = (Session) em.getDelegate();
        SessionFactory sf = s.getSessionFactory();
//        sf.getCache().evict(User.class);
//        sf.getCache().evictEntity(User.class, BaseEntity.START_SEQ);
//        sf.getCache().evictEntityRegion(User.class);
        sf.getCache().evictQueryRegions();
        sf.getCache().evictDefaultQueryRegion();
        sf.getCache().evictCollectionRegions();
        sf.getCache().evictEntityRegions();
        LOG.warn("\n\n\n------------------------ Evicting 2 level hibernate cache for {} time --------------------\n\n\n", ++logCounter);

    }
}
