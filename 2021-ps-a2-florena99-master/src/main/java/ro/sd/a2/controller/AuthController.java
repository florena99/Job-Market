package ro.sd.a2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import ro.sd.a2.config.RabbitSender;
import ro.sd.a2.model.dtos.MessageResponse;
import ro.sd.a2.model.dtos.UserDto;
import ro.sd.a2.model.entity.Users;
import ro.sd.a2.service.EmailService;
import ro.sd.a2.service.UserService;
import org.springframework.http.converter.*;
import javax.jws.soap.SOAPBinding;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitSender rabbitMQSender;

    @Autowired
    private PasswordEncoder passwordEncoder;




    private Logger logger
            = Logger.getLogger(
            AuthController.class.getName());



    @GetMapping("/register")
    public Model register(Model model) {

        Users user= Users.builder().build();
        model.addAttribute("user",user);


        return model;
    }

    @PostMapping("/process_register")
    public String processRegister(Users user){
        if(userService.findEmail(user.getEmail())==null && userService.findUsername(user.getUsername())==null) {
            if(user.getType()==null){
                user.setType("Admin");
            }
            if(user.getType().equals("Personal"))
            {
                user.setRoles("USER");
            }
            else
                if(user.getType().equals("Company"))
                {
                    user.setRoles("COMPANY");
                }
                else
                    user.setRoles("ADMIN");
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            logger.log(Level.INFO,"User added");
//            UserDto userDto= userService.usertoDto(user);
            //restTemplate.postForObject("http://localhost:7800/email/send", userDto, UserDto.class);
            return "/process_register";
        }
        else
            logger.log(Level.WARNING,"Email exists in database");

        return "/error";



    }







}
