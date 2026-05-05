package kz.edu.unicrm.domain;
import java.util.Objects;
public class Lesson {
    private LessonType type;
    private String room;
    private String time;
    private Course course;
    public LessonType getType() {
        return type;
    }
    public void setType(LessonType type) {
        this.type = type;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson lesson)) return false;
        return Objects.equals(room, lesson.room)
                && Objects.equals(time, lesson.time);
    }
    @Override
    public int hashCode() {
        return Objects.hash(room, time);
    }
    @Override
    public String toString() {
        return "Lesson{type=" + type + ", room='" + room + "', time='" + time + "'}";
    }
}
