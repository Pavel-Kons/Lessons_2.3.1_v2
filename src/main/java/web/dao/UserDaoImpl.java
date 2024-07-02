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
    public List<User> getUsers(int count) {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
//        return List.of(
//                new User("A", "B", (byte) 1),
//                new User("Pavel", "Konstantinov", (byte) 23),
//                new User("Stepa", "C", (byte) 13),
//                new User("Masha", "D", (byte) 33),
//                new User("Katya", "E", (byte) 43)
//        );
    }

//    @Override
//    public User editUser(User user) {
//        return null;
//    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
