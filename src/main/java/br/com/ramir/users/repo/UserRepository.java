package br.com.ramir.users.repo;

import br.com.ramir.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
    User findByLoginAndPassword(String login, String password);
}
