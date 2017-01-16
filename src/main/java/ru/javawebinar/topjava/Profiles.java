package ru.javawebinar.topjava;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class Profiles {
    public static final String
            POSTGRES = "postgres",
            HSQLDB = "hsqldb",
            JDBC = "jdbc",
            JPA = "jpa",
            DATA_JPA = "datajpa";

    public static final String ACTIVE_DB = HSQLDB;
    public static final String ACTIVE_JPA = DATA_JPA;

    public static final boolean SHOW_JPA = true;
}
