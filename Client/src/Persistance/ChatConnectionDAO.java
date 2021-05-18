package Persistance;

import Business.Entity.ChatMessage;
import Business.Entity.User;
import Business.Model.ChatManager;
import Business.Model.NewMessageListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatConnectionDAO extends Thread {
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private User source;
    private User destiny;
    private NewMessageListener messageListener;
    private boolean closed = false;
    private Socket socket;

    public ChatConnectionDAO(ConfigurationDAO configurationDAO, User source, User destiny) {
        try {
            // Inicialitzem tant el socket com els streams per on rebrem o enviarem la
            // informació
            socket = new Socket(configurationDAO.getIp(), 54333);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
            this.source = source;
            this.destiny = destiny;
            messageListener = new ChatManager();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            os.writeObject(source);
            os.writeObject(destiny);

            while(!closed){
                ChatMessage message = (ChatMessage) is.readObject();
                messageListener.newMessage(message);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setClosed(){
        try {
            closed = true;
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
