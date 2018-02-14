loadjava -verbose -u java /home/oracle/java/java-on-oracle.jar
loadjava -u java /home/oracle/java/java-on-oracle.jar
loadjava -force -verbose -u java /home/oracle/java/java-on-oracle.jar


slf4j-api-1.7.5.jar

loadjava -resolve -verbose -u java/java /home/oracle/java/slf4j-api-1.7.5.jar
loadjava -resolve -verbose -u java/java /home/oracle/java/hawtbuf-1.10.jar
loadjava -force -resolve -verbose -u java/java /home/oracle/java/javax.ejb-api-3.2.jar.jar
loadjava -force -resolve -verbose -u java/java /home/oracle/java/geronimo-j2ee-management_1.1_spec-1.0.1.jar

loadjava -resolve -verbose -u java /home/oracle/java/Main2.class

loadjava -jarasresource -resolve -verbose -u java /home/oracle/java/Hello.class
dropjava -u java /home/oracle/java/Main2.class