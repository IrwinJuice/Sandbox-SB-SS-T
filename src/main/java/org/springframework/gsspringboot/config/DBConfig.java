package org.springframework.gsspringboot.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("org.springframework.gsspringboot.repository")
public class DBConfig {

    @Value("${c3p0.config.path}")
    private String C3P0_CONFIG_PATH;

    @Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${hibernate.show.sql}")
    private String HIBERNATE_SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;

    @Value("${entity.manager.packages.to.scan}")
    private String PACKAGES_TO_SCAN;

    @Bean
    @Primary
    public DataSource dataSource(){
        System.setProperty("com.mchange.v2.c3p0.cfg.xml", C3P0_CONFIG_PATH);
        return new ComboPooledDataSource();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public JpaTransactionManager transactionManager (LocalContainerEntityManagerFactoryBean factoryBean){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(factoryBean.getObject());
        return manager;
    }
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);

        Properties properties = new Properties();
        properties.put("hibernate.dialect", HIBERNATE_DIALECT);
        properties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        properties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        entityManagerFactoryBean.setJpaProperties(properties);

        return entityManagerFactoryBean;
    }
}
