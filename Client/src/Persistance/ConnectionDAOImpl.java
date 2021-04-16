package Persistance;

import Business.Entity.ChatMessage;
import Business.Entity.Peer;
import Business.Entity.Trama;
import Business.Entity.User;
import Business.Model.ProtocolCommunication;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionDAOImpl implements ConnectionDAO {
    private Socket socket;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    public ConnectionDAOImpl(ConfigurationDAO configurationDAO) {
        try {
            //Inicialitzem tant el socket com els streams per on rebrem o enviarem la informació
            socket = new Socket(configurationDAO.getIpServer(), configurationDAO.getPort());
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());

            startConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startConnection() throws IOException {
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

    @Override
    public boolean registerUser(User user){
        try {
            os.writeObject(new Trama(ProtocolCommunication.CREATE_USER));
            os.writeObject(user);
            Trama trama = (Trama) is.readObject();
            if(trama.getContext().equals(ProtocolCommunication.OK)){
                System.out.println("User has been registered");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean validateLogin(User user) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.CREATE_USER));
            os.writeObject(user);
            Trama trama = (Trama) is.readObject();
            if(trama.getContext().equals(ProtocolCommunication.OK)){
                System.out.println("User has authenticated");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProfile(User user) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.CREATE_USER));
            os.writeObject(user);
            Trama trama = (Trama) is.readObject();
            if(trama.getContext().equals(ProtocolCommunication.OK)){
                System.out.println("User has been updated");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User readProfile(User user) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.READ_USER));
            os.writeObject(user);
            User newUser = (User) is.readObject();
            if(user!=null){
                System.out.println("User has been recieved");
                return newUser ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertLike(Peer peer) {
        return false;
    }

    @Override
    public boolean deletePeer(Peer peer) {
        return false;
    }

    @Override
    public ArrayList<User> getRandomUsers(User user) {
        return null;
    }

    @Override
    public ArrayList<User> getChatList(User user) {
        return null;
    }

    @Override
    public ArrayList<ChatMessage> getChatMessages(User source, User destiny) {
        return null;
    }

    @Override
    public boolean insertNewMessage(ChatMessage message) {
        return false;
    }
}
