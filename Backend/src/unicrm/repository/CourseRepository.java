package unicrm.repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.Course;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
public class CourseRepository implements IRepository<Course, String> {
    private List<Course> courses = new ArrayList<>();
    private final String fileName = "courses.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public CourseRepository() {
        load();
    }
    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                Course[] loadedCourses = gson.fromJson(json, Course[].class);
                if (loadedCourses != null) {
                    courses = new ArrayList<>(Arrays.asList(loadedCourses));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveToFile() {
        try {
            String json = gson.toJson(courses);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void save(Course entity) {
        if (entity == null || entity.getCourseId() == null) {
            return;
        }
        delete(entity.getCourseId());
        courses.add(entity);
        saveToFile();
    }
    @Override
    public Course findById(String id) {
        return courses
                .stream()
                .filter(course -> Objects.equals(course.getCourseId(), id))
                .findFirst()
                .orElse(null);
    }
    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }
    @Override
    public void delete(String id) {
        courses.removeIf(course -> Objects.equals(course.getCourseId(), id));
    }
}