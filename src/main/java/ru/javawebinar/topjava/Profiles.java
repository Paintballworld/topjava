package ru.javawebinar.topjava;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class Profiles {
    public static final String
            POSTGRES = "postgres_local",
            POSTGRES_REMOTE = "postgres_remote",
            HSQLDB = "hsqldb",
            JDBC = "jdbc",
            JPA = "jpa",
            DATA_JPA = "datajpa";

    public static final String ACTIVE_DB = POSTGRES_REMOTE;
    public static final String ACTIVE_REPO = DATA_JPA;

    public static final boolean SHOW_JPA = true;
}
