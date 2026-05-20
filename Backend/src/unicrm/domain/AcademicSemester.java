package unicrm.domain;

import java.util.Objects;

public class AcademicSemester {
    private Season season;
    private int year;

    public AcademicSemester(Season season, int year) {
        this.season = season;
        this.year = year;
    }

    public Season getSeason() { return season; }
    public void setSeason(Season season) { this.season = season; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicSemester that = (AcademicSemester) o;
        return year == that.year && season == that.season;
    }

    @Override
    public int hashCode() {
        return Objects.hash(season, year);
    }
}
