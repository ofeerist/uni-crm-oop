package unicrm.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.ResearchPaper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ResearchPaperRepository implements IRepository<ResearchPaper, String> {

    private List<ResearchPaper> papers = new ArrayList<>();
    private final String fileName = "research_papers.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ResearchPaperRepository() {
        load();
    }

    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                ResearchPaper[] loaded = gson.fromJson(json, ResearchPaper[].class);
                if (loaded != null) {
                    papers = new ArrayList<>(Arrays.asList(loaded));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            String json = gson.toJson(papers);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(ResearchPaper entity) {
        if (entity == null || entity.getId() == null) return;
        delete(entity.getId());
        papers.add(entity);
        saveToFile();
    }

    @Override
    public ResearchPaper findById(String id) {
        return papers.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public ResearchPaper findByTitle(String title) {
        return papers.stream()
                .filter(p -> p.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public List<ResearchPaper> findByAuthor(String username) {
        return papers.stream()
                .filter(p -> Objects.equals(p.getAuthorUsername(), username))
                .toList();
    }

    @Override
    public List<ResearchPaper> findAll() {
        return new ArrayList<>(papers);
    }

    @Override
    public void delete(String id) {
        papers.removeIf(p -> Objects.equals(p.getId(), id));
        saveToFile();
    }
}
