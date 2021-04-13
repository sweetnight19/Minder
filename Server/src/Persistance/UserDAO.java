package Persistance;

import Business.Entity.User;

public interface UserDAO {
    int create();

    User read();

    int update();

    int delete();
}