package unicrm.domain;

public class Lesson {
    private CourseOffering courseOffering;
    private Room room;
    private LessonType type;
    private String timeSlot;

    public Lesson(CourseOffering courseOffering, Room room, LessonType type, String timeSlot) {
        this.courseOffering = courseOffering;
        this.room = room;
        this.type = type;
        this.timeSlot = timeSlot;
    }

    public CourseOffering getCourseOffering() { return courseOffering; }
    public void setCourseOffering(CourseOffering courseOffering) { this.courseOffering = courseOffering; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public LessonType getType() { return type; }
    public void setType(LessonType type) { this.type = type; }
    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }
}
