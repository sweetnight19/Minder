package Persistance;

import Business.Entity.Trama;
import Business.Model.ProtocolCommunication;

import java.io.*;
import java.net.Socket;

public class ConnectionDAO {
    private Socket socket;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    public ConnectionDAO() throws IOException {
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");

        try {
            //Inicialitzem tant el socket com els streams per on rebrem o enviarem la informació
            socket = new Socket(configurationDAO.getIpServer(), configurationDAO.getPort());
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());

            controlConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void controlConnection() throws IOException {
        //Funció que s'encarrega d'escoltar constantment si ens arriba informació del servidor
        os.writeObject(new Trama(ProtocolCommunication.CONNECTION));
        try {
            Trama trama = (Trama) is.readObject();
            if(trama.getContext().equals(ProtocolCommunication.OK)){
                System.out.println("Connexion con servidor correcta");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
