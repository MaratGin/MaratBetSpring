package project.repositories;

import project.models.User;
import project.models.UserBet;
import org.springframework.data.repository.CrudRepository;

public  interface UsersRepository extends CrudRepository<User,Long> {
    User findByLogin(String login);
    User findByCookie(String cookie);
    UserBet uploadBet(UserBet userBet);

}