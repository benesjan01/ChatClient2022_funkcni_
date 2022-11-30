import Models.ChatClients.API.ApiChatClient;
import Models.ChatClients.ChatClient;
import Models.ChatClients.ChatFileOperation.ChatFileOperations;
import Models.ChatClients.ChatFileOperation.JsonChatFileOperations;
import Models.ChatClients.ChatFileOperation.ToFileChatClient;
import Models.ChatClients.Database.DatabaseOperations;
import Models.ChatClients.Database.DbInitializer;
import Models.ChatClients.Database.JdbcDatabaseOperations;
import Models.ChatClients.Message;
import Models.ChatClients.inMemoryChatClient;
import Models.Gui.MainFrame;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String databaseDriver = "org.apache.derby.jdbc.EmbeddedDriver";
        String databaseUrl = "jdbc:derby:ChatClientDb_skB";
        try {
            DbInitializer dbInitializer = new DbInitializer(databaseDriver, databaseUrl);
            dbInitializer.Init();
            ChatFileOperations chatFileOperations = new JsonChatFileOperations();


        } catch (Exception e) {
            e.printStackTrace();
        }

        ChatClient client = new ApiChatClient();
        Class<ApiChatClient> reflectionExample = ApiChatClient.class;
        List<Field> fields = getAllFields(reflectionExample);
        System.out.println("Class name: " + reflectionExample.getSimpleName() + " | " + reflectionExample.getName());
        //ChatClient client = new ToFileChatClient(chatFileOperations);
        //ChatClient client = new inMemoryChatClient();
        MainFrame window = new MainFrame(800, 800, client);
    }

    private static void test(){
        ChatClient client = new inMemoryChatClient();
        client.login("Honza");
        System.out.println(client.isAuthenticated());
        client.sendMessage("ahooj");
        client.sendMessage("cus");
        for (Message msg :
                client.getMessages()) {
            System.out.println(msg.getText());
        }
        client.logout();
        System.out.println(client.isAuthenticated());
    }
    private static List<Field> getAllFields(Class<?> cls){
        List<Field> fieldList = new ArrayList<>();
        for (Field f : cls.getDeclaredFields()){
            fieldList.add(f);
        }
        return fieldList;
    }
}