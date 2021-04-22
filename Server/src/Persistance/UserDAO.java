package Persistance;

import Business.Entity.User;

import java.util.ArrayList;

public interface UserDAO {
    /**
     * Method to read all users.
     *
     * @return A list of users.
     */
    ArrayList<User> getAllUsers();

    /**
     * Method to read all users.
     *
     * @return user.
     * @param uuid The id of corresponding User to get
     */
    User getUser(int uuid);

    /**
     * Method that creates a new User with a given text.
     *
     * @param user The user to create the User with.
     * @return A UUID of the created user's identifier.
     */
    int addUser(User user);

    /**
     * Method to remove a User form the model by its nickname.
     *
     * @param user The user corresponding to the User item to delete.
     */
    boolean deleteUser(User user);

    /**
     * Method to update User form the model.
     *
     * @param user The user corresponding to the User item to update.
     */
    boolean updateUser(User user);

    /**
     * Method to check the validation of User form the model by its uuid.
     *
     * @return true or false
     * 
     * @param user The user corresponding to the User item to validate login.
     */
    boolean validationLogin(User user);

    /**
     * Method to get the top 5 of User form the model.
     *
     * @return the top5 users with most received matches
     * 
     */
    ArrayList<User> top5();

    /**
     * Method to get the top 5 of User form the model.
     *
     * @return the top5 users with most received matches
     * 
     */
    int checkLoginIntent(User user);

    ArrayList<User> getPretendentsPremium(User user);

    ArrayList<User> getPretendents(User user);

}