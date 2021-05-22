package Business.Model;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The type Chat server.
 */
public class ChatServer extends Thread {
    private Socket client;
    private ServerSocket sSocketChat;

    /**
     * Instantiates a new Chat server with his ServerSocket.
     */
    public ChatServer() {
        try {
            this.sSocketChat = new ServerSocket(54333);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Thread with a bucle that for each connection of a client, will launch a dedicated chat server
     */
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
