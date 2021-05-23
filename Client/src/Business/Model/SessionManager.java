package Business.Model;

import Business.Entity.User;
import Persistance.ConnectionDAO;

import java.awt.image.BufferedImage;

/**
 * The type Session manager.
 */
public class SessionManager {
    private final ConnectionDAO connectionDAO;
    private static final int ERROR = -1;
    private static final int CORRECT_FIRST_TIME = 0;
    private static final int CORRECT_NOT_FIRST_TIME = 1;

    /**
     * Instantiates a new Session manager.
     *
     * @param connectionDAO the connection dao
     */
    public SessionManager(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
    }

    /**
     * Login int.
     *
     * @param cliente the cliente
     * @return the int
     */
    public int login(User cliente) {
        if (connectionDAO.validateLogin(cliente)) {
            switch (connectionDAO.checklogin(cliente)) {
                case CORRECT_FIRST_TIME:
                    return CORRECT_FIRST_TIME;
                case CORRECT_NOT_FIRST_TIME:
                    return CORRECT_NOT_FIRST_TIME;
                case ERROR:
                    return ERROR;
            }
        } else {
            System.out.println("login incorrecte");
        }
        return -2;

    }

    /**
     * Register boolean.
     *
     * @param cliente the cliente
     * @return the boolean
     */
    public boolean register(User cliente) {
        if (connectionDAO.registerUser(cliente)) {
            return true;
        } else {
            System.out.println("error en el servidor");
            return false;
        }
    }

    /**
     * Save global user.
     *
     * @param cliente the cliente
     */
    public void saveGlobalUser(User cliente) {
        User user = this.connectionDAO.readUser(cliente);
        if (user != null) {
            GlobalUser.getInstance().setMyUser(user);
        }
    }

    /**
     * Save new image.
     *
     * @param image the image
     */
    public void saveNewImage(BufferedImage image) {
        if (this.connectionDAO.sendImage(GlobalUser.getInstance().getMyUser(), image)) {
            GlobalUser.getInstance().getMyUser().setPathImage(GlobalUser.getInstance().getMyUser().getNickname() + ".jpg");
        }
    }

    /**
     * Disconnect.
     */
    public void disconnect() {
        connectionDAO.disconnectFromServer();
    }

    /**
     * Update user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean updateUser(User user) {
        return connectionDAO.updateUser(user);
    }
}
