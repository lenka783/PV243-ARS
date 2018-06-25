package cz.muni.fi.pv243.ars.messaging;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class Sender {

    @Resource(mappedName = "java:jboss/exported/jms/queue/dataQueue")
    private Queue dataQueue;

    @Inject
    JMSContext context;

    public void sendMessage(String message) {
        try {
            context.createProducer().send(dataQueue, message);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}
