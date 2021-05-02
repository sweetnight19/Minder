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

public class DedicatedServer extends Thread {
    private final Socket client;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private final UserDAO userDAO;
    private final ChatDAO chatDAO;
    private final PeerDAO peerDAO;
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

    private void readImage() throws IOException, InterruptedException, ClassNotFoundException {
        User user = (User) is.readObject();
        user = this.userDAO.getUser(user.getId());

        BufferedImage image;
        image = ImageIO.read(new File("Server/images/" + user.getPathImage()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        os.write(size);
        os.write(byteArrayOutputStream.toByteArray());
        os.flush();
        Thread.sleep(120000);
    }

    private void saveImage() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();

        byte[] sizeAr = new byte[4];
        is.read(sizeAr);

        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
        byte[] imageAr = new byte[size];
        is.readFully(imageAr);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

        // System.out.println("Received " + image.getHeight() + "x" + image.getWidth() +
        // ": " + System.currentTimeMillis());
        String pathBd = user.getNickname() + ".jpg";
        ImageIO.write(image, "jpg", new File("Server/images/" + pathBd));

        user.setPathImage(pathBd);
        if (this.userDAO.updateUser(user)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    private void checkLogin() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        int status = this.userDAO.checkLoginIntent(user);
        System.out.println("status: " + status + "," + user.getNickname() + "," + user.getPassword());
        if (status == -1) {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        }
    }

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

    private void listchat() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        ArrayList<User> usersArray = this.peerDAO.getUserPeers(user);
        os.writeObject(usersArray);
    }

    private void readChat() throws IOException, ClassNotFoundException {
        User source = (User) is.readObject();
        User destiny = (User) is.readObject();
        ArrayList<ChatMessage> messages = this.chatDAO.getAllXats(source, destiny);
        os.writeObject(messages);
    }

    private void createMessage() throws IOException, ClassNotFoundException {
        ChatMessage message = (ChatMessage) is.readObject();
        if (this.chatDAO.addMessage(message)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    private void deletePeer() throws IOException, ClassNotFoundException {
        Peer peer = (Peer) is.readObject();
        if (this.peerDAO.deletePeer(peer)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    private void createPeer() throws IOException, ClassNotFoundException {
        Peer peer = (Peer) is.readObject();
        if (this.peerDAO.addLike(peer)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    private void deleteUser() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        if (this.userDAO.deleteUser(user)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    private void readUser() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        User response = this.userDAO.getUser(user.getId());
        if (response != null) {
            os.writeObject(response);
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    private void updateUser() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        if (this.userDAO.updateUser(user)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    private void validateLogin() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        if (this.userDAO.validationLogin(user)) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
            os.writeObject(new Trama(ProtocolCommunication.KO));
        }
    }

    private void createUser() throws IOException, ClassNotFoundException {
        User user = (User) is.readObject();
        if (this.userDAO.addUser(user) != -1) {
            os.writeObject(new Trama(ProtocolCommunication.OK));
        } else {
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
