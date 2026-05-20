package unicrm.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.UniversityJournal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JournalRepository implements IRepository<UniversityJournal, String> {

    private List<UniversityJournal> journals = new ArrayList<>();
    private final String fileName = "journals.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public JournalRepository() {
        load();
    }

    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                UniversityJournal[] loaded = gson.fromJson(json, UniversityJournal[].class);
                if (loaded != null) {
                    journals = new ArrayList<>(Arrays.asList(loaded));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            String json = gson.toJson(journals);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(UniversityJournal entity) {
        if (entity == null || entity.getId() == null) return;
        delete(entity.getId());
        journals.add(entity);
        saveToFile();
    }

    @Override
    public UniversityJournal findById(String id) {
        return journals.stream()
                .filter(j -> Objects.equals(j.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public boolean existsByName(String name) {
        return journals.stream().anyMatch(j -> j.getName().equalsIgnoreCase(name));
    }

    @Override
    public List<UniversityJournal> findAll() {
        return new ArrayList<>(journals);
    }

    @Override
    public void delete(String id) {
        journals.removeIf(j -> Objects.equals(j.getId(), id));
        saveToFile();
    }
}
