package Business.Model;

import java.util.List;

import Business.Entity.User;

public interface SQLDAOinterface {

    List<User> getAllTodos();

    void addUser(User user);

    void deleteUser(User user);

    boolean toogleMatch();
}
