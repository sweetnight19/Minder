package Business.Model;

import Business.Entity.User;
import Persistance.ConnectionDAO;
import java.awt.image.BufferedImage;

public class ProfileManager {
    private ConnectionDAO connectionDAO;
    private BufferedImage profileImage;

    public ProfileManager(ConnectionDAO connectionDAO) {
        profileImage = null;
        this.connectionDAO = connectionDAO;
    }

    public User getUserProfileInformation(){
        GlobalUser.getInstance().setMyUser(this.connectionDAO.readUser(GlobalUser.getInstance().getMyUser()));
        profileImage = this.connectionDAO.readImage(GlobalUser.getInstance().getMyUser());
        return GlobalUser.getInstance().getMyUser();
    }

    public boolean deleteUser(){
        if(this.connectionDAO.deleteUser(GlobalUser.getInstance().getMyUser())){
            this.connectionDAO.disconnectFromServer();
            return true;
        }
        return false;
    }

    public boolean saveUserChanges(String programmingLanguage, String description){
        GlobalUser.getInstance().getMyUser().setProgrammingLanguage(programmingLanguage);
        GlobalUser.getInstance().getMyUser().setDescription(description);
        if(this.connectionDAO.updateUser(GlobalUser.getInstance().getMyUser())){
            return true;
        }
        return false;
    }

    public boolean saveNewImage(BufferedImage image){
        if(this.connectionDAO.sendImage(GlobalUser.getInstance().getMyUser(), image)){
            return true;
        }
        return false;
    }

    public BufferedImage getProfileImage() {
        return profileImage;
    }
}
