package unicrm.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.CourseOffering;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CourseOfferingRepository implements IRepository<CourseOffering, String> {
    private List<CourseOffering> offerings = new ArrayList<>();
    private final String fileName = "course_offerings.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public CourseOfferingRepository() {
        load();
    }

    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                CourseOffering[] loaded = gson.fromJson(json, CourseOffering[].class);
                if (loaded != null) {
                    offerings = new ArrayList<>(Arrays.asList(loaded));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            String json = gson.toJson(offerings);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(CourseOffering entity) {
        if (entity == null || entity.getCourse() == null || entity.getSemester() == null) {
            return;
        }

        delete(getKey(entity));
        offerings.add(entity);
        saveToFile();
    }

    @Override
    public CourseOffering findById(String id) {
        return offerings
                .stream()
                .filter(offering -> Objects.equals(getKey(offering), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<CourseOffering> findAll() {
        return new ArrayList<>(offerings);
    }

    @Override
    public void delete(String id) {
        offerings.removeIf(offering -> Objects.equals(getKey(offering), id));
        saveToFile();
    }

    private String getKey(CourseOffering offering) {
        return offering.getCourse().getCourseId()
                + "-"
                + offering.getSemester().getSeason()
                + "-"
                + offering.getSemester().getYear();
    }
}
