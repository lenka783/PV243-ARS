package cz.muni.fi.pv243.ars.websocket;

/**
 * Created by jsmolar on 6/21/18.
 */
public class Message {

    private String sender;

    private String comment;

    public Message() {

    }

    public Message(String sender, String comment) {
        this.sender = sender;
        this.comment = comment;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
