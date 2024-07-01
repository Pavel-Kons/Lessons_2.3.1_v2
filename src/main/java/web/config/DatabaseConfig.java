package web.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("web")
public class DatabaseConfig implements WebMvcConfigurer {

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//        return properties;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl(environment.getRequiredProperty("hibernate.connection.url"));
//        basicDataSource.setDriverClassName(environment.getRequiredProperty("hibernate.driver_class"));
//        basicDataSource.setUsername(environment.getRequiredProperty("hibernate.connection.username"));
//        basicDataSource.setPassword(environment.getRequiredProperty("hibernate.connection.password"));
//
//        return basicDataSource;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("web.model");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        return sessionFactory;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }

////////////////////////////////////////////////////


//    //Метод, который вернет объект dataSource для соединения к нашей бд, используя данные из файла db.properties
//    @Bean
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getProperty("hibernate.driver_class"));
//        dataSource.setUrl(environment.getProperty("hibernate.connection.url"));
//        dataSource.setUsername(environment.getProperty("hibernate.connection.username"));
//        dataSource.setPassword(environment.getProperty("hibernate.connection.password"));
//
//        return dataSource;
//    }
//
//    // метод, который создаст EntityManagerFactory, который будет управляться контейнером
//    @Bean
//    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
//        entityManager.setDataSource(getDataSource());
//        entityManager.setPackagesToScan("web.model");
//        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManager.setJpaProperties(getHibernateProperties());
//        return entityManager;
//    }
//
//
//    //метод, который возвращает объект Properties, в который переданы свойства
//    public Properties getHibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
//        return properties;
//    }
//
//    @Bean
//    public JpaTransactionManager getTransactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
//        return transactionManager;
//    }


//////////////////////////////////////////////////////////


    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl(environment.getRequiredProperty("hibernate.connection.url"));
//        basicDataSource.setDriverClassName(environment.getRequiredProperty("hibernate.driver_class"));
//        basicDataSource.setUsername(environment.getRequiredProperty("hibernate.connection.username"));
//        basicDataSource.setPassword(environment.getRequiredProperty("hibernate.connection.password"));
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/schema");
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("{tkkj4Djhkl");


        return basicDataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());

        return manager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("web.model");
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setJpaProperties(getHibernateProperties());

        return entityManager;
    }

    public Properties getHibernateProperties() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("hibernate.properties");
            properties.load(inputStream);

            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't find hibernate.properties");
        }
    }
}
