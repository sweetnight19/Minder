package Business.Model;

import Business.Entity.User;

/**
 * The type Global user.
 */
public class GlobalUser {
    private User myUser;
    private static GlobalUser instance = null;

    private GlobalUser() {
    }

    /**
     * Get instance global user.
     *
     * @return the global user
     */
    public static GlobalUser getInstance() {
        if (instance == null) {
            instance = new GlobalUser();
        }
        return instance;
    }

    /**
     * Set my user.
     *
     * @param user the user
     */
    public void setMyUser(User user) {
        this.myUser = user;
    }

    /**
     * Get my user user.
     *
     * @return the user
     */
    public User getMyUser() {
        return this.myUser;
    }

    /**
     * Sets language.
     *
     * @param programmingLanguage the programming language
     */
    public void setLanguage(String programmingLanguage) {
        myUser.setProgrammingLanguage(programmingLanguage);
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        myUser.setDescription(description);
    }
}
