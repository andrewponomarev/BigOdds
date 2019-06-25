package ru.betanalysis.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.betanalysis.model.User;
import ru.betanalysis.repository.UserRepository;
import ru.betanalysis.web.user.UserTestData;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.betanalysis.web.user.UserTestData.ADMIN;
import static ru.betanalysis.web.user.UserTestData.USER;

@Repository
public class InMemoryUserRepositoryImpl extends InMemoryBaseRepositoryImpl<User> implements UserRepository {

    public void init() {
        entryMap.clear();
        entryMap.put(UserTestData.USER_ID, USER);
        entryMap.put(UserTestData.ADMIN_ID, ADMIN);
    }

    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        Objects.requireNonNull(email, "email must not be null");
        return getCollection().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }
}
