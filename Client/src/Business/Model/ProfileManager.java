package Business.Model;

import Business.Entity.User;
import Persistance.ConnectionDAO;

import java.awt.image.BufferedImage;

/**
 * The type Profile manager.
 */
public class ProfileManager {
    private final ConnectionDAO connectionDAO;
    private BufferedImage profileImage;

    /**
     * Instantiates a new Profile manager.
     *
     * @param connectionDAO the connection dao
     */
    public ProfileManager(ConnectionDAO connectionDAO) {
        profileImage = null;
        this.connectionDAO = connectionDAO;
    }

    /**
     * Get user profile information user.
     *
     * @return the user
     */
    public User getUserProfileInformation(){
        //GlobalUser.getInstance().setMyUser(this.connectionDAO.readUser(GlobalUser.getInstance().getMyUser()));
        profileImage = this.connectionDAO.readImage(GlobalUser.getInstance().getMyUser());
        return GlobalUser.getInstance().getMyUser();
    }

    /**
     * Delete user boolean.
     *
     * @return the boolean
     */
    public boolean deleteUser() {
        if (this.connectionDAO.deleteUser(GlobalUser.getInstance().getMyUser())) {
            this.connectionDAO.disconnectFromServer();
            return true;
        }
        return false;
    }

    /**
     * Save user changes boolean.
     *
     * @param programmingLanguage the programming language
     * @param description         the description
     * @return the boolean
     */
    public boolean saveUserChanges(String programmingLanguage, String description) {
        GlobalUser.getInstance().setLanguage(programmingLanguage);
        GlobalUser.getInstance().setDescription(description);
        return this.connectionDAO.updateUser(GlobalUser.getInstance().getMyUser());
    }

    /**
     * Save new image boolean.
     *
     * @param image the image
     * @return the boolean
     */
    public boolean saveNewImage(BufferedImage image){
        if(this.connectionDAO.sendImage(GlobalUser.getInstance().getMyUser(), image)){
            profileImage = image;
            GlobalUser.getInstance().getMyUser().setPathImage(GlobalUser.getInstance().getMyUser().getNickname() + ".jpg");
            return true;
        }
        return false;
    }

    /**
     * Gets profile image.
     *
     * @return the profile image
     */
    public BufferedImage getProfileImage() {
        return profileImage;
    }
}
