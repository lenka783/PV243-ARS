package cz.muni.fi.pv243.ars.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;

import javax.json.Json;
import javax.websocket.EndpointConfig;

/**
 * Created by jsmolar on 6/21/18.
 */
public class MessageEncoder implements Encoder.Text<Message>{

    @Override
    public String encode(Message message) throws EncodeException {
        return Json.createObjectBuilder()
            .add("sender", message.getSender())
            .add("comment", message.getComment())
            .build()
            .toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
