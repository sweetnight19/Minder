package Persistance.SQL;

import Business.Entity.User;
import Persistance.ConfigurationDAO;
import Persistance.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Sql user dao.
 */
public class SQLUserDAO implements UserDAO {
    private final ConfigurationDAO confDAO;

    /**
     * Instantiates a new Sql user dao.
     *
     * @param configurationDAO the configuration dao
     */
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

    @Override
    public int addUser(User user) {
        ResultSet result;
        String uuidQuery = "SELECT `uuid` FROM `usuari` WHERE `nickname`= '" + user.getNickname() + "';";
        String query = "INSERT INTO `usuari` (`nomPila`, `nickname`, `edat`, `tipusCompte`, `email`, `password`, `pathImage`, `descripcio`, `llenguatgeDeProgramacio`) VALUES ('"
                + user.getFirstName() + "', '" + user.getNickname() + "', '" + user.getAge() + "', '" + user.getType()
                + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getPathImage() + "', '"
                + user.getDescription() + "', '" + user.getProgrammingLanguage() + "');";

        if (!SQLConnector.getInstance(confDAO).insertQuery(query)) {
            return -1;
        }
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
    public boolean deleteUser(User user) {
        String query = "DELETE FROM `usuari` WHERE `uuid`='" + user.getId() + "';";

        return SQLConnector.getInstance(confDAO).deleteQuery(query);
    }

    @Override
    public boolean updateUser(User user) {
        String query = "UPDATE `usuari` SET `nomPila`= '" + user.getFirstName() + "',`nickname`= '" + user.getNickname()
                + "',`edat`=" + user.getAge() + ",`uuid`=" + user.getId() + ",`tipusCompte`= '" + user.getType()
                + "',`email`= '" + user.getEmail() + "' ,`password`= '" + user.getPassword() + "',`pathImage`= '"
                + user.getPathImage() + "' ,`descripcio`= '" + user.getDescription() + "' ,`llenguatgeDeProgramacio`= '"
                + user.getProgrammingLanguage() + "' ,`status`= '1' WHERE `uuid`= "
                + user.getId() + ";";
        return SQLConnector.getInstance(confDAO).updateQuery(query);
    }

    @Override
    public boolean validationLogin(User user) {
        String query = "SELECT `password` FROM `usuari` WHERE `nickname`= '" + user.getNickname() + "';";
        ResultSet result;

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
        String top5Query = "SELECT *,COUNT(DISTINCT(pair.idDesti)) as matches FROM usuari,pair WHERE pair.idDesti=usuari.uuid GROUP BY idOrigen ORDER BY COUNT(pair.idDesti) DESC LIMIT 5";

        ResultSet result = SQLConnector.getInstance(confDAO).selectQuery(top5Query);
        try {
            while (result.next()) {
                top5.add(new User(result.getInt("uuid"), result.getString("nomPila"), result.getString("nickname"),
                        result.getInt("edat"), result.getString("tipusCompte"), result.getString("email"),
                        result.getString("password"), result.getString("pathImage"), result.getString("descripcio"),
                        result.getString("llenguatgeDeProgramacio"), result.getInt("matches")));
            }
            return top5;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUser(String nickname) {
        String query = "SELECT * FROM `usuari` WHERE `nickname`= '" + nickname + "';";

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
        //SELECT * FROM usuari WHERE usuari.llenguatgeDeProgramacio="Rust" AND usuari.uuid NOT IN (SELECT pair.idDesti FROM pair WHERE pair.idOrigen =7) AND usuari.uuid IN (SELECT pair.idOrigen FROM pair WHERE pair.idDesti=7) UNION SELECT * FROM usuari WHERE usuari.llenguatgeDeProgramacio="Rust" AND usuari.uuid NOT IN (SELECT pair.idDesti FROM pair WHERE pair.idOrigen =7) AND usuari.uuid NOT IN (SELECT pair.idOrigen FROM pair WHERE pair.idDesti=7)
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM usuari WHERE usuari.llenguatgeDeProgramacio LIKE '" + user.getProgrammingLanguage() + "' AND usuari.uuid NOT IN (SELECT pair.idDesti FROM pair WHERE pair.idOrigen =" + user.getId() + ") AND usuari.uuid IN (SELECT pair.idOrigen FROM pair WHERE pair.idDesti=" + user.getId() + ") UNION SELECT * FROM usuari WHERE usuari.llenguatgeDeProgramacio LIKE '" + user.getProgrammingLanguage() + "' AND usuari.uuid NOT IN (SELECT pair.idDesti FROM pair WHERE pair.idOrigen =" + user.getId() + ") AND usuari.uuid NOT IN (SELECT pair.idOrigen FROM pair WHERE pair.idDesti=" + user.getId() + ");";

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

    @Override
    public ArrayList<User> getPretendents(User user) {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM usuari WHERE usuari.llenguatgeDeProgramacio LIKE '" + user.getProgrammingLanguage() + "' AND usuari.uuid NOT IN (SELECT pair.idDesti FROM pair WHERE pair.idOrigen =" + user.getId() + ");";

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

    @Override
    public int countPretendentsPremium(User user) {
        //SELECT COUNT(*) AS total FROM usuari WHERE usuari.llenguatgeDeProgramacio="Rust" AND usuari.uuid NOT IN (SELECT pair.idDesti FROM pair WHERE pair.idOrigen =7) AND usuari.uuid IN (SELECT pair.idOrigen FROM pair WHERE pair.idDesti=7)
        String query = "SELECT COUNT(*) AS total FROM usuari WHERE usuari.llenguatgeDeProgramacio LIKE '" + user.getProgrammingLanguage() + "' AND usuari.uuid NOT IN (SELECT pair.idDesti FROM pair WHERE pair.idOrigen =" + user.getId() + ") AND usuari.uuid IN (SELECT pair.idOrigen FROM pair WHERE pair.idDesti=" + user.getId() + ")";
        ResultSet result;

        result = SQLConnector.getInstance(confDAO).selectQuery(query);
        try {
            result.next();
            return result.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int checkLoginIntent(User user) {
        String query = "SELECT `status` FROM `usuari` WHERE `nickname` = '" + user.getNickname() + "';";
        ResultSet result;

        result = SQLConnector.getInstance(confDAO).selectQuery(query);
        try {
            result.next();
            return result.getInt("status");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}


