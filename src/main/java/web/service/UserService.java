package web.service;

import web.model.User;

public interface UserService {
    void saveUser(User user);

    User getUserById(Long id);
}
