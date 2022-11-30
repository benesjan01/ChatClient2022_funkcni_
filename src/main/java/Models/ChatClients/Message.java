package Models.ChatClients;

import com.google.gson.annotations.Expose;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {

    @Expose(serialize = true, deserialize = true)
    private String author;
    @Expose(serialize = true, deserialize = true)
    private String text;
    @Expose(serialize = true, deserialize = true)
    private LocalDateTime created;

    public static final int USER_LOGGED_IN = 1;
    public static final int USER_LOGGED_OUT = 2;

    private static final String AUTHOR_SYSTEM = "System";

    public Message(String author, String text){
        this.author = author;
        this.text = text;
        this.created = LocalDateTime.now();

    }
    public Message(ResultSet resultSet) {
        try {
            this.author = resultSet.getString("author");
            this.text = resultSet.getString("text");
            this.created = resultSet.getTimestamp("created").toLocalDateTime();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Message(int type, String userName){
        this.author = AUTHOR_SYSTEM;
        this.created = LocalDateTime.now();
        if (type == USER_LOGGED_IN) {
            text = userName + " has joined the chat\n";
        } else if (type == USER_LOGGED_OUT){
            text = userName + " has logged out\n";
        }

    }
    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        String tempText = author;
        if (author.toUpperCase().equals(AUTHOR_SYSTEM.toUpperCase())){
            tempText = AUTHOR_SYSTEM.toUpperCase();
        }

        tempText += " [" + created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "]\n";
        tempText += text+"\n";

        return tempText;
    }
}
