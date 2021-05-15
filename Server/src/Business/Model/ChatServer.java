package Business.Model;

import Business.Entity.User;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer extends Thread {
    private Socket client;
    private ServerSocket sSocketChat;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private int sizeArr;
    private User myUser;
    private User otherUser;

    public ChatServer() {
        try {
            this.sizeArr = 0;
            this.sSocketChat = new ServerSocket(54333);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            client = sSocketChat.accept(); //Peticions de client per chat instantani
            this.is = new ObjectInputStream(client.getInputStream());
            this.os = new ObjectOutputStream(client.getOutputStream());

            myUser = (User) is.readObject();
            otherUser = (User) is.readObject();
            sizeArr = ChatMessagesManager.getSize();

            while (!DedicatedServer.clientDisconnect) {

                if(ChatMessagesManager.getSize() > sizeArr){
                    for (int i = sizeArr; i < ChatMessagesManager.getSize(); i++) {
                        if(ChatMessagesManager.getMessages().get(i).getIdDestiny() == myUser.getId()
                                && ChatMessagesManager.getMessages().get(i).getIdSource() == otherUser.getId()){
                            os.writeObject(ChatMessagesManager.getMessages().get(i));
                        }
                    }

                    sizeArr = ChatMessagesManager.getSize();
                }

            }

            client.close();
        } catch (
        EOFException e) {
            System.out.println("[SERVER]: disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
