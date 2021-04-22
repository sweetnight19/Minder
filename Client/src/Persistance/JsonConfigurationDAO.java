package Persistance;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonConfigurationDAO implements ConfigurationDAO {
    private Path path;
    private String ipServer;
    private int port;

    public JsonConfigurationDAO(String path) throws IOException {
        Gson gson = new Gson();
        this.path = Paths.get(path); // Ens guardem la path on es troba el fitxer de configuració

        // Parsejem el fitxer json
        JsonObject json = JsonParser.parseString(Files.readString(this.path)).getAsJsonObject();
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
