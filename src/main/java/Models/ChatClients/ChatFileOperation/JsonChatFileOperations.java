package Models.ChatClients.ChatFileOperation;

import Models.ChatClients.LocalDateTimeDeserializer;
import Models.ChatClients.LocalDateTimeSerializer;
import Models.ChatClients.Message;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JsonChatFileOperations implements ChatFileOperations{
    private Gson gson;
    private static final String MESSAGES_FILE = "./messages.json";
    public JsonChatFileOperations(){
        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gson = gsonBuilder.create();
    }
    @Override
    public List<Message> loadMessages() {
        try {
            FileReader reader = new FileReader(MESSAGES_FILE);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder jsonText = new StringBuilder();

            String line;
            while((line = bufferedReader.readLine()) != null){
                jsonText.append(line);
            }
            reader.close();
            Type targetType = new TypeToken<ArrayList<Message>>(){}.getType();
            return gson.fromJson(jsonText.toString(), targetType);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeMessages(List<Message> messages) {
        String jsonText = gson.toJson(messages);
        //System.out.println(jsonText);
        try {
            FileWriter writer = new FileWriter(MESSAGES_FILE);
            writer.write(jsonText);
            writer.flush();
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> loadLoggedUsers() {
        return null;
    }

    @Override
    public void writeLoggedUsers(List<String> users) {

    }
}


