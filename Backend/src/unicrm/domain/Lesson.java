package unicrm.domain;

import java.util.Objects;
import java.util.UUID;

public class Lesson {
    private String id;
    private transient CourseOffering courseOffering;
    private Room room;
    private LessonType type;
    private String timeSlot;

    public Lesson() {
        this.id = UUID.randomUUID().toString();
    }

    public Lesson(CourseOffering courseOffering, Room room, LessonType type, String timeSlot) {
        this.id = UUID.randomUUID().toString();
        this.courseOffering = courseOffering;
        this.room = room;
        this.type = type;
        this.timeSlot = timeSlot;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public CourseOffering getCourseOffering() { return courseOffering; }
    public void setCourseOffering(CourseOffering courseOffering) { this.courseOffering = courseOffering; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public LessonType getType() { return type; }
    public void setType(LessonType type) { this.type = type; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson lesson)) return false;
        return Objects.equals(id, lesson.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Lesson{type=" + type + ", slot='" + timeSlot + "', room=" +
                (room != null ? room.getName() : "null") + "}";
    }
}
