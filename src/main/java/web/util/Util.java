package web.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import web.model.User;

import java.util.function.Consumer;

@org.springframework.context.annotation.Configuration
@EnableWebMvc
@ComponentScan
public class Util {
//    String hostName = "localhost";
//    String userName = "root";
//    String password = "{tkkj4Djhkl";
//    String connectionURL = "jdbc:mysql://" + hostName + ":3306/new_schema";

    private Environment environment;

    public static void createSession(Consumer<Session> consumer) {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            consumer.accept(session);
        }
    }


}
