package Business.Model;

import Business.Entity.Trama;

import java.io.*;
import java.net.Socket;

public class DedicatedServer extends Thread {
    private Socket client;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private boolean clientDisconnect;

    public DedicatedServer(Socket client) {
        this.client = client;
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
            while(!clientDisconnect){
                Trama trama = (Trama) is.readObject();
                if(trama != null) {
                    switch (trama.getContext()) {
                        case ProtocolCommunication.CONNECTION:
                            os.writeObject(new Trama(ProtocolCommunication.OK));
                            System.out.println("connected to the client");
                            break;
                        case ProtocolCommunication.DISCONNECTION:
                            clientDisconnect = true;
                            break;
                        default:
                            os.writeObject(new Trama(ProtocolCommunication.KO));
                    }
                }else{
                    System.out.println("Server disconnected");
                }
            }

            client.close();         //Tanquem el socket client
        }catch(EOFException e){
            System.out.println("[SERVER]: disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
