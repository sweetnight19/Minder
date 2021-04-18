package Persistance;

public interface ConfigurationDAO {
    int getPort();

    String getIp();

    String getDatabase();

    String getUsername();

    String getPassword();

    int getPortTCP();
}
