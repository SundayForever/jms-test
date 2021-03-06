## jms 配合 activeMQ 实现生产者、消费者

```
中间件：非底层操作系统软件，非业务应用软件，不是直接给最终用户使用的，不能直接给客户带来价值的软件统称为中间件
```


### 消息推送：

```
1.队列
1.1.队列模式，如果两个消费者，一个生产者，那么两个消费者接收的消息之和是生产者生产的消息
```

```
2.订阅
2.1.订阅模式，如果两个消费者，一个生产者，那么两个消费者接收的消息都等于生产者生产的消息
2.2.必须先订阅才会产生消息
```

### jms接口直接关系：
```
ConnectionFactory create Connection
Connection create Session
Session create MessageProducer
Session create MessageConsumer
MessageProducer Send to Destination
MessageConsumer Receive from Destination
Session create Message
```



### 中间件对比：

|对比  | ActiveMQ | RabbitMQ | Kafaka |
| --- | --- | --- | --- |
| 跨语言 | 支持(java优先) | 语言无关 | 支持(java优先) |
| 支持协议 | OpenWire，Stomp，XMPP， AMQP | AMQP |   |
| 优点 | 遵循JMS规范 安装部署简单 | 继承Erlang天生的并发性最初用于金融行业，稳定性，安全性有保障 | 依赖zk，可动态扩展节点，高性能、高吞吐量、无限扩容，消息可指定追溯  |
| 缺点 | 根据其它用户反馈，会莫名丢失消息。目前中心在下代产品apolle上，目前社区不活跃，对5.X维护较少  | Erlang语言难道大不支持，不支持动态扩展 | 严格的顺序机制，不支持消息优先级、不支持标准的消息协议，不利于平台迁移 |
| 综合评价 | 适合中小企业级消息应用场景，不适合上千个队列应用 | 适合对稳定性高的企业应用 | 一般应用在大数据日志处理或实时性（少量延迟），可靠性（小量丢数据）要求低的场景应用  |


