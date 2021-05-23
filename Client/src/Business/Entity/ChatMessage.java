package Business.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Chat message.
 */
public class ChatMessage implements Serializable {
    private final int idSource;
    private final int idDestiny;
    private final String message;
    private Date date;

    /**
     * Instantiates a new Chat message.
     *
     * @param idSource  the id source
     * @param idDestiny the id destiny
     * @param message   the message
     */
    public ChatMessage(int idSource, int idDestiny, String message) {
        this.idSource = idSource;
        this.idDestiny = idDestiny;
        this.message = message;
    }

    /**
     * Instantiates a new Chat message.
     *
     * @param idSource  the id source
     * @param idDestiny the id destiny
     * @param message   the message
     * @param date      the date
     */
    public ChatMessage(int idSource, int idDestiny, String message, Date date) {
        this.idSource = idSource;
        this.idDestiny = idDestiny;
        this.message = message;
        this.date = date;
    }

    /**
     * Gets id source.
     *
     * @return the id source
     */
    public int getIdSource() {
        return idSource;
    }

    /**
     * Gets id destiny.
     *
     * @return the id destiny
     */
    public int getIdDestiny() {
        return idDestiny;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }
}
