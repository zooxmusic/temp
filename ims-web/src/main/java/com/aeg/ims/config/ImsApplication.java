package com.aeg.ims.config;

import com.aeg.ims.model.Message;
import org.hibernate.EmptyInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.Serializable;
import org.hibernate.type.Type;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@SpringBootApplication
//@EnableJpaRepositories
//@EnableTransactionManagement
@ComponentScan("com.aeg.ims")
public class ImsApplication /*implements TransactionManagementConfigurer*/ {

    public static void main(String[] args) {
        SpringApplication.run(ImsApplication.class, args);
    }

    /*@Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.HSQL);
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(Message.class.getPackage().getName());
        factory.setDataSource(dataSource());

        return factory;
    }

    @Bean
    @DependsOn("entityManagerFactory")
    public ResourceDatabasePopulator initDatabase(DataSource dataSource) throws Exception {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data.sql"));
        populator.populate(dataSource.getConnection());
        return populator;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager  transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }
    @Bean
    public EmptyInterceptor hibernateInterceptor() {
        return new EmptyInterceptor() {
            @Override
            public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
                return false;
            }
        };
    }*/

    /*@Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }*/
}
