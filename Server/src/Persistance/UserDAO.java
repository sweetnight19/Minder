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
     * Method to read only one user.
     *
     * @param nickname the nickname
     * @return user. user
     */
    User getUser(String nickname);

    /**
     * Method that creates a new User with a user object given.
     *
     * @param user The user to create the User with.
     * @return A UUID of the created user's identifier.
     */
    int addUser(User user);

    /**
     * Method to remove a User form the databse by its user object.
     *
     * @param user The user corresponding to the User item to delete.
     * @return the boolean
     */
    boolean deleteUser(User user);

    /**
     * Method to update User form the database.
     *
     * @param user The user corresponding to the User item to update.
     * @return the boolean
     */
    boolean updateUser(User user);

    /**
     * Method to check if the login user exists and has texted the correct password.
     *
     * @param user The user corresponding to the User item to validate login.
     * @return true or false
     */
    boolean validationLogin(User user);

    /**
     * Method to get the top 5 of User with more matches from the database.
     *
     * @return the top5 users with most received matches
     */
    ArrayList<User> top5();

    /**
     * Method to get if the log in user is the first time that enters the platform or not.
     *
     * @param user the user
     * @return the intent login of users.
     */
    int checkLoginIntent(User user);

    /**
     * Gets pretendents premium that matches the programming language of the client and also they may
     * give a like to our client before.
     *
     * @param user the user
     * @return the pretendents premium
     */
    ArrayList<User> getPretendentsPremium(User user);

    /**
     * Gets pretendents that matches the programming language of the client.
     *
     * @param user the user
     * @return the pretendents
     */
    ArrayList<User> getPretendents(User user);

    /**
     * Count how many pretendents premium does our client have.
     *
     * @param user the user
     * @return the int
     */
    int countPretendentsPremium(User user);

}