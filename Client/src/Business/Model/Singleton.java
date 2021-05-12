package Business.Model;

import Business.Entity.User;

public class Singleton {
    private User myUser;
    private static Singleton instance = null;

    private Singleton(){ }

    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public void setMyUser(User user){
        this.myUser = user;
    }

    public User getMyUser(){
        return this.myUser;
    }
}
