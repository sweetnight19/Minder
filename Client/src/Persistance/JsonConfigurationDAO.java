package Persistance;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type Json configuration dao.
 */
public class JsonConfigurationDAO implements ConfigurationDAO {
    private final String ipServer;
    private final int port;

    /**
     * Instantiates a new Json configuration dao.
     *
     * @param path the path
     * @throws IOException the io exception
     */
    public JsonConfigurationDAO(String path) throws IOException {
        Gson gson = new Gson();
        Path path1 = Paths.get(path); // Ens guardem la path on es troba el fitxer de configuració

        // Parsejem el fitxer json
        JsonObject json = JsonParser.parseString(Files.readString(path1)).getAsJsonObject();
        this.port = gson.fromJson(json.get("portTCP"), int.class); // obtenció de dades segon la key i les guardem
        this.ipServer = gson.fromJson(json.get("ipServer"), String.class);
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public String getIp() {
        return this.ipServer;
    }
}
