package Persistance;

import Business.Entity.User;

import java.util.ArrayList;

/**
 * The interface User dao.
 */
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
     * @param nickname the nickname
     * @return user. user
     */
    User getUser(String nickname);

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
     * @return the boolean
     */
    boolean deleteUser(User user);

    /**
     * Method to update User form the model.
     *
     * @param user The user corresponding to the User item to update.
     * @return the boolean
     */
    boolean updateUser(User user);

    /**
     * Method to check the validation of User form the model by its uuid.
     *
     * @param user The user corresponding to the User item to validate login.
     * @return true or false
     */
    boolean validationLogin(User user);

    /**
     * Method to get the top 5 of User form the model.
     *
     * @return the top5 users with most received matches
     */
    ArrayList<User> top5();

    /**
     * Method to get the intent login of User form the model.
     *
     * @param user the user
     * @return the intent login of users.
     */
    int checkLoginIntent(User user);

    /**
     * Gets pretendents premium.
     *
     * @param user the user
     * @return the pretendents premium
     */
    ArrayList<User> getPretendentsPremium(User user);

    /**
     * Gets pretendents.
     *
     * @param user the user
     * @return the pretendents
     */
    ArrayList<User> getPretendents(User user);

    /**
     * Count pretendents premium int.
     *
     * @param user the user
     * @return the int
     */
    int countPretendentsPremium(User user);

}