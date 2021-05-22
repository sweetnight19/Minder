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
    private ConnectionDAO connectionDAO;
    private BufferedImage homeImage;
    private ArrayList<User> arrayNextUsers;
    private int countPremium;

    /**
     * Instantiates a new Home manager.
     *
     * @param connectionDAO the connection dao
     */
    public HomeManager(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
        homeImage = null;
        arrayNextUsers = new ArrayList<>();
    }

    /**
     * Get next users.
     */
// Funcio retorni un array de users amb el mateix llenguatge
    public void getNextUsers(){
        arrayNextUsers = null;
        System.out.println("nickname = " + GlobalUser.getInstance().getMyUser().getNickname());
        System.out.println("language = " + GlobalUser.getInstance().getMyUser().getProgrammingLanguage());
        System.out.println("premium = " + GlobalUser.getInstance().getMyUser().getType());
        User myUser = GlobalUser.getInstance().getMyUser();
        arrayNextUsers = connectionDAO.getRandomUsers(myUser);
        for (int i = 0; i < arrayNextUsers.size(); i++) {
            if(arrayNextUsers.get(i).getId() == GlobalUser.getInstance().getMyUser().getId()){
                arrayNextUsers.remove(i);
            }
        }
        if(GlobalUser.getInstance().getMyUser().getType().equals("Premium")){
            this.countPremium = this.connectionDAO.countPremium(GlobalUser.getInstance().getMyUser());
            System.out.println(this.countPremium + "usuaris que mhan donat like");
        }
        System.out.println("despues");
        System.out.println("is empty = " + arrayNextUsers.isEmpty());
        System.out.println("size = " + arrayNextUsers.size());
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
     * Get count premium int.
     *
     * @return the int
     */
    public int getCountPremium(){
        return this.countPremium;
    }

    /**
     * Get next image buffered image.
     *
     * @param nextUser the next user
     * @return the buffered image
     */
    public BufferedImage getNextImage(User nextUser){
        BufferedImage image = this.connectionDAO.readImage(nextUser);
        return image;
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
