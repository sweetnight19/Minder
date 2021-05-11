package Business.Model;

import Business.Entity.User;
import Persistance.ConnectionDAO;

import java.awt.image.BufferedImage;

public class ProfileManager {
    private ConnectionDAO connectionDAO;

    public ProfileManager(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
    }

    public boolean deleteUser(User user){
        if(this.connectionDAO.deleteUser(user)){
            return true;
        }
        return false;
    }

    public boolean saveUserChanges(User user){
        if(this.connectionDAO.updateUser(user)){
            return true;
        }
        return false;
    }

    public boolean saveNewImage(BufferedImage image){
        //agafar usuari global
        User user = new User(0, null, null, 0, null, null, null, null, null, null);
        if(this.connectionDAO.sendImage(user, image)){
            return true;
        }
        return false;
    }
}
