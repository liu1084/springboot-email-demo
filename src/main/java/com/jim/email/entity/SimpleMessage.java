package com.jim.email.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @project: springboot-email-demo
 * @packageName: com.jim.email.entity
 * @author: Administrator
 * @date: 2020/3/2 19:54
 * @description：TODO
 */
@Data
public class SimpleMessage implements Serializable {
	private static final long serialVersionUID = -5986501003278993347L;
	private String [] to;
	private String subject;
	private String body;
}
