package cn.wyy.service.impl;

import cn.wyy.service.EmailService;
import org.springframework.context.event.EventListener;

public class EmailServiceImpl implements EmailService {


    @Override
    public void sendEmail(String emailAdr) {
        System.out.println("发送邮件到" + emailAdr);
    }

    @EventListener
    @Override
    public void emailEventListener(String emailAdr) {
        sendEmail(emailAdr);
    }
}
