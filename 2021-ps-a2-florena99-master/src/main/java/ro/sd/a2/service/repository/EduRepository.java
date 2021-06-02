package ro.sd.a2.service.repository;

import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.model.entity.CV;
import ro.sd.a2.model.entity.Education;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface EduRepository extends JpaRepository<Education, Long> {

    Education findByCv(CV cv);

    void deleteByCv(CV cv);
}
