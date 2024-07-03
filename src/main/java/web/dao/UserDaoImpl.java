package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void updateUser(User user, Long id) {
        entityManager.find(User.class, id).setName(user.getName());
        entityManager.find(User.class, id).setSurname(user.getSurname());
        entityManager.find(User.class, id).setAge(user.getAge());
    }

    @Override
    public List<User> getUsers(int count) {
        return entityManager.createQuery("from User", User.class)
                .getResultList()
                .stream()
                .limit(count)
                .toList();
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}
