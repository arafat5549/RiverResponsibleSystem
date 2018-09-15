package com.jqm.ssm.misc.es;

import com.jqm.ssm.util.ReflectionUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * MQTT消息发送与接收
 * MQTT用来接收传感器传过来的数据
 *
 * QOS:
 0 ：消息最多被传递一次，比如一般类广告，通知
 1 ：消息会被传递但可能会重复传递，比如账户余额通知
 2 ：消息保证传递且仅有一次传递，比如交易支付批复通知
 * @author Join
 *
 */
@Component
//@Lazy(false)
public class MqttBroker {

    private final static Logger logger = LoggerFactory.getLogger(MqttBroker.class);// 日志对象

    // 连接参数
    //private final static String CONNECTION_STRING = "tcp://119.29.238.193:1883";//tcp://192.168.226.91:1883";
    private final static boolean CLEAN_START = true;
    private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s
    //private final static String CLIENT_ID = "master";// 客户端标识
    //private final static int[] QOS_VALUES = { 0 };// 对应主题的消息级别
    // private final static String[] TOPICS = { "topic_cctt_mqtt" };
    public final static String TOPICS= "topic_cctt_mqtt";
    private static MqttBroker instance;
    MemoryPersistence persistence = new MemoryPersistence();

    private MqttClient mqttClient;
    private String connectUrl ;
    private String clientId;

    KafkaDataProducer kafakProducer = null;

    public MqttBroker(){
        this.clientId = "master";
        this.connectUrl ="tcp://"+ELKConstants.HOST_MQTT+":1883";
        //System.out.println("MqttBroker:"+this.clientId+","+this.connectUrl);
    }
    public MqttBroker(String clientId,String connectUrl){
        this.clientId = clientId;
        this.connectUrl = connectUrl;
    }


    /**
     * 返回实例对象
     *
     * @return
     */
    public static MqttBroker getInstance() {
        if(instance == null)
            instance =  new MqttBroker();
        return instance;
    }

    /**
     * 重新连接服务
     */
    private void connect() throws MqttException {
        logger.info("connect to mqtt broker.");
        if(mqttClient ==null)
        {
            mqttClient = new MqttClient(connectUrl,clientId,persistence);
            SimpleCallbackHandler simpleCallbackHandler = new SimpleCallbackHandler();
            mqttClient.setCallback(simpleCallbackHandler);
        }
        //mqttClient.connect(CLIENT_ID, CLEAN_START, KEEP_ALIVE);
       // mqttClient.connect();
        MqttConnectOptions options = getOptions();
        if (!mqttClient.isConnected()) {
            mqttClient.connect(options);
            System.out.println("连接成功");
        }else {//这里的逻辑是如果连接成功就重新连接
            mqttClient.disconnect();
            mqttClient.connect(options);
            System.out.println("连接成功");
        }

        mqttClient.subscribe(TOPICS,0);// 订阅接主题
        logger.info("***********subscribe receiver topics***********"+TOPICS);
        logger.info("***********CLIENT_ID:" + clientId);
        /**
         * 完成订阅后，可以增加心跳，保持网络通畅，也可以发布自己的消息
         */
        mqttClient.publish("keepalive", "keepalive".getBytes(),0,true);// 增加心跳，保持网络通畅

        logger.info("***********create kafakProducer***********");
        //kafakProducer = new KafkaDataProducer();
    }

    //生成配置对象，用户名，密码等
    public MqttConnectOptions getOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        // options.setUserName(account);
        //options.setPassword(password.toCharArray());
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        return options;
    }

    /**
     * 发送消息
     *
     * @param clientId
     * @param message
     */
    public void sendMessage(String clientId, String message) {
        try {
            if (mqttClient == null || !mqttClient.isConnected()) {
                connect();
            }

            logger.info("send message to " + clientId + ", message is " + message);
            // 发布自己的消息 "GMCC/client/" +
            mqttClient.publish( clientId, message.getBytes(),0, false);
        } catch (MqttException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 简单回调函数，处理server接收到的主题消息
     *
     * @author Join
     *
     */
    class SimpleCallbackHandler implements MqttCallback {

        /**
         * 当客户机和broker意外断开时触发 可以再此处理重新订阅
         */
        @Override
        public void connectionLost(Throwable t) {
            logger.error("客户机和broker已经断开");
             try {
                if (mqttClient == null || !mqttClient.isConnected())
                        connect();
                } catch (MqttException e) {
                    e.printStackTrace();
             }

        }
        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {

            // if(!message.toString().contains("debugmsg"))
            {
                logger.info(topic);
                System.out.println("Received Message from broker:"+topic);
                TestTrackingData m =ReflectionUtil.parseTackingDate(message.toString(),TestTrackingData.class);
               /// kafakProducer.publish(ELKConstants.TOPIC_KAFAKA, message.toString());//发送给kafka
                System.out.println("Publish to Kafka:" + message.toString());
            }
        }


        public void deliveryComplete(IMqttDeliveryToken token) {
            try {
                logger.info( "deliveryComplete: "+ new String(token.getMessage().getPayload()));
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args)
    {
        // new MqttBroker().sendMessage("client", "message");
        new MqttBroker("mqttclient/0","tcp://119.29.238.193:1883").sendMessage(MqttBroker.TOPICS,"sosomesg");
    }
}
