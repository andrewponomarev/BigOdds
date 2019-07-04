package ru.betanalysis.service.datajpa;


import org.springframework.test.context.ActiveProfiles;
import ru.betanalysis.Profiles;
import ru.betanalysis.service.AbstractUserServiceTest;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
