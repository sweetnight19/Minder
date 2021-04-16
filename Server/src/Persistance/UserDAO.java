package Persistance;

import Business.Entity.User;

import java.util.ArrayList;
import java.util.UUID;

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
     * @param nickname The nickname corresponding to the User item to delete.
     */
    void deleteUser(int uuid);

    /**
     * Method to toggle a todo's completed status by its id.
     *
     * @param id The id corresponding to the todo item to toggle.
     * @return The resulting completed status, after the toggle.
     */
    // boolean toggleTodo(String id);
}