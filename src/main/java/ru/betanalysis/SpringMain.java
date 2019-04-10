package ru.betanalysis;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.betanalysis.model.Role;
import ru.betanalysis.model.User;
import ru.betanalysis.repository.UserRepository;
import ru.betanalysis.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password",
                    "secondName", "firstName", "phoneNumber",
                    LocalDateTime.now(), Role.ROLE_ADMIN));
        }
    }
}
