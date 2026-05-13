package unicrm.repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.Manager;
import unicrm.domain.Student;
import unicrm.domain.Teacher;
import unicrm.domain.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class UserRepository implements IRepository<User, String> {
    private UserDataWrapper data = new UserDataWrapper();
    private final String fileName = "users.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public UserRepository() {
        load();
    }
    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                data = gson.fromJson(json, UserDataWrapper.class);
                if (data == null) {
                    data = new UserDataWrapper();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveToFile() {
        try {
            String json = gson.toJson(data);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void save(User entity) {
        if (entity == null || entity.getId() == null) {
            return;
        }
        delete(entity.getId());
        if (entity instanceof Student student) {
            data.getStudents().add(student);
        } else if (entity instanceof Teacher teacher) {
            data.getTeachers().add(teacher);
        } else if (entity instanceof Manager manager) {
            data.getManagers().add(manager);
        }
        saveToFile();
    }
    @Override
    public User findById(String id) {
        return findAll()
                .stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst()
                .orElse(null);
    }
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.addAll(data.getStudents());
        users.addAll(data.getTeachers());
        users.addAll(data.getManagers());
        return users;
    }
    @Override
    public void delete(String id) {
        data.getStudents().removeIf(user -> Objects.equals(user.getId(), id));
        data.getTeachers().removeIf(user -> Objects.equals(user.getId(), id));
        data.getManagers().removeIf(user -> Objects.equals(user.getId(), id));
        saveToFile();
    }
    public List<User> findByRole(String role) {
        List<User> result = new ArrayList<>();
        for (User user : findAll()) {
            if (user.getClass().getSimpleName().equalsIgnoreCase(role)) {
                result.add(user);
            }
        }
        return result;
    }
}
