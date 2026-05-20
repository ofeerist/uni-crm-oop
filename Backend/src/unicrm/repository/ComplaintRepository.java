package unicrm.repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.Complaint;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ComplaintRepository implements IRepository<Complaint, String> {

    private List<Complaint> complaints = new ArrayList<>();
    private final String fileName = "complaints.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ComplaintRepository() {
        load();
    }

    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                Complaint[] loaded = gson.fromJson(json, Complaint[].class);
                if (loaded != null) {
                    complaints = new ArrayList<>(Arrays.asList(loaded));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            String json = gson.toJson(complaints);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Complaint entity) {
        if (entity == null || entity.getId() == null) {
            return;
        }
        delete(entity.getId());
        complaints.add(entity);
        saveToFile();
    }

    @Override
    public Complaint findById(String id) {
        return complaints
                .stream()
                .filter(c -> Objects.equals(c.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Complaint> findAll() {
        return new ArrayList<>(complaints);
    }

    @Override
    public void delete(String id) {
        complaints.removeIf(c -> Objects.equals(c.getId(), id));
        saveToFile();
    }
}
