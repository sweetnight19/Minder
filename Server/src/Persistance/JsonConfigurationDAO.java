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
    private int port;
    private String ip;
    private String database;
    private String username;
    private String password;
    private int portTCP;

    public JsonConfigurationDAO(String path) throws IOException {
        Gson gson = new Gson();
        this.path = Paths.get(path); //Ens guardem la path on es troba el fitxer de configuració

        //Parsejem el fitxer json
        JsonObject json = JsonParser.parseString(Files.readString(this.path)).getAsJsonObject();
        this.port = gson.fromJson(json.get("port"), int.class); //obtenció de dades segon la key i les guardem
        this.ip = gson.fromJson(json.get("ip"), String.class);
        this.database = gson.fromJson(json.get("database"), String.class);
        this.username = gson.fromJson(json.get("username"), String.class);
        this.password = gson.fromJson(json.get("password"), String.class);
        this.portTCP = gson.fromJson(json.get("portTCP"), int.class);
    }


    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public String getIp() {
        return this.ip;
    }

    @Override
    public String getDatabase() {
        return this.database;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public int getPortTCP() {
        return this.portTCP;
    }
}
