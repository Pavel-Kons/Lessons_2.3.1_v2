package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("web")
@PropertySource("classpath:hibernate.properties")
public class DatabaseConfig {

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource basicDataSource = new DriverManagerDataSource();
        basicDataSource.setUrl(environment.getProperty("hibernate.connection.url"));
        basicDataSource.setDriverClassName(environment.getProperty("hibernate.driver_class"));
        basicDataSource.setUsername(environment.getProperty("hibernate.connection.username"));
        basicDataSource.setPassword(environment.getProperty("hibernate.connection.password"));

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