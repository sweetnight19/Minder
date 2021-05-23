package Persistance;

import Business.Entity.ChatMessage;
import Business.Entity.User;
import Business.Model.NewMessageListener;
import Presentation.Controller.ChatController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The type Chat connection dao.
 */
public class ChatConnectionDAO extends Thread {
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private User source;
    private User destiny;
    private NewMessageListener messageListener;
    private boolean closed = false;
    private Socket socket;

    /**
     * Instantiates a new Chat connection dao.
     *
     * @param configurationDAO the configuration dao
     * @param source           the source
     * @param destiny          the destiny
     * @param controller       the controller
     */
    public ChatConnectionDAO(ConfigurationDAO configurationDAO, User source, User destiny, ChatController controller) {
        try {
            socket = new Socket(configurationDAO.getIp(), 54333);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
            this.source = source;
            this.destiny = destiny;
            messageListener = controller;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            os.writeObject(source);
            os.writeObject(destiny);

            while (!closed) {
                ChatMessage message = (ChatMessage) is.readObject();
                if (message != null) {
                    System.out.println(message.getMessage());
                    messageListener.newMessage(message);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Chat Server closed");
            //e.printStackTrace();
        }
    }

    /**
     * Set closed.
     */
    public void setClosed() {
        try {
            closed = true;
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
