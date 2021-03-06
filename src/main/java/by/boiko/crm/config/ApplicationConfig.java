package by.boiko.crm.config;


import by.boiko.crm.model.Goods;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Configurer for database.
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:hibernate.properties",
        "classpath:data-base.properties",})
@ComponentScan(basePackageClasses = {Goods.class})
public class ApplicationConfig {


    private Environment environment;

    /**
     * Environment for database and mail configuration files
     * @param environment
     */
    @Autowired
    public ApplicationConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * @return deploy heroku
     * @throws URISyntaxException
     */
    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        String path;
        String username;
        String password;

        if (System.getenv("DATABASE_PATH") == null) {
            path = environment.getProperty("localhost.path");
            username = environment.getProperty("localhost.username");
            password = environment.getProperty("localhost.password");
        } else {
            path = System.getenv("DATABASE_PATH");
            username = System.getenv("DATABASE_USER");
            password = System.getenv("DATABASE_PASSWORD");
        }
        String dbUrl = "jdbc:mysql://" + path;

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException, URISyntaxException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("by.boiko.crm.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}