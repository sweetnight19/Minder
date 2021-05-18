package Business.Model;

import Business.Entity.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatServerDedicated extends Thread{
    private Socket client;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private int sizeArr;
    private User myUser;
    private User otherUser;

    public ChatServerDedicated(Socket client){
        this.sizeArr = 0;
        this.client = client;
    }

    @Override
    public void run() {
        try {
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
            sleep(100);

        }
            client.close();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
