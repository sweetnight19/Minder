package Persistance;

/**
 * The interface Configuration dao.
 */
public interface ConfigurationDAO {
    /**
     * Gets port.
     *
     * @return the port
     */
    int getPort();

    /**
     * Gets ip.
     *
     * @return the ip
     */
    String getIp();

    /**
     * Gets database.
     *
     * @return the database
     */
    String getDatabase();

    /**
     * Gets username.
     *
     * @return the username
     */
    String getUsername();

    /**
     * Gets password.
     *
     * @return the password
     */
    String getPassword();

    /**
     * Gets port tcp.
     *
     * @return the port tcp
     */
    int getPortTCP();
}
