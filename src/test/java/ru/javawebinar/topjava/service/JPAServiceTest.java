package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by yerlan on 14/01/17.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JPA})
public class JPAServiceTest extends AllServicesTest{
}
