package br.com.ramir.users.repo;

import br.com.ramir.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

    User findByLoginAndPassword(String login, String password);

    List<User> findByAdmin(Boolean admin);

    User findByLogin(String username);
}
