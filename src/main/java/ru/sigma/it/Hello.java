package ru.sigma.it;

import ru.sigma.it.jms.JmsService;

import javax.jms.JMSException;

public class Hello {

    private static JmsService jmsService = new JmsService();

    public static String world() {
        return "Hello world";
    }

    public static void send_test_message() {
        try {
            jmsService.sendMessage("INSIDE_ORACLE");
        } catch (JMSException e) {
//            e.printStackTrace();
        }
    }

}
