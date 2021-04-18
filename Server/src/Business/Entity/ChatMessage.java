package Business.Entity;

import java.util.Date;

public class ChatMessage {
    private int idSource;
    private int idDestiny;
    private String message;
    private Date date;

    public ChatMessage(int idSource, int idDestiny, String message) {
        this.idSource = idSource;
        this.idDestiny = idDestiny;
        this.message = message;
        this.date = new Date();
    }

    public ChatMessage(int idSource, int idDestiny, String message, Date date) {
        this.idSource = idSource;
        this.idDestiny = idDestiny;
        this.message = message;
        this.date = date;
    }

    public int getIdSource() {
        return idSource;
    }

    public int getIdDestiny() {
        return idDestiny;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
