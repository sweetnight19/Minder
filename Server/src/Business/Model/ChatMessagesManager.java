package Business.Model;

import Business.Entity.ChatMessage;

import java.util.ArrayList;

public class ChatMessagesManager {

    public static ArrayList<ChatMessage> messages = new ArrayList<>();

    public static void addMessage(ChatMessage message){
        messages.add(message);
    }

    public static ArrayList<ChatMessage> getMessages(){
        return messages;
    }

    public static int getSize(){
        return messages.size();
    }
}
