package web.dao;


import web.model.User;

public interface UserDao {
    void saveUser(User user);

    User getUserById(Long id);

    User updateUser(User user);
}
