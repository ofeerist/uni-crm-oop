package unicrm.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.Enrollment;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EnrollmentRepository implements IRepository<Enrollment, String> {
    private List<Enrollment> enrollments = new ArrayList<>();
    private final String fileName = "enrollments.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public EnrollmentRepository() {
        load();
    }

    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                Enrollment[] loaded = gson.fromJson(json, Enrollment[].class);
                if (loaded != null) {
                    enrollments = new ArrayList<>(Arrays.asList(loaded));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            String json = gson.toJson(enrollments);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Enrollment entity) {
        if (entity == null || entity.getStudent() == null || entity.getCourseOffering() == null) {
            return;
        }

        delete(getKey(entity));
        enrollments.add(entity);
        saveToFile();
    }

    @Override
    public Enrollment findById(String id) {
        return enrollments
                .stream()
                .filter(enrollment -> Objects.equals(getKey(enrollment), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    @Override
    public void delete(String id) {
        enrollments.removeIf(enrollment -> Objects.equals(getKey(enrollment), id));
        saveToFile();
    }

    private String getKey(Enrollment enrollment) {
        return enrollment.getStudent().getId()
                + "-"
                + enrollment.getCourseOffering().getCourse().getCourseId()
                + "-"
                + enrollment.getCourseOffering().getSemester().getSeason()
                + "-"
                + enrollment.getCourseOffering().getSemester().getYear();
    }
}
