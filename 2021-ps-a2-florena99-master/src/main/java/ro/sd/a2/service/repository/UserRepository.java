package ro.sd.a2.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.model.entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findByUsernameAndPassword(String username, String password);

    void deleteById(Long id);

    Users findByUsername(String username);

    Users findByEmail(String email);
}
