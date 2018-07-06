package cz.muni.fi.pv243.ars.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by jsmolar on 6/21/18.
 */
@ServerEndpoint(value = "/comments/{offer_id}")
public class OfferCommentsWS {

    @Inject
    private Logger log;

//    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    private static Map<Integer, Set> offers = Collections.synchronizedMap(new HashMap<Integer, Set>());

    @OnOpen
    public void onOpen(@PathParam("offer_id") Integer offer_id, Session session) {
        log.info("New websocket session opened: " + session.getId() + " for Offer: " + offer_id);

        if(offers.containsKey(offer_id)) {
            offers.get(offer_id).add(session);
        } else {
            Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
            clients.add(session);
            offers.put(offer_id, clients);
        }
    }

    @OnMessage
    public String onMessage(@PathParam("offer_id") Integer offer_id,
        String message, Session session) throws IOException, EncodeException {

        log.info("Web socket message" + message + "received from " + session.getId() + " from Offer: " + offer_id);

        Set<Session> clients = offers.get(offer_id);

        for(Session client : clients) {
            if(!session.getId().equals(client.getId())) {
                client.getBasicRemote().sendObject(message);
            }
        }

        return message;
    }

    @OnClose
    public void onClose(@PathParam("offer_id") Integer offer_id, Session session) {
        log.info("Websocket session closed: " + session.getId() + " from Offer: " + offer_id);

        Set<Session> clients = offers.get(offer_id);
        clients.remove(session);
    }

}
