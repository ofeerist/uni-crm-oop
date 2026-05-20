package unicrm.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicrm.domain.News;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NewsRepository implements IRepository<News, String> {

    private List<News> newsList = new ArrayList<>();
    private final String fileName = "news.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public NewsRepository() {
        load();
    }

    private void load() {
        try {
            Path path = Paths.get(fileName);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                News[] loaded = gson.fromJson(json, News[].class);
                if (loaded != null) {
                    newsList = new ArrayList<>(Arrays.asList(loaded));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            String json = gson.toJson(newsList);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(News entity) {
        if (entity == null || entity.getId() == null) return;
        delete(entity.getId());
        newsList.add(entity);
        saveToFile();
    }

    @Override
    public News findById(String id) {
        return newsList.stream()
                .filter(n -> Objects.equals(n.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<News> findAll() {
        return new ArrayList<>(newsList);
    }

    @Override
    public void delete(String id) {
        newsList.removeIf(n -> Objects.equals(n.getId(), id));
        saveToFile();
    }
}
