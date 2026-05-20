package unicrm.domain;

import java.util.Objects;
import java.util.UUID;

public class UniversityJournal {
    private String id;
    private String name;

    public UniversityJournal() {
        this.id = UUID.randomUUID().toString();
    }

    public UniversityJournal(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UniversityJournal j)) return false;
        return Objects.equals(id, j.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return "Journal{'" + name + "'}"; }
}
