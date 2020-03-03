package com.jim.email.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.Properties;

/**
 * @project: springboot-email-demo
 * @packageName: com.jim.email.config
 * @author: Administrator
 * @date: 2020/3/3 10:40
 * @description：TODO spring.mail.properties.mail.smtp.auth=true
 * spring.mail.properties.mail.smtp.starttls.enable=true
 * spring.mail.properties.mail.smtp.starttls.required=true
 */
@Configuration
@Getter
@Setter
@ToString
public class MailPropertiesConfig implements Serializable {
	private static final long serialVersionUID = -1723274388990495785L;

	@Value("${mail.transport.protocol}")
	private String protocol;

	@Value("${mail.smtp.auth}")
	private Boolean auth;

	@Value("${mail.smtp.starttls.enable}")
	private Boolean tlsEnable;

	@Value("${mail.debug}")
	private Boolean debug;

	@Bean(name = "mailProperties")
	public Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", protocol);
		properties.setProperty("mail.smtp.auth", String.valueOf(auth));
		properties.setProperty("mail.smtp.starttls.enable", String.valueOf(tlsEnable));
		properties.setProperty("mail.debug", String.valueOf(debug));
		return properties;
	}
}
