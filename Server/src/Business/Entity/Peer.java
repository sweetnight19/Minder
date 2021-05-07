package Business.Entity;

import java.io.Serializable;
import java.util.Date;

public class Peer implements Serializable {
    private final int idSource;
    private final int idDestiny;
    private final boolean matchDuo;
    private final Date date;

    public Peer(int idSource, int idDestiny, boolean matchDuo) {
        this.idSource = idSource;
        this.idDestiny = idDestiny;
        this.matchDuo = matchDuo;
        this.date = new Date();
    }

    public Peer(int idSource, int idDestiny, boolean matchDuo, Date data) {
        this.idSource = idSource;
        this.idDestiny = idDestiny;
        this.matchDuo = matchDuo;
        this.date = data;
    }

    public int getIdSource() {
        return idSource;
    }

    public int getIdDestiny() {
        return idDestiny;
    }

    public boolean isMatchDuo() {
        return matchDuo;
    }

    public Date getDate() {
        return date;
    }
}
