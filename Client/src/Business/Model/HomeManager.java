package Business.Model;

import Business.Entity.Peer;
import Business.Entity.User;
import Persistance.ConnectionDAO;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The type Home manager.
 */
public class HomeManager {
    private final ConnectionDAO connectionDAO;
    private ArrayList<User> arrayNextUsers;
    private int countPremium;

    /**
     * Instantiates a new Home manager.
     *
     * @param connectionDAO the connection dao
     */
    public HomeManager(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
        arrayNextUsers = new ArrayList<>();
    }

    /**
     * Get next users.
     */
    public void getNextUsers() {
        arrayNextUsers = null;
        User myUser = GlobalUser.getInstance().getMyUser();
        arrayNextUsers = connectionDAO.getRandomUsers(myUser);
        for (int i = 0; i < arrayNextUsers.size(); i++) {
            if (arrayNextUsers.get(i).getId() == GlobalUser.getInstance().getMyUser().getId()) {
                arrayNextUsers.remove(i);
            }
        }
        if (GlobalUser.getInstance().getMyUser().getType().equals("Premium")) {
            this.countPremium = this.connectionDAO.countPremium(GlobalUser.getInstance().getMyUser());
        }
    }

    /**
     * Gets next user.
     *
     * @param posicion the posicion
     * @return the next user
     */
    public User getNextUser(int posicion) {
        return arrayNextUsers.get(posicion);
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return arrayNextUsers.size();
    }

    /**
     * Get count premium.
     *
     * @return the int
     */
    public int getCountPremium() {
        return this.countPremium;
    }

    /**
     * Get next image.
     *
     * @param nextUser the next user
     * @return the buffered image
     */
    public BufferedImage getNextImage(User nextUser) {
        return this.connectionDAO.readImage(nextUser);
    }

    /**
     * Insert like.
     *
     * @param posicion the posicion
     */
    public void insertLike(int posicion) {
        Peer peer = new Peer(GlobalUser.getInstance().getMyUser().getId(), arrayNextUsers.get(posicion).getId(), false);
        this.connectionDAO.insertLike(peer);
    }
}
