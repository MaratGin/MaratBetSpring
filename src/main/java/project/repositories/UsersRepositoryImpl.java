package project.repositories;

import project.models.Auth;
import project.models.User;
import project.models.UserBet;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Repository()
public class UsersRepositoryImpl implements UsersRepository{

    //language=SQL
    private final String SQL_SHOW_ALL = "select * from users";
    private final String DELETE_EVERYTHING = "delete from users";
    private final String SQL_GET_ROW ="select * from users where login=?";
    private final String DELETE_ROW = "delete from users where id = ?";
    private final String SQL_INSERT_USER = "INSERT INTO users(login, password, email,registration, balance) VALUES (?, ?, ?, now(), 1000)";
    private final String SQL_SHOW_ID_USER = "select id, login from users";
    private final String SQL_FIND_USER_BY_LOGIN = "select * from users where login=?";
    private final String SQL_FIND_USER_BY_ID = "select * from users where id=?";
    private final String SQL_UPDATE_BALANCE = "update users set balance = ? where id = ?";
    private final String SQL_FIND_USER_ID_BY_COOKIE = "select user_id from auth where cookie_value = ?";
    private final String SQL_GET_USER_BY_ID = "select * from users where id = ?";
    private final String SQL_GET_COUNT = "select count(*) from users";
    private final String SQL_UPLOAD_BET = "insert into user_bet(user_id, match_id, outcome, value) VALUES (?,?,?,?);";
    private static final String SQL_INSERT_ALL = "insert into users(login, password, email,registration, balance) values (:login,:passwordHash,:email,:registrationDate,:balance)";
    private static final String SQL_DELETE_ALL = "delete from users where id = :id";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    //    public UsersRepositoryImpl(Connection connection) {
//        this.connection = connection;
//    }
    private RowMapper<User> userRowMapper = ((resultSet, rowNum) -> {
        return User.builder()
                .id(resultSet.getLong("id"))
                .login(resultSet.getString("login"))
                .email(resultSet.getString("email"))
                .registrationDate(resultSet.getString("registration"))
                .balance(resultSet.getDouble("balance"))
                .build();
    });
    private RowMapper<Auth> cookieRowMapper = ((resultSet, rowNum) -> {
        return Auth.builder()
                .id(resultSet.getLong("user_id"))
                .cookieValue(resultSet.getString("cookie_value"))
                .user(findById(resultSet.getLong("user_id")).get())
                .build();
    });

    public UsersRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByLogin(String login) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, userRowMapper, login);
        } catch (DataAccessException ex) {
            return null;
        }

        return user;    }

    @Override
    public User findByCookie(String cookie) {
        Auth auth;
        try {
            auth = jdbcTemplate.queryForObject(SQL_FIND_USER_ID_BY_COOKIE, cookieRowMapper, cookie);
        } catch (DataAccessException ex) {
            return null;
        }

        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, userRowMapper, auth.getId());
        } catch (DataAccessException ex) {
            return null;
        }
        return user;
    }

    @Override
    public UserBet uploadBet(UserBet userBet) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("user_id", userBet.getUserId());
        in.addValue("match_id", userBet.getMatchId());
        in.addValue("outcome",userBet.getOutcome());
        in.addValue("k",userBet.getValue());
        String SQL_UPLOAD_BET = "insert into user_bet(user_id, match_id, outcome, value) VALUES (:user_id,:match_id,:outcome,:k);";

        jdbcTemplate.update(SQL_UPLOAD_BET, in);

        //MARK - Check!!!
        User user =  findById((long) userBet.getUserId()).get();
        MapSqlParameterSource userIn = new MapSqlParameterSource();
        in.addValue("balance", user.getBalance() - userBet.getValue());
        in.addValue("user_id", userBet.getUserId());
        String SQL_UPDATE_BALANCE = "update users set balance = ? where id = ?";
        jdbcTemplate.update(SQL_UPDATE_BALANCE, userIn);

        return userBet;
    }

    @Override
    public <S extends User> S save(S user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER, new String[]{"id"});
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getPasswordHash());
            statement.setString(3,user.getEmail());
            return statement;
        },keyHolder);

        //MARK - check
        user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return user;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> users) {
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(userList.toArray());
        namedParameterJdbcTemplate.batchUpdate(SQL_INSERT_ALL, batch);
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, userRowMapper,aLong);
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public boolean existsById(Long aLong) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, userRowMapper,aLong);
        } catch (DataAccessException ex) {
//            return Optional.empty();
        }
        if (Optional.of(user).isPresent() ) {
            return true;
        }
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        User user = new User();
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        return jdbcTemplate.query(SQL_SHOW_ALL, userRowMapper);

//        try {
//
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SHOW_ALL, Statement.RETURN_GENERATED_KEYS);
//            resultSet =  preparedStatement.executeQuery();
//            while (resultSet.next()){
//                user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setLogin(resultSet.getString("login"));
//                user.setPasswordHash(resultSet.getString("password"));
//                user.setEmail(resultSet.getString("email"));
//                user.setRegistrationDate(resultSet.getString("registration"));
//                user.setBalance(resultSet.getDouble("balance"));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return users;
//        return null;

    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        Long count  = jdbcTemplate.queryForObject(SQL_GET_COUNT, long.class);
        return count;
    }

    @Override
    public void deleteById(Long aLong) {
        jdbcTemplate.update(DELETE_ROW, aLong);
    }

    @Override
    public void delete(User entity) {
        jdbcTemplate.update(DELETE_ROW, entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> users) {

        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(userList.toArray());
        namedParameterJdbcTemplate.batchUpdate(SQL_DELETE_ALL, batch);
    }

    @Override
    public void deleteAll() {

        jdbcTemplate.update(DELETE_EVERYTHING);
    }
}