package etu.uportal;

import etu.uportal.domain.User;
import etu.uportal.infrastructure.service.UserService;
import etu.uportal.web.dto.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserService userService;

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            userService.registerNewUserAccount(new UserDto("Admin", "H123sadasd"));
            userService.registerNewUserAccount(new UserDto("Tester", "Hsasdasadasd"));
            userService.registerNewUserAccount(new UserDto("Guest", "H12321sadasd"));

            log.info("get all users:");
            log.info("-------------------------------");
            for (User user : userService.getAll()) {
                log.info(user.toString());
            }

            log.info("");
        };
    }
}
