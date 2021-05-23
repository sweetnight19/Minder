package Persistance.SQL;

import Business.Entity.ChatMessage;
import Business.Entity.User;
import Persistance.ChatDAO;
import Persistance.ConfigurationDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * The type Sql chat dao.
 */
public class SQLChatDAO implements ChatDAO {
    private final ConfigurationDAO confDAO;

    /**
     * Instantiates a new Sql chat dao.
     *
     * @param configurationDAO the configuration dao
     */
    public SQLChatDAO(ConfigurationDAO configurationDAO) {
        confDAO = configurationDAO;
    }

    @Override
    public ArrayList<ChatMessage> getAllXats(User user1, User user2) {
        ArrayList<ChatMessage> chats = new ArrayList<>();
        String query = "SELECT * FROM `xat` WHERE (`idOrigen` LIKE " + user1.getId() + " AND `idDesti` LIKE "
                + user2.getId() + ") OR (`idOrigen` LIKE " + user2.getId() + " AND `idDesti` LIKE " + user1.getId()
                + ")";

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
    public boolean addMessage(ChatMessage chatMessage) {
        String query = "INSERT INTO `xat` (`idOrigen`, `idDesti`, `missatge`) VALUES ('"
                + chatMessage.getIdSource() + "', '" + chatMessage.getIdDestiny() + "', '" + chatMessage.getMessage()
                + "');";

        return SQLConnector.getInstance(confDAO).insertQuery(query);

    }

    @Override
    public boolean deleteChat(User user1, User user2) {
        String query = "DELETE FROM `xat` WHERE (`idOrigen` LIKE " + user1.getId() + " AND `idDesti` LIKE "
                + user2.getId() + ") OR (`idOrigen` LIKE " + user2.getId() + " AND `idDesti` LIKE " + user1.getId()
                + ")";
        return SQLConnector.getInstance(confDAO).deleteQuery(query);
    }
}
