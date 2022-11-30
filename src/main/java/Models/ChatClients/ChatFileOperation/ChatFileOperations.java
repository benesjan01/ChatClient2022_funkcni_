package Models.ChatClients.ChatFileOperation;

import Models.ChatClients.Message;

import java.util.List;

public interface ChatFileOperations {
    List<Message> loadMessages();
    void writeMessages(List<Message> messages);

    List<String> loadLoggedUsers();
    void writeLoggedUsers(List<String> users);
}
