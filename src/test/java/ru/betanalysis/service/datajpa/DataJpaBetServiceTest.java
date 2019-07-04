package ru.betanalysis.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.betanalysis.service.AbstractBetServiceTest;

import static ru.betanalysis.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaBetServiceTest extends AbstractBetServiceTest {
}
