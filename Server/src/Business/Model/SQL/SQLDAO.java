package Business.Model.SQL;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Business.Entity.User;
import Business.Model.SQLDAOinterface;
import Persistance.ConfigurationDAO;
import Persistance.JsonConfigurationDAO;

public class SQLDAO implements SQLDAOinterface {
    private ConfigurationDAO confDAO;

    public SQLDAO() throws IOException {
        confDAO = new JsonConfigurationDAO("Server/Data/configuracio-servidor.json");
    }

    @Override
    public List<User> getAllTodos() {

        List<User> users = new List<>();
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
                users.add(new User(uuid,nomPila, nickname, edat,tipusCompte,email,password,pathImage,descripcio,llenguatgeDeProgramacio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        /*
         * String id = UUID.randomUUID().toString(); String query =
         * "INSERT INTO Todo(id, text, completed) VALUES ('" + id + "', '" + text +
         * "', '" + 0 + "');"; SQLConnector.getInstance().insertQuery(query); return id;
         */

    }

    @Override
    public void deleteUser(User user) {
        /*
         * String query = "DELETE FROM Todo WHERE ID='" + id + "';";
         * SQLConnector.getInstance().deleteQuery(query);
         */
    }

    @Override
    public boolean toogleMatch() {
        /*
         * String query = "UPDATE Todo SET completed = !completed WHERE ID='" + id +
         * "';"; SQLConnector.getInstance().updateQuery(query);
         * 
         * query = "SELECT completed FROM Todo WHERE ID='" + id + "';"; ResultSet result
         * = SQLConnector.getInstance().selectQuery(query);
         * 
         * try { result.next(); return result.getBoolean("completed"); } catch
         * (SQLException e) { e.printStackTrace(); return false; }
         */
        return false;
    }

}
