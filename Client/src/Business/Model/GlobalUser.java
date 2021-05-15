package Business.Model;

import Business.Entity.User;

public class GlobalUser {
    private User myUser;
    private static GlobalUser instance = null;

    private GlobalUser(){ }

    public static GlobalUser getInstance(){
        if (instance == null){
            instance = new GlobalUser();
        }
        return instance;
    }

    public void setMyUser(User user){
        this.myUser = user;
    }

    public User getMyUser(){
        return this.myUser;
    }

    public void setLanguage(String programmingLanguage) {
        myUser.setProgrammingLanguage(programmingLanguage);
    }

    public void setDescription(String description) {
        myUser.setDescription(description);
    }
}
