package unicrm.repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.Message;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MessageRepository implements IRepository<Message, String> {

    private List<Message> messages = new ArrayList<>();
    private final String fileName = "messages.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public MessageRepository() {
        load();
    }

    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                Message[] loaded = gson.fromJson(json, Message[].class);
                if (loaded != null) {
                    messages = new ArrayList<>(Arrays.asList(loaded));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            String json = gson.toJson(messages);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Message entity) {
        if (entity == null || entity.getId() == null) {
            return;
        }
        delete(entity.getId());
        messages.add(entity);
        saveToFile();
    }

    @Override
    public Message findById(String id) {
        return messages
                .stream()
                .filter(message -> Objects.equals(message.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Message> findAll() {
        return new ArrayList<>(messages);
    }

    @Override
    public void delete(String id) {
        messages.removeIf(message -> Objects.equals(message.getId(), id));
        saveToFile();
    }
}
