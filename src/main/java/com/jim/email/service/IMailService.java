package com.jim.email.service;

import com.jim.email.entity.SimpleMessage;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;

/**
 * @project: springboot-email-demo
 * @packageName: com.jim.email.service
 * @author: Administrator
 * @date: 2020/3/2 19:53
 * @description：TODO
 */
public interface IMailService {
	void sendSimpleMail(SimpleMailMessage simpleMessage);
	void sendMailWithAttachments(SimpleMailMessage simpleMailMessage, List<File> files) throws MessagingException;
}
