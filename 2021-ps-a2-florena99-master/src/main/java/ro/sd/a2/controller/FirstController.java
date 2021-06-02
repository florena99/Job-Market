package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ro.sd.a2.model.dtos.AuthDto;
import ro.sd.a2.model.dtos.UserDto;
import ro.sd.a2.model.entity.Users;
import ro.sd.a2.service.UserService;

@SessionAttributes({"user"})
@Controller
public class FirstController {

    @Autowired
    private UserService userService;




}

