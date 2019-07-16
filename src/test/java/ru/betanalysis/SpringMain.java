package ru.betanalysis;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.betanalysis.model.Bet;
import ru.betanalysis.model.Role;
import ru.betanalysis.model.User;
import ru.betanalysis.web.bet.BetRestController;
import ru.betanalysis.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), Profiles.REPOSITORY_IMPLEMENTATION);
            appCtx.load("spring/spring-app.xml", "spring/inmemory.xml");
            appCtx.refresh();
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password",
                    "secondName", "firstName", "phoneNumber",
                    new Date(), Role.ROLE_ADMIN));
            System.out.println();

            BetRestController BetController = appCtx.getBean(BetRestController.class);
            List<Bet> filteredBetsWithExcess =
                    BetController.getBetween(
                            LocalDate.of(2015, Month.MAY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2015, Month.MAY, 31), LocalTime.of(11, 0));
            filteredBetsWithExcess.forEach(System.out::println);
        }
    }
}
