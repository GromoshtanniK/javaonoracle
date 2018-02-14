package ru.sigma.it;

import ru.sigma.it.jms.JmsService;

import javax.jms.JMSException;

public class Main2 {

    public static void main(String[] args) {
        invokeFromDB();
    }

    private static JmsService jmsService = new JmsService();

    private static void invokeFromDB() {
        while (true) {
            try {
                jmsService.sendMessage("HEKKO");
            } catch (JMSException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
