package web.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.User;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
