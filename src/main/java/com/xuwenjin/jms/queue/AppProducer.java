package com.xuwenjin.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Arrays;

/**
 * @Auther: xuwenjin
 * @Date: 2018/7/18 16:14
 * @Description:
 */
public class AppProducer {

    public static final String url = "tcp://172.16.3.129:61616";
    public static final String queueName = "queue-test";

    public static void main(String[] args) throws JMSException {
        //1. 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        //2. 创建Connection
        Connection connection = connectionFactory.createConnection();

        //3. 启动连接
        connection.start();

        //4. 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5. 创建一个目标
        Destination destination = session.createQueue(queueName);

        //6. 创建一个生产者
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            //7. 创建消息
            TextMessage message = session.createTextMessage("test" + i);
            producer.send(message);
            System.out.println(message.getText());
        }

        //8. 关闭连接
        connection.close();
    }
}
