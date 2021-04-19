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
     * Method that creates a new User with a given text.
     *
     * @param user The user to create the User with.
     * @return A UUID of the created user's identifier.
     */
    int addUser(User user);

    /**
     * Method to remove a User form the model by its nickname.
     *
     * @param uuid The id corresponding to the User item to delete.
     */
    void deleteUser(int uuid);

    /**
     * Method to update type of User form the model by its uuid.
     *
     * @param uuid The id corresponding to the User item to update.
     * @param type The type of account to update.
     */
    void updateTypeUser(int uuid, String type);

    /**
     * Method to update description of User form the model by its uuid.
     *
     * @param uuid        The id corresponding to the User item to update.
     * @param description The description of account to update.
     */
    void updateDescription(int uuid, String description);

    /**
     * Method to update pathImage of User form the model by its uuid.
     *
     * @param uuid The id corresponding to the User item to update.
     * @param path The path of account to update.
     */
    void updateImage(int uuid, String path);

    /**
     * Method to update programming language of User form the model by its uuid.
     *
     * @param uuid     The id corresponding to the User item to update.
     * @param language The language of account to update.
     */
    void updateProgammingLanguage(int uuid, String language);

    /**
     * Method to check the validation of User form the model by its uuid.
     *
     * @return true or false
     * 
     * @param uuid     The id corresponding to the User item to update.
     * @param password The password of account to check.
     */
    boolean validadionLogin(int uuid, String password);

    /**
     * Method to get the top 5 of User form the model.
     *
     * @return the top5 users with most received matches
     * 
     */
    ArrayList<User> top5();

}