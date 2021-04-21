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
    public void deleteUser(User user) {
        String query = "DELETE FROM `usuari` WHERE `uuid`='" + user.getId() + "';";
        SQLConnector.getInstance(confDAO).deleteQuery(query);
    }

    @Override
    public void updateUser(User user) {
        String query = "UPDATE `usuari` SET `nomPila`= '" + user.getFirstName() + "',`nickname`= '" + user.getNickname()
                + "',`edat`=" + user.getAge() + ",`uuid`=" + user.getId() + ",`tipusCompte`= '" + user.getType()
                + "',`email`= '" + user.getEmail() + "' ,`password`= '" + user.getPassword() + "',`pathImage`= '"
                + user.getPathImage() + "' ,`descripcio`= '" + user.getDescription() + "' ,`llenguatgeDeProgramacio`= '"
                + user.getProgrammingLanguage() + "' WHERE `uuid`= " + user.getId() + ";";
        SQLConnector.getInstance(confDAO).updateQuery(query);
    }

    @Override
    public boolean validadionLogin(User user) {
        String query = "SELECT `password` FROM `usuari` WHERE `uuid` = " + user.getId() + ";";
        ResultSet result;

        SQLConnector.getInstance(confDAO).selectQuery(query);
        result = SQLConnector.getInstance(confDAO).selectQuery(query);
        try {
            result.next();
            return user.getPassword().equals(result.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<User> top5() {
        ArrayList<User> top5 = new ArrayList<>();
        String top5Query = "SELECT *,COUNT(DISTINCT(pair.idDesti)) FROM usuari,pair WHERE pair.idDesti=usuari.uuid GROUP BY idOrigen ORDER BY COUNT(pair.idDesti) DESC LIMIT 5";

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

    @Override
    public User getUser(int uuid) {
        String query = "SELECT * FROM usuari WHERE usuari.uuid=" + uuid + ";";

        ResultSet result = SQLConnector.getInstance(confDAO).selectQuery(query);
        try {
            result.next();
            return new User(result.getInt("uuid"), result.getString("nomPila"), result.getString("nickname"),
                    result.getInt("edat"), result.getString("tipusCompte"), result.getString("email"),
                    result.getString("password"), result.getString("pathImage"), result.getString("descripcio"),
                    result.getString("llenguatgeDeProgramacio"));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<User> getPretendentsPremium(User user) {
        // TODO
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM usuari,pair WHERE usuari.llenguatgeDeProgramacio=" + user.getProgrammingLanguage()
                + " AND usuari.uuid=pair.idDesti AND pair.matchDuo=0 HAVING usuari.uuid=" + user.getId() + ";";

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
    public ArrayList<User> getPretendents(User user) {
        // TODO
        return null;
    }
}
