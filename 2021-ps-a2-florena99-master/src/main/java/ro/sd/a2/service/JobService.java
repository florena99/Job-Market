package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.model.dtos.JobDto;
import ro.sd.a2.model.entity.Job;
import ro.sd.a2.model.mapper.JobMapper;
import ro.sd.a2.service.repository.JobRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper  jobMapper;

    public List<JobDto> findAll(String approved){
        return jobRepository.findAllByApproved(approved)
                .stream()
                .map(jobMapper::toDto)
                .collect(Collectors.toList());
    }



    public List<JobDto> findAllByCompany(String company){
        return jobRepository.findAllByCompany(company)
                .stream()
                .map(jobMapper::toDto)
                .collect(Collectors.toList());
    }

    public void create(Job job){
        jobRepository.save(job);
    }


    public List<Job> findJobByName(String name) {
        return jobRepository.findAllByName(name);
    }

    public void deletejobs(){ jobRepository.deleteAll();}

    public boolean existingJob(Job job){
        if(jobRepository.findByNameAndAndDescriptionAndType(job.getName(),job.getDescription(),job.getType())!=null)
            return true;
        return false;
    }

    public Job findJob(JobDto jobDto)
    {
        return jobRepository.findByNameAndAndDescriptionAndType(jobDto.getName(),jobDto.getDescription(),jobDto.getType());
    }




}
