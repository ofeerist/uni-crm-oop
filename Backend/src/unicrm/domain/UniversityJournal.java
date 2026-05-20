package unicrm.domain;

import java.util.Objects;

public class UniversityJournal {
    private String name;

    public UniversityJournal() {}

    public UniversityJournal(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UniversityJournal j)) return false;
        return Objects.equals(name, j.name);
    }

    @Override
    public int hashCode() { return Objects.hash(name); }

    @Override
    public String toString() { return "Journal{'" + name + "'}"; }
}
