package Persistance.SQL;

import Business.Entity.ChatMessage;
import Persistance.ChatDAO;
import Persistance.ConfigurationDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQLChatDAO implements ChatDAO {
    private final ConfigurationDAO confDAO;

    public SQLChatDAO(ConfigurationDAO configurationDAO) {
        confDAO = configurationDAO;
    }

    @Override
    public ArrayList<ChatMessage> getAllXats(int id1, int id2) {
        ArrayList<ChatMessage> chats = new ArrayList<>();
        String query = "SELECT * FROM `xat` WHERE (`idOrigen` LIKE " + id1 + " AND `idDesti` LIKE " + id2
                + ") OR (`idOrigen` LIKE " + id2 + " AND `idDesti` LIKE " + id1 + ")";

        ResultSet result = SQLConnector.getInstance(confDAO).selectQuery(query);
        try {
            while (result.next()) {
                int idSource = result.getInt("idOrigen");
                int idDestiny = result.getInt("idDesti");
                String message = result.getString("missatge");
                Date data = result.getDate("data");
                chats.add(new ChatMessage(idSource, idDestiny, message, data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return chats;
    }

    @Override
    public void addMessage(int id1, int id2, String message) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String query = "INSERT INTO `xat` (`idOrigen`, `idDesti`, `missatge`, `data`) VALUES ('" + id1 + "', '" + id2
                + "', '" + message + "', '" + dateFormat.format(date) + "');";

        SQLConnector.getInstance(confDAO).insertQuery(query);

    }

    @Override
    public void deleteChat(int id1, int id2) {
        String query = "DELETE FROM `xat` WHERE (`idOrigen` LIKE " + id1 + " AND `idDesti` LIKE " + id2
                + ") OR (`idOrigen` LIKE " + id2 + " AND `idDesti` LIKE " + id1 + ")";
        SQLConnector.getInstance(confDAO).deleteQuery(query);
    }
}
