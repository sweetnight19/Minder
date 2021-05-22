package Business.Model;

import Business.Entity.ChatMessage;
import Business.Entity.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The type Chat server dedicated.
 */
public class ChatServerDedicated extends Thread{
    private Socket client;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private int sizeArr;
    private User myUser;
    private User otherUser;
    private static ChatMessage newMessage;
    private static boolean messageToSend = false;

    /**
     * Instantiates a new Chat server dedicated.
     *
     * @param client the client
     */
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
            System.out.println(ChatMessagesManager.getSize());

            while (!DedicatedServer.clientDisconnect) {

                /*if(ChatMessagesManager.getSize() > sizeArr){
                    System.out.println("hi ha un nou missatge");
                    for (int i = (sizeArr-1); i < ChatMessagesManager.getSize(); i++) {
                        System.out.println("entrem en el bucle");
                        if(ChatMessagesManager.getMessages().get(i).getIdDestiny() == myUser.getId()
                                && ChatMessagesManager.getMessages().get(i).getIdSource() == otherUser.getId()){
                            System.out.println("el missatge es per mi li envio al client");
                            os.writeObject(ChatMessagesManager.getMessages().get(i));
                        }
                    }

                    sizeArr = ChatMessagesManager.getSize();
                }*/
                if(messageToSend){
                    if(newMessage.getIdDestiny() == myUser.getId()
                            && newMessage.getIdSource() == otherUser.getId()){
                        System.out.println("el missatge es per mi li envio al client");
                        os.writeObject(newMessage);
                        messageToSend = false;
                    }
                }
                sleep(100);

            }
            client.close();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * New message.
     *
     * @param message the message
     */
    public static void newMessage(ChatMessage message){
        newMessage = message;
        System.out.println(message.getMessage());
        messageToSend = true;
    }
}
