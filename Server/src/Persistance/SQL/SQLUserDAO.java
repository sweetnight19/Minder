package Persistance.SQL;

import Business.Entity.User;
import Persistance.ConfigurationDAO;
import Persistance.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLUserDAO implements UserDAO {
    private final ConfigurationDAO confDAO;

    public SQLUserDAO(ConfigurationDAO configurationDAO) {
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
                + user.getFirstName() + "', '" + user.getNickname() + "', '" + user.getAge() + "', '" + user.getType()
                + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getPathImage() + "', '"
                + user.getDescription() + "', '" + user.getProgrammingLanguage() + "');";

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

    @Override
    public void updateTypeUser(int uuid, String type) {
        String query = "UPDATE `usuari` SET `tipusCompte` = '" + type + "' WHERE `usuari`.`uuid` = '" + uuid + "';";
        SQLConnector.getInstance(confDAO).updateQuery(query);
    }

    @Override
    public void updateDescription(int uuid, String descripcio) {
        String query = "UPDATE `usuari` SET `descripcio` = '" + descripcio + "' WHERE `usuari`.`uuid` = '" + uuid
                + "';";
        SQLConnector.getInstance(confDAO).updateQuery(query);

    }

    @Override
    public void updateImage(int uuid, String pathImage) {
        String query = "UPDATE `usuari` SET `pathImage` = '" + pathImage + "' WHERE `usuari`.`uuid` = '" + uuid + "';";
        SQLConnector.getInstance(confDAO).updateQuery(query);

    }

    @Override
    public void updateProgammingLanguage(int uuid, String language) {
        String query = "UPDATE `usuari` SET `llenguatgeDeProgramacio` = '" + language + "' WHERE `usuari`.`uuid` = '"
                + uuid + "';";
        SQLConnector.getInstance(confDAO).updateQuery(query);

    }

    @Override
    public boolean validadionLogin(int uuid, String password) {
        String query = "SELECT `password` FROM `usuari` WHERE `uuid` = " + uuid + ";";
        ResultSet result;

        SQLConnector.getInstance(confDAO).selectQuery(query);
        result = SQLConnector.getInstance(confDAO).selectQuery(query);
        try {
            result.next();
            return password.equals(result.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<User> top5() {
        ArrayList<User> top5 = new ArrayList<>();
        String top5Query = "SELECT *,COUNT(pair.idDesti) FROM usuari,pair WHERE pair.idDesti=usuari.uuid GROUP BY idOrigen ORDER BY COUNT(pair.idDesti) DESC LIMIT 5";

        ResultSet result = SQLConnector.getInstance(confDAO).selectQuery(top5Query);
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
                top5.add(new User(uuid, nomPila, nickname, edat, tipusCompte, email, password, pathImage, descripcio,
                        llenguatgeDeProgramacio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return top5;
    }
}
