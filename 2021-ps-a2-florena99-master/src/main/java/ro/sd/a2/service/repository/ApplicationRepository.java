package ro.sd.a2.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.model.entity.Application;
import ro.sd.a2.model.entity.Job;
import ro.sd.a2.model.entity.Users;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String> {

    Application findByUserAndJob(Users user, Job job );

    List<Application> findAllByJob_Company(String company);
}
