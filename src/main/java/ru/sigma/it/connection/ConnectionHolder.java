package ru.sigma.it.connection;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;

public class ConnectionHolder {

    private static final Logger log = LoggerFactory.getLogger(ConnectionHolder.class);

    private ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    private volatile ActiveMQConnection connection;
    private final int reestablishWaitTime = 15000;

    public ConnectionHolder(String brokerURL) {
        connectionFactory.setBrokerURL(brokerURL);
    }

    public synchronized Connection getConnection() {
        return connection;
    }


    public void establishConnection() {
        try {
            log.info("Establishing connection");
            synchronized (this) {
                connection = (ActiveMQConnection) connectionFactory.createConnection();
                connection.setExceptionListener(new ExceptionListener() {
                    @Override
                    public void onException(JMSException listenerException) {
                        log.error("Connection exception", listenerException);
                        try {
                            connection.close();
                            log.info("Corrupted connection closed");

                        } catch (JMSException closeException) {
                            log.error("Error while closing connection", closeException);

                        } finally {
                            connection = null;
                            reestablishConnection();
                        }
                    }
                });
            }
        } catch (JMSException connectionException) {
            log.error("Error while establishing connection", connectionException);
            reestablishConnection();
        }
    }

    private void reestablishConnection() {
        log.info("Reestablish connection after" + reestablishWaitTime + " ms.");
        try {
            Thread.sleep(reestablishWaitTime);
            establishConnection();
        } catch (InterruptedException e) {
            log.error("Sleep interrupt", e);
        }
    }
}