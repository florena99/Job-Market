package ro.sd.a2.controller;


import com.itextpdf.text.DocumentException;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.sd.a2.config.RabbitSender;
import ro.sd.a2.model.dtos.ApplicationDto;
import ro.sd.a2.model.dtos.AuthDto;
import ro.sd.a2.model.dtos.JobDto;
import ro.sd.a2.model.dtos.UserUpdateDto;
import ro.sd.a2.model.entity.*;
import ro.sd.a2.security.UserPrincipal;
import ro.sd.a2.service.*;
import ro.sd.a2.service.strategy.CVCSV;
import ro.sd.a2.service.strategy.CvPDF;
import ro.sd.a2.service.strategy.CvStrategy;


import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.sql.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
@SessionAttributes({"user"})
public class UserController {

    @Autowired
    private CVService CVService;

    @Autowired
    private EduService eduService;

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private RabbitSender rabbitMQSender;

    private CvStrategy strategy;


    private Logger logger
            = Logger.getLogger(
            UserController.class.getName());


    @GetMapping("/login")
    public Model loginForm(Model model){


        AuthDto authDto= AuthDto.builder().build();
        model.addAttribute("credentials", authDto);
        return model;

    }


    public Users currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userService.findUsername(username);

    }

//    @PostMapping("/login")
//    public String login( AuthDto authDto){
//        user=userService.login(authDto);
//        if(user!=null) {
//            if (user.getType().equals("Personal")) {
//                logger.log(Level.INFO, "Regular user login");
//
//                return "redirect:/home";
//            } else if(user.getType().equals("Company")) {
//                logger.log(Level.INFO,"Company login");
//                return "redirect:/homeAdmin";
//            }
//            else
//                return "redirect:/homeadm";
//        }
//
//        return "redirect:/error";
//
//
//
//    }

    @GetMapping("/home")
    public String homePage()
    {
        return "/home";
    }


    @GetMapping("/homeadm")
    public String homePageAdm()
    {
        return "/homeadm";
    }


    @GetMapping("/error")
    public String errorPage(){
        return "/error";
    }


    @GetMapping("/downloadCVSuccess")
    public String downloadsucpage(){
        return "/downloadCVSuccess";
    }


    @GetMapping("/cvPage")
    public Model processRegister(Model mav){

        CV cv = CV.builder().build();
        Education  education = Education.builder().build();

        mav.addAttribute("cv", cv);
        mav.addAttribute("education",education);
        Users us = new Users();
        mav.addAttribute("user", us);

        return mav;
    }


    @PostMapping("/cvCreated")
    public String processRegister( CV cv, Education education){
        Users user=currentUser();
        if(user.getCv()==null) {
            education.setCv(cv);
            eduService.save(education);
            cv.setUsers(user);
            CVService.save(cv);
            user.setCv(cv);
            logger.log(Level.INFO,"Cv created");
            return "/cvCreated";
        }
        logger.log(Level.INFO,"This user has an CV already");
        return "/existingCV";

    }

    @GetMapping("/account")
    public Model accountUpdate(Model mav){
        String choice = "";
        UserUpdateDto userUpdateDto=UserUpdateDto.builder().build();
        mav.addAttribute("user", userUpdateDto);
        return mav;

    }

    @PostMapping("/account")
    public String account( UserUpdateDto userUpdateDto) {
        Users user=currentUser();
        user.setUsername(userUpdateDto.getName());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(userUpdateDto.getPassword());
        userService.save(user);
        logger.log(Level.INFO,"Account modified");
        return "redirect:/accountSuc";
    }

    @GetMapping("/accountSuc")
    public String accountPage()
    {
        return "/accountSuc";
    }

    @PostMapping("/deleteSucced")
    public String delete(){
        Users user=currentUser();
        eduService.delete(user.getCv());
        CVService.delete(user);
        userService.delete(user);
        return "/deleteSucced";
    }

    @GetMapping("/deleteSucced")
    public String deleteSuccedPage()
    {
        return "/deleteSucced";
    }



    @PostMapping("/viewCv")
    public Model viewCV(Model model){
        Users user=currentUser();
        CV cv = CVService.findCV(user);
        System.out.println(cv.getAddress());
        Education  education = eduService.findEducations(cv);
        model.addAttribute("cv", cv);
        model.addAttribute("education",education);
        return model;
    }



    @PostMapping("/editCv")
    public String editCV(CV cv, Education education){
        Users user=currentUser();
        eduService.edit(cv, education);
        CVService.edit(cv,user);

        return "/editCv";

    }
    @PostMapping("/deleteCV")
    public String deleteCV(){
        Users user=currentUser();
        eduService.delete(user.getCv());
        CVService.delete(user);
        return "/deleteCV";
    }

    @GetMapping("/downloadCV")
    public Model downloadpage(Model model){
        StrategyDto strategyDto=new StrategyDto();
        model.addAttribute("strategie", strategyDto);
        return model;

    }




    @PostMapping("/downloadCVSuccess")
    public String downloadCV(StrategyDto strategyDto) throws FileNotFoundException, DocumentException {
        System.out.println(strategyDto.getType());
        if(strategyDto.getType().equals("PDF")) {
            strategy = new CvPDF();
        }
        else
        {
            strategy= new CVCSV();
        }
        Users user=currentUser();
        strategy.generate(user.getCv());
        return "/downloadCVSuccess";
    }




    @GetMapping("/homeAdmin")
    public String homeAdminPage(){
        return "/homeAdmin";
    }


    @PostMapping("/apply")
    public String applyPage(JobDto jobDto) {
        Users user=currentUser();
        Job job = jobService.findJob(jobDto);
        if (applicationService.findbyUserAndJob(user, job) == null){
            Application application = Application.builder().id(UUID.randomUUID().toString())
                    .job(job)
                    .user(user)
                    .date(new Date(System.currentTimeMillis()))
                    .build();

        applicationService.add(application);
            ApplicationDto applicationDto=ApplicationDto.builder()
                    .id(application.getId())
                    .mail(application.getUser().getEmail())
                    .jobname(application.getJob().getName())
                    .date(application.getDate())
                    .build();
            rabbitMQSender.send(applicationDto);
        return "/apply";
    }
        return "/existingApplication";
    }

    @PostMapping("/jobPageAdmin")
    public String createJob(Job job){
        Users user=currentUser();
        if(!jobService.existingJob(job)) {
            job.setApproved("no");
            job.setCompany(user.getUsername());
            jobService.create(job);
            logger.log(Level.INFO,"Job created");
            return "/jobPageAdmin";
        }
        logger.log(Level.INFO,"Job already exists");
        return  "/existingJob";
    }

    @GetMapping("/jobs2")
    public Model jobs2(Model model)
    {

        Users user=currentUser();
        model.addAttribute("jobs",jobService.findAllByCompany(user.getUsername()));
        return model;
    }

    @GetMapping("/users")
    public Model usersList(Model model)
    {

        Users user=currentUser();
        model.addAttribute("applications", applicationService.findbyCompany(user.getUsername()) );
        return model;
    }


}


