package Models.ChatClients.ChatFileOperation;

import Models.ChatClients.ChatClient;
import Models.ChatClients.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ToFileChatClient implements ChatClient {
    private String loggedUser;
    private List<String> loggedUsers;
    private List<Message> messages;
    private List<ActionListener> listenerLoggedUsersChanged = new ArrayList<>();
    private List<ActionListener> listenerMessageChanged = new ArrayList<>();

    private ChatFileOperations chatFileOperations;

    public ToFileChatClient(ChatFileOperations chatFileOperations){
        loggedUsers = new ArrayList<>();
        messages = new ArrayList<>();
        this.chatFileOperations = chatFileOperations;

        messages = chatFileOperations.loadMessages();
    }

    @Override
    public void sendMessage(String text) {
        messages.add(new Message(loggedUser, text));
        chatFileOperations.writeMessages(messages);
        raiseEventMessagesChanged();

    }

    @Override
    public void login(String userName) {
        loggedUser = userName;
        loggedUsers.add(userName);
        addSystemMessage(Message.USER_LOGGED_IN, userName);
        raiseEventLoggedUsersChanged();
    }

    @Override
    public void logout() {
        addSystemMessage(Message.USER_LOGGED_OUT, loggedUser);
        loggedUsers.remove(loggedUser);
        loggedUser = null;

        raiseEventLoggedUsersChanged();
    }

    @Override
    public boolean isAuthenticated() {
        return loggedUser != null;
    }

    @Override
    public List<String> getLoggedUsers() {
        return loggedUsers;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addActionListenerLoggedUsersChanged(ActionListener toAdd) {
        listenerLoggedUsersChanged.add(toAdd);

    }

    @Override
    public void addActionListenerMessagesChanged(ActionListener toAdd) {
        listenerMessageChanged.add(toAdd);
    }
    private void raiseEventLoggedUsersChanged(){
        for (ActionListener al: listenerLoggedUsersChanged) {
            al.actionPerformed(new ActionEvent(this, 1, "Users changed"));
        }
    }
    private void raiseEventMessagesChanged(){
        for (ActionListener al: listenerMessageChanged){
            al.actionPerformed(new ActionEvent(this, 2, "Messages changed"));
        }
    }
    private void addSystemMessage(int type, String userName){
        messages.add(new Message(type, userName));
        chatFileOperations.writeMessages(messages);
        raiseEventMessagesChanged();
    }
}
