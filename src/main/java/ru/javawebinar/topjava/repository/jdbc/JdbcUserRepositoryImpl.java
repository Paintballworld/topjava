package ru.javawebinar.topjava.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserRepositoryImpl implements UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcUserRepositoryImpl.class);

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("registered", user.getRegistered())
                .addValue("enabled", user.isEnabled())
                .addValue("caloriesPerDay", user.getCaloriesPerDay());

        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            user.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE users SET name=:name, email=:email, password=:password, " +
                            "registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id", map);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        Set<Role> extractedRoles = new CopyOnWriteArraySet<>();
        List<User> users = jdbcTemplate.query("SELECT * FROM users LEFT JOIN user_roles ON (user_id=?) WHERE id=?", (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRegistered(rs.getTimestamp("registered"));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setCaloriesPerDay(rs.getInt("calories_per_day"));

                Role role = Role.valueOf(rs.getString("role"));
                extractedRoles.add(role);
                return user;
            }, id, id);
        if (users.isEmpty())
            return null;
        User result = users.get(0);
        result.setRoles(extractedRoles);
        return result;
    }

    @Override
    public User getByEmail(String email) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll()
    {
        Map<Integer, Set<Role>> rolesMap = new ConcurrentHashMap<>();
        List<Object> ignore = jdbcTemplate.query("SELECT * FROM user_roles", (rs, rowNum) -> {
            int id = rs.getInt("user_id");
            Role role = Role.valueOf(rs.getString("role"));
            if (!rolesMap.containsKey(id))
                rolesMap.put(id, new CopyOnWriteArraySet<>());
            rolesMap.get(id).add(role);
            LOG.info("Relation {} - {} mapped", id, role);
            return null;
        });
        List<User> users = jdbcTemplate.query("SELECT * FROM users ORDER BY name, email", ROW_MAPPER);
        users.forEach(u -> {
            int id = u.getId();
            if (rolesMap.containsKey(id)) {
                u.setRoles(rolesMap.get(id));
                LOG.info("Set of {} roles added to User {}", rolesMap.get(id).size(), u);
            }
        });
        return users;
    }
}
