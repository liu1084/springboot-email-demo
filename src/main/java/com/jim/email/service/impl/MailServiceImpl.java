package com.jim.email.service.impl;

import com.jim.email.entity.SimpleMessage;
import com.jim.email.service.IMailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.lang.Nullable;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;

/**
 * @project: springboot-email-demo
 * @packageName: com.jim.email.service.impl
 * @author: Administrator
 * @date: 2020/3/3 10:07
 * @description：TODO
 */
@Service
public class MailServiceImpl implements IMailService {
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendSimpleMail(@NotNull SimpleMailMessage simpleMessage) {
		mailSender.send(simpleMessage);
	}

	@Override
	public void sendMailWithAttachments(@NotNull SimpleMailMessage simpleMailMessage, @NotNull List<File> files) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(simpleMailMessage.getTo());
			helper.setBcc(simpleMailMessage.getBcc());
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setText(simpleMailMessage.getText());
			helper.setReplyTo(simpleMailMessage.getReplyTo());
			files.forEach((file) -> {
				try {
					helper.addAttachment("", file);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			});
			mailSender.send(mimeMessage);
		}catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
