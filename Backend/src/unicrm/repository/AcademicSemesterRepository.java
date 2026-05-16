package unicrm.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.AcademicSemester;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AcademicSemesterRepository implements IRepository<AcademicSemester, String> {

    private List<AcademicSemester> semesters = new ArrayList<>();
    private final String fileName = "semesters.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public AcademicSemesterRepository() {
        load();
    }

    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                AcademicSemester[] loaded = gson.fromJson(json, AcademicSemester[].class);
                if (loaded != null) {
                    semesters = new ArrayList<>(Arrays.asList(loaded));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            String json = gson.toJson(semesters);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(AcademicSemester entity) {
        if (entity == null || entity.getSeason() == null) {
            return;
        }

        delete(getKey(entity));
        semesters.add(entity);
        saveToFile();
    }

    @Override
    public AcademicSemester findById(String id) {
        return semesters
                .stream()
                .filter(semester -> Objects.equals(getKey(semester), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<AcademicSemester> findAll() {
        return new ArrayList<>(semesters);
    }

    @Override
    public void delete(String id) {
        semesters.removeIf(semester -> Objects.equals(getKey(semester), id));
        saveToFile();
    }

    private String getKey(AcademicSemester semester) {
        return semester.getSeason() + "-" + semester.getYear();
    }
}
