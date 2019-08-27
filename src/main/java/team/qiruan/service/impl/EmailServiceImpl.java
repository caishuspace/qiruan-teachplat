package team.qiruan.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import team.qiruan.service.EMailService;

@Service
/**
 * EmailServiceImpl
 */
public class EmailServiceImpl implements EMailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${qiruan.mailaccount}")
    String mailAccount;
    @Value("${qiruan.baseurl}")
    String baseUrl;

    @Override
    @Async
    public void sendEmailValiCode(String username,String toAddress,String valiCode) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailAccount);
            helper.setTo(toAddress);
            helper.setSubject("邮箱绑定验证");

            Context context = new Context();
            context.setVariable("username", username);
            context.setVariable("vali", valiCode);
            context.setVariable("baseurl", baseUrl);
            String text = templateEngine.process("user/valimail", context);
            helper.setText(text, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}