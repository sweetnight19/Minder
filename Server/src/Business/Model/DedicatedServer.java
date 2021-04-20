package Business.Model;

import Business.Entity.ChatMessage;
import Business.Entity.Peer;
import Business.Entity.Trama;
import Business.Entity.User;
import Persistance.ChatDAO;
import Persistance.PeerDAO;
import Persistance.UserDAO;

import java.io.*;
import java.net.Socket;

public class DedicatedServer extends Thread {
    private Socket client;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private UserDAO userDAO;
    private ChatDAO chatDAO;
    private PeerDAO peerDAO;
    private boolean clientDisconnect;

    public DedicatedServer(Socket client, UserDAO userDAO, ChatDAO chatDAO, PeerDAO peerDAO) {
        this.client = client;
        this.userDAO = userDAO;
        this.peerDAO = peerDAO;
        this.chatDAO = chatDAO;
        this.clientDisconnect = false;

        try {
            this.is = new ObjectInputStream(client.getInputStream());
            this.os = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!clientDisconnect) {
                Trama trama = (Trama) is.readObject();
                if (trama != null) {
                    switch (trama.getContext()) {
                        case ProtocolCommunication.CONNECTION:
                            connection();
                            break;
                        case ProtocolCommunication.DISCONNECTION:
                            disconnection();
                            break;
                        case ProtocolCommunication.CREATE_USER:
                            createUser();
                            break;
                        case ProtocolCommunication.LOGIN_USER:
                            validateLogin();
                            break;
                        case ProtocolCommunication.UPDATE_USER:
                            updateUser();
                            break;
                        case ProtocolCommunication.READ_USER:
                            readUser();
                            break;
                        case ProtocolCommunication.DELETE_USER:
                            deleteUser();
                            break;
                        case ProtocolCommunication.CREATE_PEER:
                            createPeer();
                            break;
                        case ProtocolCommunication.LIST_CHAT:
                            listchat();
                            break;
                        case ProtocolCommunication.DELETE_PEER:
                            deletePeer();
                            break;
                        case ProtocolCommunication.CREATE_CHAT_MESSAGE:
                            createMessage();
                            break;
                        case ProtocolCommunication.READ_CHAT:
                            readChat();
                            break;
                        default:
                            os.writeObject(new Trama(ProtocolCommunication.KO));
                    }
                } else {
                    System.out.println("Server disconnected");
                }
            }

            client.close(); // Tanquem el socket client
        } catch (EOFException e) {
            System.out.println("[SERVER]: disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    private void listchat() {
    }

    private void readChat() {
        
    }

    private void createMessage() throws IOException, ClassNotFoundException {
        ChatMessage message = (ChatMessage) is.readObject();
        this.chatDAO.addMessage(message.getIdSource(), message.getIdDestiny(), message.getMessage());
        os.writeObject(new Trama(ProtocolCommunication.OK));
    }

    private void deletePeer() throws IOException, ClassNotFoundException {
        Peer peer = (Peer) is.readObject();
        this.peerDAO.deletePeer(peer.getIdSource(), peer.getIdDestiny());
        os.writeObject(new Trama(ProtocolCommunication.OK));
    }

    private void createPeer() throws IOException, ClassNotFoundException {
        Peer peer = (Peer) is.readObject();
        this.peerDAO.addLike(peer.getIdSource(), peer.getIdDestiny());
        os.writeObject(new Trama(ProtocolCommunication.OK));
    }

    private void deleteUser() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        this.userDAO.deleteUser(user.getId());
        os.writeObject(new Trama(ProtocolCommunication.OK));
    }

    private void readUser() {

    }

    private void updateUser() {

    }

    private void validateLogin() {

    }

    private void createUser() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        //System.out.println(user.getEmail() + user.getFirstName() + user.getProgrammingLanguage());
        if(this.userDAO.addUser(user) != -1){
            os.writeObject(new Trama(ProtocolCommunication.OK));
        }else{
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    private void disconnection() throws IOException {
        this.clientDisconnect = true;
        System.out.println("Client wants to disconnect");
        os.writeObject(new Trama(ProtocolCommunication.OK));
    }

    private void connection() throws IOException {
        os.writeObject(new Trama(ProtocolCommunication.OK));
        System.out.println("Connected to the client");
    }

}
