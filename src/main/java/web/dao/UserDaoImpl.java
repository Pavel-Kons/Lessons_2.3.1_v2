package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import web.model.User;
import web.util.Util;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long id) {
        List<User> array = new ArrayList<>();
        Util.createSession(session -> {
            User user1 = session.get(User.class, id);
            array.add(user1);
        });
        return array.get(0);
    }

    @Override
    public User updateUser(User user) {
        return user;
    }

}
