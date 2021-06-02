package ro.sd.a2.service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.model.entity.CV;
import ro.sd.a2.model.entity.Users;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CVRepository extends JpaRepository <CV, Long> {

    void deleteByUsers(Users user);

    CV findCVByUsers(Users user);
}
