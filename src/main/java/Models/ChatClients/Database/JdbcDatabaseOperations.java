package Models.ChatClients.Database;

import Models.ChatClients.Message;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDatabaseOperations implements DatabaseOperations{
    private final Connection connection;

    public JdbcDatabaseOperations(String driver, String url) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        this.connection = DriverManager.getConnection(url);
    }

    @Override
    public void addMessage(Message message) {
        try {
            Statement statement = connection.createStatement();
            String sql =
                "INSERT INTO ChatMessages (author, text, created) "
                + "VALUES ("
                    +"'" + message.getAuthor() + ","
                    +"'" + message.getText() + ","
                    +"'" + Timestamp.valueOf(message.getCreated()) + "'"
                + ")";
            statement.executeUpdate(sql);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override//TODO
    public List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM ChatMessages;";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                messages.add(new Message(rs));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }
}
