package Business.Model;

import Business.Entity.User;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The type Chat server.
 */
public class ChatServer extends Thread {
    private Socket client;
    private ServerSocket sSocketChat;

    /**
     * Instantiates a new Chat server.
     */
    public ChatServer() {
        try {
            this.sSocketChat = new ServerSocket(54333);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            while(true) {
                client = sSocketChat.accept(); //Peticions de client per chat instantani
                ChatServerDedicated dedicated = new ChatServerDedicated(client);
                dedicated.start();
            }
        } catch (
        EOFException e) {
            System.out.println("[SERVER]: disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
