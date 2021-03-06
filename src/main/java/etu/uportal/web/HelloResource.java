package etu.uportal.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/")
public class HelloResource {


    @GetMapping("/principal")
    public Principal user(Principal principal) {
        return principal;
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

}