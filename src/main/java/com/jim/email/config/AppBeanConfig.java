package com.jim.email.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @project: springboot-email-demo
 * @packageName: com.jim.email.config
 * @author: Administrator
 * @date: 2020/3/2 19:42
 * @description：TODO
 */
@Configuration
@Data
public class AppBeanConfig implements Serializable {
	private static final long serialVersionUID = 7160571458111633458L;

	@Autowired
	private SMTPConfig smtpConfig;

	@Autowired
	private Properties mailProperties;

	@Bean(name = "mailSender")
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setDefaultEncoding(StandardCharsets.UTF_8.toString());
		sender.setHost(smtpConfig.getHost());
		sender.setPort(smtpConfig.getPort());
		sender.setProtocol(smtpConfig.getProtocol());
		sender.setUsername(smtpConfig.getUsername());
		sender.setPassword(smtpConfig.getPassword());
		//Properties properties = sender.getJavaMailProperties();
		sender.setJavaMailProperties(mailProperties);
		return sender;
	}
}
