package unicrm.repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.TechRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TechRequestRepository implements IRepository<TechRequest, String> {

    private List<TechRequest> requests = new ArrayList<>();
    private final String fileName = "requests.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public TechRequestRepository() {
        load();
    }

    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                TechRequest[] loaded = gson.fromJson(json, TechRequest[].class);
                if (loaded != null) {
                    requests = new ArrayList<>(Arrays.asList(loaded));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            String json = gson.toJson(requests);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(TechRequest entity) {
        if (entity == null || entity.getId() == null) {
            return;
        }
        delete(entity.getId());
        requests.add(entity);
        saveToFile();
    }

    @Override
    public TechRequest findById(String id) {
        return requests
                .stream()
                .filter(r -> Objects.equals(r.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<TechRequest> findAll() {
        return new ArrayList<>(requests);
    }

    @Override
    public void delete(String id) {
        requests.removeIf(r -> Objects.equals(r.getId(), id));
        saveToFile();
    }
}