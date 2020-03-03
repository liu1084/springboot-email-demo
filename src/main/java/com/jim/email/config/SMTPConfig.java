package com.jim.email.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @project: springboot-email-demo
 * @packageName: com.jim.email.config
 * @author: Administrator
 * @date: 2020/3/3 10:57
 * @description：TODO
 */
@Configuration
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "spring.mail")
public class SMTPConfig implements Serializable {
	private static final long serialVersionUID = -6211650594716174322L;
	private String host;
	private Integer port;
	private String username;
	private String password;
	private String protocol;
}
