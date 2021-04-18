package Persistance.SQL;

import Business.Entity.Peer;
import Persistance.ConfigurationDAO;
import Persistance.PeerDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQLPeerDAO implements PeerDAO {
    private final ConfigurationDAO confDAO;

    public SQLPeerDAO(ConfigurationDAO configurationDAO) {
        confDAO = configurationDAO;
    }

    @Override
    public ArrayList<Peer> getAllPeers() {
        ArrayList<Peer> peers = new ArrayList<>();
        String query = "SELECT * FROM pair;";

        ResultSet result = SQLConnector.getInstance(confDAO).selectQuery(query);
        try {
            while (result.next()) {
                int id1 = result.getInt("idOrigen");
                int id2 = result.getInt("idDesti");
                boolean matchDuo = result.getBoolean("matchDuo");
                Date data = result.getDate("data");
                peers.add(new Peer(id1, id2, matchDuo, data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return peers;
    }

    @Override
    public void addLike(int id1, int id2) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String query = "INSERT INTO `pair` (`idOrigen`, `idDesti`, `matchDuo`, `data`) VALUES ('" + id1 + "', '" + id2
                + "', '" + 1 + "', '" + dateFormat.format(date) + "');";

        SQLConnector.getInstance(confDAO).insertQuery(query);

    }

    @Override
    public void deletePeer(int id1, int id2) {
        String query1 = "DELETE FROM `pair` WHERE `idOrigen`= " + id1 + " AND `idDesti` = " + id2 + ";";
        String query2 = "DELETE FROM `pair` WHERE `idOrigen`= " + id2 + " AND `idDesti` = " + id1 + ";";
        SQLConnector.getInstance(confDAO).deleteQuery(query1);
        SQLConnector.getInstance(confDAO).deleteQuery(query2);

    }

    /*
     * @Override public void tooglePeer(int id1, int id2) { String query1 =
     * "UPDATE `pair` SET `matchDuo`= 1 WHERE `idOrigen`="+id1+" AND `idDesti`= "
     * +id2+";"; String query2 =
     * "UPDATE `pair` SET `matchDuo`= 1 WHERE `idOrigen`="+id2+" AND `idDesti`= "
     * +id1+";";
     *
     * SQLConnector.getInstance(confDAO).updateQuery(query1);
     * SQLConnector.getInstance(confDAO).updateQuery(query2); }
     */
}
