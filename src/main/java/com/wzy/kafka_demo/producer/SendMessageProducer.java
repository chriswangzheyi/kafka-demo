package com.wzy.kafka_demo.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class SendMessageProducer {
	
	
	public static final String SERVERS = "54.249.79.156:9092";
	public static final String TOPIC = "wzy-topic";
	
	
	public static void main(String[] args) {
		// 如果要想进行Kafka消息发送需要使用Properties定义一些环境属性
		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS); // 定义Kafka服务地址
		// Kafka之中是以key和value的形式进行消息的发送处理， 所以为了保证Kafka的性能，专门提供有统一类型
		// props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer") ;
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()) ;
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName()) ;
		long start = System.currentTimeMillis() ;
		
		
		// 定义消息发送者对象，依靠此对象可以进行消息的传递
		Producer<String,Integer> producer = new KafkaProducer<String,Integer>(props) ;
		for (int x = 0 ; x < 100 ; x ++) {
			producer.send(new ProducerRecord<String,Integer>(TOPIC,"wzy-" + x,x)) ;
		}
		long end = System.currentTimeMillis() ;
		System.out.println("*** 消息发送完成：" + (end - start));
		producer.close(); 
	}

}
