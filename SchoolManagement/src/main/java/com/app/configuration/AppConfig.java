package com.app.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.app.model.Student;
import com.app.model.Teacher;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.app")
@EnableTransactionManagement
public class AppConfig {

	@Bean
	public DriverManagerDataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/webservices");
		dataSource.setUsername("root");
		dataSource.setPassword("mysql");

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {

		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setAnnotatedClasses(Student.class, Teacher.class);
		bean.setHibernateProperties(hiProperties());

		return bean;
	}

	public Properties hiProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");

		return properties;

	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactoryBean().getObject());

		return transactionManager;
	}

}
