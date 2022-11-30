package Models.ChatClients.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DbInitializer {
    private final String driver;
    private final String url;

    public DbInitializer(String driver, String url) {
        this.driver = driver;
        this.url = url;
    }
    public void Init(){
        try {
            //nacteme driver
            Class.forName(driver);

            //otevreme spojeni
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE ChatMessages " +
                    "(id INT NOT NULL GENERATED ALWAYS AS IDENTITY " +
                    "CONSTRAINT ChatMessages_PK PRIMARY KEY, " +
                    "author VARCHAR(50), " +
                    "text VARCHAR(1000), " +
                    "created timestamp)";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            if (Objects.equals(e.getSQLState(), "X0Y32")) {
                return;
            }

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
