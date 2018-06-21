package cz.muni.fi.pv243.ars.websocket;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by jsmolar on 6/21/18.
 */
public class MessageDecoder implements Decoder.Text<Message>{

    @Override
    public Message decode(String s) throws DecodeException {
        Message message = new Message();
        JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
        message.setSender(jsonObject.getString("sender"));
        message.setComment(jsonObject.getString("comment"));

        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
