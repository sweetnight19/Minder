package Persistance;

public interface ConfDAO {
    int getPort();
    String getIp();
    String getDatabase();
    String getUsername();
    String getPassword();
    int getPortTCP();
}
