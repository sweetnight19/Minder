package Persistance.SQL;

import Business.Entity.Peer;
import Business.Entity.User;
import Persistance.ConfigurationDAO;
import Persistance.PeerDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void addLike(Peer peer) {

        String query = "INSERT INTO `pair` (`idOrigen`, `idDesti`, `matchDuo`, `data`) VALUES ('" + peer.getIdSource()
                + "', '" + peer.getIdDestiny() + "', '" + 1 + "', '" + peer.getDate() + "');";

        SQLConnector.getInstance(confDAO).insertQuery(query);

    }

    @Override
    public void deletePeer(Peer peer) {
        String query1 = "DELETE FROM `pair` WHERE `idOrigen`= " + peer.getIdSource() + " AND `idDesti` = "
                + peer.getIdDestiny() + ";";
        String query2 = "DELETE FROM `pair` WHERE `idOrigen`= " + peer.getIdDestiny() + " AND `idDesti` = "
                + peer.getIdSource() + ";";
        SQLConnector.getInstance(confDAO).deleteQuery(query1);
        SQLConnector.getInstance(confDAO).deleteQuery(query2);

    }

    @Override
    public ArrayList<User> getUserPeers(User user) {
        // TODO
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM usuari JOIN pair a JOIN pair b ON a.idDesti=b.idOrigen AND a.idOrigen=b.idDesti AND a.idOrigen=usuari.uuid HAVING a.idDesti="
                + user.getId() + ";";

        ResultSet result = SQLConnector.getInstance(confDAO).selectQuery(query);
        try {
            while (result.next()) {
                users.add(new User(result.getInt("uuid"), result.getString("nomPila"), result.getString("nickname"),
                        result.getInt("edat"), result.getString("tipusCompte"), result.getString("email"),
                        result.getString("password"), result.getString("pathImage"), result.getString("descripcio"),
                        result.getString("llenguatgeDeProgramacio")));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
