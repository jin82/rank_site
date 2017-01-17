package info.epochpro.repository;

import info.epochpro.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jin on 2016/12/11.
 */
public interface UserRepository extends CrudRepository<User,Long> {

    User findByName(String name);

    User findByNameAndPassword(String name, String password);
}
