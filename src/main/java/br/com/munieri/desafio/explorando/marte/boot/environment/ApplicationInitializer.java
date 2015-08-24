package br.com.munieri.desafio.explorando.marte.boot.environment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

@Configuration
@ComponentScan("br.com.munieri.explorando.marte")
@EnableJpaRepositories({"br.com.munieri.explorando.marte.infrastructure.repository"})
@EnableWebMvc
public class ApplicationInitializer {

    private static final Logger LOGGER = LogManager.getLogger();

    public ApplicationInitializer() throws Exception {
        LOGGER.info("Inicializando aplicacao");
    }

    @Autowired
    private Environment env;

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    @Bean
    public DriverManagerDataSource dataSource() {
        LOGGER.info("Configurando [dataSource]...");
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("hibernate.driver"));
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setUsername(env.getProperty("jdbc.user"));
        ds.setPassword(env.getProperty("jdbc.pass"));
        LOGGER.info("Datasource configurado: " + ds.toString() );
        return ds;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LOGGER.info("Configurando [entityManagerFactory]...");
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("br.com.munieri.explorando.marte.infrastructure.repository");
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.hbm2ddl.auto", "create");
        hibernateProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.show_sql", "false");
        entityManagerFactoryBean.setJpaProperties(hibernateProperties);
        LOGGER.info(entityManagerFactoryBean);
        return entityManagerFactoryBean;
    }
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
