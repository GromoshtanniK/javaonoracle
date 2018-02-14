package ru.sigma.it.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sigma.it.connection.ConnectionHolder;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JmsService {

    private static final Logger logger = LoggerFactory.getLogger(JmsService.class);

    private static final ConnectionHolder connectionHolder = new ConnectionHolder("tcp://10.196.6.43:61616");

    static {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                connectionHolder.establishConnection();
            }
        });
    }

    public void sendMessage(String queueName) throws JMSException {
        Connection connection = connectionHolder.getConnection();

        if (connection != null) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(queueName);

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create a messages
            String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
            TextMessage message = session.createTextMessage(text);

            producer.send(message);
        } else {
            logger.error("EROEROROOREOROEOROEROREOOREORO!!!!!!!!!!");
        }
    }
}
