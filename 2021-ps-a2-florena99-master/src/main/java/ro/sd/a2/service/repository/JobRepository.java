package ro.sd.a2.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.model.entity.Job;

import java.util.Arrays;
import java.util.List;
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findAllByApproved(String approved);

    List<Job> findAllByName(String name);

    Job findByNameAndAndDescriptionAndType(String name,String description, String type);

    List<Job> findAllByCompany(String company);
}
