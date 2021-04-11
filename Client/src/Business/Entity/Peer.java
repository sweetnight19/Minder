package Business.Entity;

import java.util.Date;

public class Peer {
    private int idSource;
    private int idDestiny;
    private boolean matchDuo;
    private Date date;

    public Peer(int idSource, int idDestiny, boolean matchDuo) {
        this.idSource = idSource;
        this.idDestiny = idDestiny;
        this.matchDuo = matchDuo;
        this.date = new Date();
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
