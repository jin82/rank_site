package info.epochpro.repository;

import info.epochpro.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jin on 2016/12/11.
 */
public interface UserRepository extends MongoRepository<User,String> {

    User findByName(String name);

}
