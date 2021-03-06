package com.church.clearance;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJms
@EnableScheduling
@EnableAsync

@EnableAspectJAutoProxy(proxyTargetClass = true)
public class App  extends SpringBootServletInitializer//implements CommandLineRunner
{
	 @Autowired
	    DataSource dataSource;
	
	 
	 @Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			return application.sources(App.class);
		}
	 
	 @Bean
	    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
	                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
	        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        // This provides all boot's default to this factory, including the message converter
	        configurer.configure(factory, connectionFactory);
	        // You could still override some of Boot's default if necessary.
	        return factory;
	    }

	    @Bean // Serialize message content to json using TextMessage
	    public MessageConverter jacksonJmsMessageConverter() {
	        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	        converter.setTargetType(MessageType.TEXT);
	        converter.setTypeIdPropertyName("_type");
	        return converter;
	    }
	    
	    @Bean(destroyMethod = "shutdown")
	    public Executor getTaskExecutor() {
			
	        return Executors.newScheduledThreadPool(20);
	    }	
	    
	   
	    
	    @Bean 
     public InternalResourceViewResolver thymeleafTemplateResolver() { 
	    	InternalResourceViewResolver thymeleafTemplateResolver = new InternalResourceViewResolver(); 
	    	
             thymeleafTemplateResolver.setPrefix("/WEB-INF/view/"); 
             thymeleafTemplateResolver.setSuffix(".jsp"); 
            // thymeleafTemplateResolver.setTemplateMode("HTML5"); 
             thymeleafTemplateResolver.setOrder(1); 
             return thymeleafTemplateResolver; 
     } 
	    public static void main( String[] args )
	    {
	    	SpringApplication.run(App.class, args);	
	    
	   
	    }
	}
