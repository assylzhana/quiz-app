package assylzhan.project.quizapp.repositories;

import assylzhan.project.quizapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    UserDetails findByUsername(String username);
}
