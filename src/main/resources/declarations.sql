ALTER SESSION SET CURRENT_SCHEMA = JAVA;

CREATE OR REPLACE
FUNCTION test_test_mek
  RETURN VARCHAR2
AS LANGUAGE JAVA NAME 'ru.sigma.it.Hello.world () return java.lang.String';










begin
  simple_test();
end;






/

CREATE FUNCTION amqp_exchange_declare
  (brokerId IN NUMBER, exchange IN VARCHAR2, exchange_type IN VARCHAR2)
  RETURN NUMBER
AS LANGUAGE JAVA
NAME 'com.zenika.oracle.amqp.RabbitMQPublisher.amqpExchangeDeclare(int, java.lang.String, java.lang.String) return int';



















/

DECLARE
  my_string VARCHAR2(400 CHAR);
BEGIN
  my_string := test_test_mek();
  dbms_output.put_line('The value of the string is ' || my_string);
END;


SELECT test_test_mek
FROM dual;


CREATE OR REPLACE PROCEDURE simple_test
AS LANGUAGE JAVA
NAME 'ru.sigma.it.Hello.send_test_message ()';
/