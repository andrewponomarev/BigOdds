package ru.betanalysis.service;

import ru.betanalysis.model.User;
import ru.betanalysis.to.UserTo;
import ru.betanalysis.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    void update(UserTo userTo);

    List<User> getAll();

    void enable(int id, boolean enable);

    User getWithBets(int id);
}
