package ro.sd.a2.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ro.sd.a2.model.dtos.AuthDto;
import ro.sd.a2.model.dtos.UserDto;
import ro.sd.a2.model.entity.Users;
import ro.sd.a2.model.mapper.UserMapper;
import ro.sd.a2.service.repository.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<Users> getAllUsers() {

        return new ArrayList<>();
    }

    /**
     * Metoda care creaza un user
     * @param  - input param whatever
     * @throws ArithmeticException - when ceva/0
     * @return - ceva
     */


    public UserDto usertoDto(Users users){
        return userMapper.toDto(users);
    }


    public Users save(Users user){
        userRepository.save(user);
        return user;
    }

    public Users addAdmin(){
        Users users=Users.builder().email("adm@adm")
                .username("adm")
                .gender("female")
                .password("adm")
                .type("Admin")
                .build();
        return users;
    }

    public Users findUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Users findEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Users login(AuthDto authDto){
        return userRepository.findByUsernameAndPassword(authDto.getUsername(), authDto.getPassword()).get(0);
    }

    public void delete(Users users){
        userRepository.deleteById(users.getId());
    }


    public List<UserDto> usersList(){
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

}
