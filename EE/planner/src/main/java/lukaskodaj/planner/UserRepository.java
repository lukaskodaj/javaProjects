package lukaskodaj.planner;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    List<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u")
    List<User> findAllUsers();

}
