package Models.ChatClients.Database;

import Models.ChatClients.Message;

import java.util.List;

public interface DatabaseOperations {
    void addMessage(Message message);
    List<Message> getMessages();

}
