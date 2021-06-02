package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.model.entity.Application;
import ro.sd.a2.model.entity.Job;
import ro.sd.a2.model.entity.Users;
import ro.sd.a2.service.repository.ApplicationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public void add(Application application){
        applicationRepository.save(application);
    }

    public Application findbyUserAndJob(Users users, Job job){
        return applicationRepository.findByUserAndJob(users,job);
    }

    public List<Application> findbyCompany(String company){
        return applicationRepository.findAllByJob_Company(company);

    }
}
