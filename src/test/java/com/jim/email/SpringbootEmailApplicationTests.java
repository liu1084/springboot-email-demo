package com.jim.email;

import com.jim.email.service.IMailService;
import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Files;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class SpringbootEmailApplicationTests {

	@Autowired
	private IMailService mailService;

	private static final String EMAIL_TEMPLATE_PATH = "/templates/email.stg";
	private static final String RECOVERY_PASS_EMAIL_TEMPLATE_INSTANCE = "recoveryEmail";

	@Test
	public void testSendSimpleMail() throws IOException {
		mailService.sendSimpleMail(setSimpleMail());
	}

	private SimpleMailMessage setSimpleMail() throws IOException {
		String [] to = {"liu1084@163.com"};
		String [] bcc = {"liu1084@gmail.com"};
		String from = "proaim@163.com";
		String replyTo = "admin@proaimltd.com.cn";
		String subject = "密码找回测试邮件";
		String body = generateMailBody();

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setBcc(bcc);
		simpleMailMessage.setReplyTo(replyTo);
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		simpleMailMessage.setSentDate(new Date());
		return simpleMailMessage;
	}

	private String generateMailBody() throws IOException {
		String code = UUID.randomUUID().toString();
		ClassPathResource cpr = new ClassPathResource(EMAIL_TEMPLATE_PATH);
		InputStream inputStream = cpr.getInputStream();
		File file = File.createTempFile("tmp", ".stg");
		FileUtils.copyInputStreamToFile(inputStream, file);
		final char delimiter = '$';
		STGroup stGroup = new STGroupFile(file.getAbsolutePath(), delimiter, delimiter);
		final ST recoveryEmailTemplate = stGroup.getInstanceOf(RECOVERY_PASS_EMAIL_TEMPLATE_INSTANCE);
		recoveryEmailTemplate.add("url", code);
		recoveryEmailTemplate.add("validHours", 60);
		recoveryEmailTemplate.add("currentYear", LocalDate.now().getYear());
		return recoveryEmailTemplate.render();
	}

	@Test
	public void testAttachmentMail() throws IOException, MessagingException {
		SimpleMailMessage simpleMailMessage = setSimpleMail();
		File attachment = new File("C:\\Users\\Administrator\\Desktop\\active-win10-ltsc.bat");
		if (!attachment.isFile() || !attachment.exists()) {
			throw new FileNotFoundException();
		}
		FileSystemResource fileSystemResource = new FileSystemResource(attachment);
		mailService.sendMailWithAttachments(simpleMailMessage,fileSystemResource);
	}
}
