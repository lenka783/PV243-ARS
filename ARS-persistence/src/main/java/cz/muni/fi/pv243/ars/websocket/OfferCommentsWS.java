package cz.muni.fi.pv243.ars.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by jsmolar on 6/21/18.
 */
@ServerEndpoint(value = "/offer/comments")
public class OfferCommentsWS {

    @Inject
    private Logger log;

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        log.info("New websocket session opened: " + session.getId());
        clients.add(session);
    }

    @OnMessage
    public String onMessage(String message, Session session) throws IOException, EncodeException {
        log.info("Web socket message" + message + "received from " + session.getId());

        for(Session client : clients) {
            if(!session.getId().equals(client.getId())) {
                client.getBasicRemote().sendObject(message);
            }
        }

        return message;
    }

    @OnClose
    public void onClose(Session session) {
        log.info("Websocket session closed: " + session.getId());
        clients.remove(session);
    }

}
