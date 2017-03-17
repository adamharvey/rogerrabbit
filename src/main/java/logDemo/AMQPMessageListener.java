package logDemo;

import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by adam.harvey on 12/15/16.
 */
public class AMQPMessageListener implements MessageListener {

    @Autowired
    private AmqpTemplate responseTemplate;

    private Map<String,Object> returnMessage;

    public Map<String, Object> getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(Map<String, Object> returnMessage) {
        this.returnMessage = returnMessage;
    }

    public void setResponseTemplate(AmqpTemplate responseTemplate) { this.responseTemplate = responseTemplate; }

    public AmqpTemplate getResponseTemplate() { return responseTemplate; }

    public void onMessage(Message message) {
        String messageBody = new String(message.getBody());
        System.out.println("onMessage:  " + messageBody + " : Message received!");
        returnMessage = new LinkedHashMap<String, Object>();

        returnMessage = new Gson().fromJson(messageBody, LinkedHashMap.class);

        sendMessage(returnMessage);
    }

    public void sendMessage(Map<String,Object> map) {
        responseTemplate.convertAndSend(new Gson().toJson(map).getBytes());
        System.out.println( "Sent message:  " + new Gson().toJson(map).toString());
    }
}