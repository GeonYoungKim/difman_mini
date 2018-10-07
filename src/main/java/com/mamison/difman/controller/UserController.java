package com.mamison.difman.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    @GetMapping
    public List<User> getUsers(){
        return Arrays.asList(
                new User("Sam",2000L),
                new User("Peter",1000L)
        );
    }

    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") final String userName){
        return new User(userName,1000L);
    }

    @Getter
    @Setter
    private class User{
        private String userName;
        private Long salary;

        public User(String userName, Long salary) {
            this.userName = userName;
            this.salary = salary;
        }
    }
}
