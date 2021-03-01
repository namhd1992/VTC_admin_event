//package com.vtc.gamerid.gateway.config;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate3.HibernateTemplate;
//import org.springframework.orm.hibernate3.HibernateTransactionManager;
//import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
///**
// * Created by phucnguyen on 06/03/2017.
// */
//@Configuration
//public class RepositoryConfig
//{
//    //${jdbc.driverClassName}
//    @Value("${jdbc.driverClassName}")     private String driverClassName;
//    @Value("${jdbc.url}")                 private String url;
//    @Value("${jdbc.username}")             private String username;
//    @Value("${jdbc.password}")             private String password;
//
//    @Value("${hibernate.dialect}")         private String hibernateDialect;
//    @Value("${hibernate.show_sql}")     private String hibernateShowSql;
//    @Value("${hibernate.hbm2ddl.auto}") private String hibernateHbm2ddlAuto;
//    @Value("${hibernate.connection.CharSet}") private String hibernateCharSet;
//    @Value("${hibernate.connection.useUnicode}") private String hibernateUniCode;
//    @Value("${hibernate.connection.characterEncoding}") private String hibernateCharacter;
//
//    @Bean()
//    public DataSource getDataSource()
//    {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName(driverClassName);
//        ds.setUrl(url);
//        ds.setUsername(username);
//        ds.setPassword(password);
//        return ds;
//    }
//
//    @Bean
//    @Autowired
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
//    {
//        HibernateTransactionManager htm = new HibernateTransactionManager();
//        htm.setSessionFactory(sessionFactory);
//        return htm;
//    }
//
//    @Bean
//    @Autowired
//    public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory)
//    {
//        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
//        return hibernateTemplate;
//    }
//
//    @Bean
//    public AnnotationSessionFactoryBean getSessionFactory()
//    {
//        AnnotationSessionFactoryBean asfb = new AnnotationSessionFactoryBean();
//        asfb.setDataSource(getDataSource());
//        asfb.setHibernateProperties(getHibernateProperties());
//        asfb.setPackagesToScan(new String[]{"com.vtc.gamerid.gateway"});
//        return asfb;
//    }
//
//    @Bean
//    public Properties getHibernateProperties()
//    {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", hibernateDialect);
//        properties.put("hibernate.show_sql", hibernateShowSql);
//        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
//        properties.put("hibernate.connection.CharSet", hibernateCharSet);
//        properties.put("hibernate.connection.useUnicode", hibernateUniCode);
//        properties.put("hibernate.connection.characterEncoding", hibernateCharacter);
//
//        return properties;
//    }
//
//}
