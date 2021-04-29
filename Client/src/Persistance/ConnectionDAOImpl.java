package Persistance;

import Business.Entity.ChatMessage;
import Business.Entity.Peer;
import Business.Entity.Trama;
import Business.Entity.User;
import Business.Model.ProtocolCommunication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ConnectionDAOImpl implements ConnectionDAO {
    private Socket socket;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    public ConnectionDAOImpl(ConfigurationDAO configurationDAO) {
        try {
            // Inicialitzem tant el socket com els streams per on rebrem o enviarem la
            // informació
            socket = new Socket(configurationDAO.getIp(), configurationDAO.getPort());
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());

            startConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startConnection() {
        try {
            os.writeObject(new Trama(ProtocolCommunication.CONNECTION));
            Trama trama = (Trama) is.readObject();
            if (trama.getContext().equals(ProtocolCommunication.OK)) {
                System.out.println("Connexion con servidor correcta");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean registerUser(User user) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.CREATE_USER));
            os.writeObject(user);
            Trama trama = (Trama) is.readObject();
            if (trama.getContext().equals(ProtocolCommunication.OK)) {
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
            os.writeObject(new Trama(ProtocolCommunication.LOGIN_USER));
            os.writeObject(user);
            Trama trama = (Trama) is.readObject();
            if (trama.getContext().equals(ProtocolCommunication.OK)) {
                System.out.println("User has been authenticated");
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
    public boolean checklogin(User user) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.CHECK_LOGIN));
            os.writeObject(user);
            Trama trama = (Trama) is.readObject();
            if (trama.getContext().equals(ProtocolCommunication.OK)) {
                System.out.println("It is not the first time that he does login");
                return true;
            } else if (trama.getContext().equals(ProtocolCommunication.KO)) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.UPDATE_USER));
            os.writeObject(user);
            Trama trama = (Trama) is.readObject();
            if (trama.getContext().equals(ProtocolCommunication.OK)) {
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
    public User readUser(User user) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.READ_USER));
            os.writeObject(user);
            User newUser = (User) is.readObject();
            if (newUser != null) {
                System.out.println("User has been recieved");
                return newUser;
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
        try {
            os.writeObject(new Trama(ProtocolCommunication.CREATE_PEER));
            os.writeObject(peer);
            Trama trama = (Trama) is.readObject();
            if (trama.getContext().equals(ProtocolCommunication.OK)) {
                System.out.println("Peer has been created");
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
    public boolean deletePeer(Peer peer) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.DELETE_PEER));
            os.writeObject(peer);
            Trama trama = (Trama) is.readObject();
            if (trama.getContext().equals(ProtocolCommunication.OK)) {
                System.out.println("Peer has been deleted");
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
    public ArrayList<User> getRandomUsers(User user) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.READ_PEERS));
            os.writeObject(user);
            ArrayList<User> usersCarrussel = (ArrayList<User>) is.readObject();
            if (usersCarrussel != null) {
                System.out.println("Possible users have been recieved");
                return usersCarrussel;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User> getChatList(User user) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.LIST_CHAT));
            os.writeObject(user);
            ArrayList<User> listChatUsers = (ArrayList<User>) is.readObject();
            if (listChatUsers != null) {
                System.out.println("List chat from user recieved");
                return listChatUsers;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ChatMessage> getChatMessages(User source, User destiny) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.READ_CHAT));
            os.writeObject(source);
            os.writeObject(destiny);
            ArrayList<ChatMessage> chatMessages = (ArrayList<ChatMessage>) is.readObject();
            if (chatMessages != null) {
                System.out.println("Chat messages recieved");
                return chatMessages;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertNewMessage(ChatMessage message) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.CREATE_CHAT_MESSAGE));
            os.writeObject(message);
            Trama trama = (Trama) is.readObject();
            if (trama.getContext().equals(ProtocolCommunication.OK)) {
                System.out.println("Message has been created");
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
    public BufferedImage readImage(User user) {
        try {
            os.writeObject(new Trama(ProtocolCommunication.READ_IMAGE));
            os.writeObject(user);

            byte[] sizeAr = new byte[4];
            is.read(sizeAr);

            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
            byte[] imageAr = new byte[size];
            is.readFully(imageAr);

            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

            System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
            //ImageIO.write(image, "jpg", new File("Server/images/" + user.getNickname() + ".jpg"));

            return image;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean sendImage(User user) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:\\Users\\edmon\\Downloads\\softwareTest.jpg"));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);

            byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();

            os.writeObject(new Trama(ProtocolCommunication.SEND_IMAGE));
            os.writeObject(user);
            os.write(size);
            os.write(byteArrayOutputStream.toByteArray());
            os.flush();
            Thread.sleep(120000);

            Trama trama = (Trama) is.readObject();
            if(trama.getContext().equals(ProtocolCommunication.OK)){
                System.out.println("Image has been sent correctly and saved");
                return true;
            }
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void disconnectFromServer() {
        try {
            os.writeObject(new Trama(ProtocolCommunication.DISCONNECTION));
            Trama trama = (Trama) is.readObject();
            if (trama.getContext().equals(ProtocolCommunication.OK)) {
                System.out.println("Disconnection correct");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
