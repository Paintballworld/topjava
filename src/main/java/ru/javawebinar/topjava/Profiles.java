package ru.javawebinar.topjava;

import org.springframework.util.ClassUtils;

public class Profiles {
    public static final String
            POSTGRES = "postgres",
            HSQLDB = "hsqldb",
            JDBC = "jdbc",
            JPA = "jpa",
            DATAJPA = "datajpa",
            TOMCAT = "tomcat";

    public static final String DB_IMPLEMENTATION = DATAJPA;
    private static final String DEFAULT_PROFILE = TOMCAT;

    private static final boolean forceDbProfileOverride = false;

    public static String getActiveDbProfile() {
        if (forceDbProfileOverride) return DEFAULT_PROFILE;
        try {
            Class.forName("org.postgresql.Driver", true, ClassUtils.getDefaultClassLoader());
            return Profiles.POSTGRES;
        } catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.hsqldb.jdbcDriver", true, ClassUtils.getDefaultClassLoader());
                return Profiles.HSQLDB;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Could not resolve DB profile");
            }
        }
    }
}