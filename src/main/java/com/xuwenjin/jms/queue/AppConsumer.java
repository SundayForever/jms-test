package com.xuwenjin.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: xuwenjin
 * @Date: 2018/7/18 16:56
 * @Description:
 */
public class AppConsumer {

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

        //6. 创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //7. 创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try{
                    System.out.println("接收消息" + textMessage.getText());
                }catch (JMSException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
