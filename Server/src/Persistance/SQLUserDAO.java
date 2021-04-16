package Persistance;

import Business.Entity.User;
import Persistance.SQL.SQLConnector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class SQLUserDAO implements UserDAO {
    private final ConfigurationDAO confDAO;

    public SQLUserDAO(ConfigurationDAO configurationDAO) throws IOException {
        confDAO = configurationDAO;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM usuari;";

        ResultSet result = SQLConnector.getInstance(confDAO).selectQuery(query);
        try {
            while (result.next()) {
                int uuid = result.getInt("uuid");
                String nomPila = result.getString("nomPila");
                String nickname = result.getString("nickname");
                int edat = result.getInt("edat");
                String tipusCompte = result.getString("tipusCompte");
                String email = result.getString("email");
                String password = result.getString("password");
                String pathImage = result.getString("pathImage");
                String descripcio = result.getString("descripcio");
                String llenguatgeDeProgramacio = result.getString("llenguatgeDeProgramacio");
                users.add(new User(uuid, nomPila, nickname, edat, tipusCompte, email, password, pathImage, descripcio,
                        llenguatgeDeProgramacio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }

    @Override
    public int addUser(User user) {
        ResultSet result;
        String uuidQuery = "SELECT `uuid` FROM `usuari` WHERE `nickname`= '" + user.getNickname() + "';";
        String query = "INSERT INTO `usuari` (`nomPila`, `nickname`, `edat`, `tipusCompte`, `email`, `password`, `pathImage`, `descripcio`, `llenguatgeDeProgramacio`) VALUES ('"
                + user.getFirstName() + "', '" + user.getNickname() + "', '" + String.valueOf(user.getAge()) + "', '"
                + user.getType() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getPathImage()
                + "', '" + user.getDescription() + "', '" + user.getProgrammingLanguage() + "');";

        SQLConnector.getInstance(confDAO).insertQuery(query);
        result = SQLConnector.getInstance(confDAO).selectQuery(uuidQuery);
        try {
            result.next();
            return result.getInt("uuid");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM `usuari` WHERE `uuid`='" + id + "';";
        SQLConnector.getInstance(confDAO).deleteQuery(query);
    }
}
