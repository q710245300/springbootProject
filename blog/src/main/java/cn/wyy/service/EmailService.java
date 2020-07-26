package cn.wyy.service;

import cn.wyy.pojo.User;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public void sendEmail(String emailAdr);

    public void emailEventListener(String emailAdr);
}
