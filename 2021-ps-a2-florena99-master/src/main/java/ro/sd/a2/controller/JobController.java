package ro.sd.a2.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ro.sd.a2.model.dtos.ApplicationDto;
import ro.sd.a2.model.dtos.JobDto;
import ro.sd.a2.model.entity.Application;
import ro.sd.a2.model.entity.Job;
import ro.sd.a2.service.ApplicationService;
import ro.sd.a2.service.JobService;
import ro.sd.a2.service.UserService;
import sun.util.logging.PlatformLogger;

import java.sql.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@SessionAttributes({"job"})
public class JobController {

    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationService applicationService;

    private Logger logger
            = Logger.getLogger(
            JobController.class.getName());

    @GetMapping("/jobs")
    public Model jobs(Model model)
    {


        model.addAttribute("jobs",jobService.findAll("yes"));
        model.addAttribute("job", new JobDto());
        return model;
    }




    @GetMapping("/jobPageAdmin")
    public Model jobPage(Model model){

        model.addAttribute("job", Job.builder().build());
        return model;
    }



    @PostMapping("/deleteJobs")
    public String deleteJobs(){
        jobService.deletejobs();
        return "/deleteJobs";
    }


    @GetMapping("/allusers")
    public Model allusersList(Model model)
    {


        model.addAttribute("users",userService.usersList());
        return model;
    }




    @GetMapping("/jobs3")
    public Model jobs3(Model model)
    {


        model.addAttribute("jobs",jobService.findAll("no"));
        model.addAttribute("job", new JobDto());
        return model;
    }


    @PostMapping("/approved")
    public String applyPage(JobDto jobDto) {
        Job job = jobService.findJob(jobDto);
        job.setApproved("yes");
        jobService.create(job);
      return "/approved";
    }

    @GetMapping("/accessDenied")
    public String access()
    {
        return "/accessDenied";
    }



}
