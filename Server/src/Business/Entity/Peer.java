package Business.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Peer.
 */
public class Peer implements Serializable {
    private final int idSource;
    private final int idDestiny;
    private final boolean matchDuo;
    private Date date;

    /**
     * Instantiates a new Peer.
     *
     * @param idSource  the id source
     * @param idDestiny the id destiny
     * @param matchDuo  the match duo
     */
    public Peer(int idSource, int idDestiny, boolean matchDuo) {
        this.idSource = idSource;
        this.idDestiny = idDestiny;
        this.matchDuo = matchDuo;
    }

    /**
     * Instantiates a new Peer.
     *
     * @param idSource  the id source
     * @param idDestiny the id destiny
     * @param matchDuo  the match duo
     * @param data      the data
     */
    public Peer(int idSource, int idDestiny, boolean matchDuo, Date data) {
        this.idSource = idSource;
        this.idDestiny = idDestiny;
        this.matchDuo = matchDuo;
        this.date = data;
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
     * Is match duo boolean.
     *
     * @return the boolean
     */
    public boolean isMatchDuo() {
        return matchDuo;
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
