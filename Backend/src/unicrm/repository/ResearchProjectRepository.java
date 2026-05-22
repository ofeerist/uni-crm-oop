package unicrm.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import unicrm.domain.ResearchProject;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResearchProjectRepository {

    private static final String FILE_NAME = "research_projects.json";
    private final Gson gson;

    public ResearchProjectRepository() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<ResearchProject> findAll() {
        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                return new ArrayList<>();
            }

            FileReader reader = new FileReader(file);

            Type type = new TypeToken<List<ResearchProject>>(){}.getType();

            List<ResearchProject> projects = gson.fromJson(reader, type);

            reader.close();

            return projects != null ? projects : new ArrayList<>();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveAll(List<ResearchProject> projects) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);

            gson.toJson(projects, writer);

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProject(ResearchProject project) {
        List<ResearchProject> projects = findAll();

        projects.add(project);

        saveAll(projects);
    }
}