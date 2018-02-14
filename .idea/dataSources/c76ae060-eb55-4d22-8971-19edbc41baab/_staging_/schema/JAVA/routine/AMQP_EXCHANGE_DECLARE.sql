CREATE FUNCTION amqp_exchange_declare
  (brokerId IN NUMBER, exchange IN VARCHAR2, exchange_type IN VARCHAR2)
  RETURN NUMBER
AS LANGUAGE JAVA
NAME 'com.zenika.oracle.amqp.RabbitMQPublisher.amqpExchangeDeclare(int, java.lang.String, java.lang.String) return int';
/