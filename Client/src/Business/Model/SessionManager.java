package Business.Model;

import Business.Entity.User;
import Persistance.ConnectionDAO;

public class SessionManager {
    private final ConnectionDAO connectionDAO;

    public SessionManager(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
    }

    public void login(User cliente) {
        if (connectionDAO.validateLogin(cliente)) {
            switch (connectionDAO.checklogin(cliente)) {
                case 0:
                    System.out.println("Login correcte, primer cop");
                    break;
                case 1:
                    System.out.println("login correcte, usuari reincident");
                    break;
                case -1:
                    System.out.println("error en el servidor");
                    break;
            }
        } else {
            System.out.println("login incorrecte");
        }
    }

    public boolean register(User cliente) {
        if (connectionDAO.registerUser(cliente)) {
            return true;
        } else {
            System.out.println("error en el servidor");
            return false;
        }
    }
    public void disconnect(){
        connectionDAO.disconnectFromServer();
    }
}
