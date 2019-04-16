package ru.betanalysis;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.betanalysis.model.Bet;
import ru.betanalysis.model.Role;
import ru.betanalysis.model.User;
import ru.betanalysis.web.user.AdminRestController;
import ru.betanalysis.web.bet.BetRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password",
                    "secondName", "firstName", "phoneNumber",
                    LocalDateTime.now(), Role.ROLE_ADMIN));
            System.out.println();

            BetRestController mealController = appCtx.getBean(BetRestController.class);
            List<Bet> filteredMealsWithExcess =
                    mealController.getBetween(
                            LocalDate.of(2015, Month.MAY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2015, Month.MAY, 31), LocalTime.of(11, 0));
            filteredMealsWithExcess.forEach(System.out::println);
        }
    }
}
