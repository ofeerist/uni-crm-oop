package unicrm.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.Room;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RoomRepository implements IRepository<Room, String> {

    private List<Room> rooms = new ArrayList<>();
    private final String fileName = "rooms.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public RoomRepository() {
        load();
    }

    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                Room[] loaded = gson.fromJson(json, Room[].class);
                if (loaded != null) {
                    rooms = new ArrayList<>(Arrays.asList(loaded));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            String json = gson.toJson(rooms);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Room entity) {
        if (entity == null || entity.getNumber() == null) {
            return;
        }

        delete(entity.getNumber());
        rooms.add(entity);
        saveToFile();
    }

    @Override
    public Room findById(String id) {
        return rooms
                .stream()
                .filter(room -> Objects.equals(room.getNumber(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(rooms);
    }

    @Override
    public void delete(String id) {
        rooms.removeIf(room -> Objects.equals(room.getNumber(), id));
        saveToFile();
    }
}
