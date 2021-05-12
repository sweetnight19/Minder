package Business.Model;

import Business.Entity.User;
import Persistance.ConnectionDAO;
import java.awt.image.BufferedImage;

public class ProfileManager {
    private ConnectionDAO connectionDAO;
    private BufferedImage profileImage;

    public ProfileManager(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
    }

    public User getUserProfileInformation(){
        Singleton.getInstance().setMyUser(this.connectionDAO.readUser(Singleton.getInstance().getMyUser()));
        profileImage = this.connectionDAO.readImage(Singleton.getInstance().getMyUser());
        return Singleton.getInstance().getMyUser();
    }

    public boolean deleteUser(){
        if(this.connectionDAO.deleteUser(Singleton.getInstance().getMyUser())){
            this.connectionDAO.disconnectFromServer();
            return true;
        }
        return false;
    }

    public boolean saveUserChanges(String programmingLanguage, String description){
        Singleton.getInstance().getMyUser().setProgrammingLanguage(programmingLanguage);
        Singleton.getInstance().getMyUser().setDescription(description);
        if(this.connectionDAO.updateUser(Singleton.getInstance().getMyUser())){
            return true;
        }
        return false;
    }

    public boolean saveNewImage(BufferedImage image){
        if(this.connectionDAO.sendImage(Singleton.getInstance().getMyUser(), image)){
            return true;
        }
        return false;
    }

    public BufferedImage getProfileImage() {
        return profileImage;
    }
}
