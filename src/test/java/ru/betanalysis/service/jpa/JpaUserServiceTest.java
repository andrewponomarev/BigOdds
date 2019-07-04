package ru.betanalysis.service.jpa;


import org.springframework.test.context.ActiveProfiles;
import ru.betanalysis.Profiles;
import ru.betanalysis.service.AbstractUserServiceTest;

@ActiveProfiles(Profiles.JPA)
public class JpaUserServiceTest extends AbstractUserServiceTest {
}
