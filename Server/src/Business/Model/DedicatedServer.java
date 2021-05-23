package Business.Model;

import Business.Entity.ChatMessage;
import Business.Entity.Peer;
import Business.Entity.Trama;
import Business.Entity.User;
import Persistance.ChatDAO;
import Persistance.PeerDAO;
import Persistance.UserDAO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * The type Dedicated server.
 */
public class DedicatedServer extends Thread {
    private final Socket client;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private final UserDAO userDAO;
    private final ChatDAO chatDAO;
    private final PeerDAO peerDAO;
    /**
     * The constant clientDisconnect.
     */
    public static boolean clientDisconnect;

    /**
     * Instantiates a new Dedicated server.
     *
     * @param client  the client
     * @param userDAO the user dao
     * @param chatDAO the chat dao
     * @param peerDAO the peer dao
     */
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
                        case ProtocolCommunication.CHECK_LOGIN:
                            checkLogin();
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
                        case ProtocolCommunication.READ_PEERS:
                            feedUsers();
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
                        case ProtocolCommunication.READ_IMAGE:
                            readImage();
                            break;
                        case ProtocolCommunication.SEND_IMAGE:
                            saveImage();
                            break;
                        case ProtocolCommunication.COUNT:
                            countPremiumUsers();
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
        }
    }

    /**
     * Function called when client requests his image or an image of another user, so we loaded from the
     * images folder in the server and send the image throught the socket
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readImage() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        user = this.userDAO.getUser(user.getNickname());
        System.out.println(user.getPathImage());
        if(!user.getPathImage().equals("null") && !user.getPathImage().startsWith("http://dummyimage.com")) {
            BufferedImage image;
            image = ImageIO.read(new File("Server/images/" + user.getPathImage()));

            os.writeObject(new Trama(ProtocolCommunication.OK));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);

            byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
            os.write(size);
            os.write(byteArrayOutputStream.toByteArray());
            os.flush();
        }else{
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    /**
     * Function that recieves an image from the client that must be treated to save the path into the
     * database, and the image inside the images folder inside the server.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void saveImage() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();

        byte[] sizeAr = new byte[4];
        is.read(sizeAr);

        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
        byte[] imageAr = new byte[size];
        is.readFully(imageAr);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
        String pathBd = user.getNickname() + ".jpg";
        ImageIO.write(image, "jpg", new File("Server/images/" + pathBd));

        user.setPathImage(pathBd);
        if (this.userDAO.updateUser(user)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    /**
     * Function that tells the client if the user that wants to log in is the first time doing it or not.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void checkLogin() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        int status = this.userDAO.checkLoginIntent(user);
        if (status == -1) {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        } else {
            if(status == 0){
                os.writeObject(new Trama(ProtocolCommunication.STATUS_0));
            }else if(status == 1){
                os.writeObject(new Trama(ProtocolCommunication.STATUS_1));
            }
        }
    }

    /**
     * Function that sends to the client an array of pretendents to show them on the feed
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void feedUsers() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        ArrayList<User> pretendientes;
        if (user.getType().equals("Premium")) {
            pretendientes = this.userDAO.getPretendentsPremium(user);
        } else {
            pretendientes = this.userDAO.getPretendents(user);
        }
        os.writeObject(pretendientes);
    }

    /**
     * Function only available for premium users, that tells the client how many users on the feed
     * have said they like me before.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void countPremiumUsers() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        int countPremium = this.userDAO.countPretendentsPremium(user);
        os.writeObject(new Trama(String.valueOf(countPremium)));
    }

    /**
     * Function that sends to the client all the users that have match with the client.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void listchat() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        ArrayList<User> usersArray = this.peerDAO.getUserPeers(user);
        os.writeObject(usersArray);
    }

    /**
     * Function that sends to client all the messages that have been texted between user source and destiny.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readChat() throws IOException, ClassNotFoundException {
        User source = (User) is.readObject();
        User destiny = (User) is.readObject();
        ArrayList<ChatMessage> messages = this.chatDAO.getAllXats(source, destiny);
        os.writeObject(messages);
    }

    /**
     * Function that recieves a message and adds to persistance, also it announces to the chat server
     * dedicated there is a new message to treat.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void createMessage() throws IOException, ClassNotFoundException {
        ChatMessage message = (ChatMessage) is.readObject();
        if (this.chatDAO.addMessage(message)) {
            ChatServerDedicated.newMessage(message);
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    /**
     * Function that requests the persistance to delete a Peer (DISMATCH)
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void deletePeer() throws IOException, ClassNotFoundException {
        Peer peer = (Peer) is.readObject();
        if (this.peerDAO.deletePeer(peer)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    /**
     * Function that requests the persistance to insert a new Peer (LIKE)
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void createPeer() throws IOException, ClassNotFoundException {
        Peer peer = (Peer) is.readObject();
        if (this.peerDAO.addLike(peer)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    /**
     * Function that requests persistance to delete the user from the database
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void deleteUser() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        if (this.userDAO.deleteUser(user)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    /**
     * Function that returns to the client all the information that persistance have about the user
     * recieved.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readUser() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        User response = this.userDAO.getUser(user.getNickname());
        os.writeObject(response);
    }

    /**
     * Function to say the persistance we want to update the user we have recieved.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void updateUser() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        if (this.userDAO.updateUser(user)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    /**
     * Function that request the persistance to validate a user that wants to log in.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void validateLogin() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        if (this.userDAO.validationLogin(user)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    /**
     * Function that recieves a user that wants to register on the platform, it requests the database
     * if the register is good and tells the client the answer.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void createUser() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        if (this.userDAO.addUser(user) != -1) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    /**
     * Function where server accepts the request of the client to get disconnected
     * @throws IOException
     */
    private void disconnection() throws IOException {
        this.clientDisconnect = true;
        System.out.println("Client wants to disconnect");
        os.writeObject(new Trama(ProtocolCommunication.OK));
    }

    /**
     * Function communicates the client the connection with the client is OK
     * @throws IOException
     */
    private void connection() throws IOException {
        os.writeObject(new Trama(ProtocolCommunication.OK));
        System.out.println("Connected to the client");
    }

}
