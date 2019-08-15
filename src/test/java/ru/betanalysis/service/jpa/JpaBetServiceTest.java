package ru.betanalysis.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.betanalysis.service.AbstractBetServiceTest;

import static ru.betanalysis.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaBetServiceTest extends AbstractBetServiceTest {
}
