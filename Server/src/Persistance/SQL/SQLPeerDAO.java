package Persistance.SQL;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Business.Entity.Peer;
import Persistance.ConfigurationDAO;
import Persistance.PeerDAO;

public class SQLPeerDAO implements PeerDAO {

    private final ConfigurationDAO confDAO;

    public SQLPeerDAO(ConfigurationDAO configurationDAO) throws IOException {
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
    public void addPeer(int id1, int id2) {
        String query = "INSERT INTO `pair` (`idOrigen`, `idDesti`, `matchDuo`, `data`) VALUES ('" + id1 + "', '" + id2
                + "', '" + true + "', '" + new java.util.Date() + "');";
                
        SQLConnector.getInstance(confDAO).insertQuery(query);

    }

    @Override
    public void deleteUser(int id1, int id2) {
        // TODO Auto-generated method stub

    }
}
