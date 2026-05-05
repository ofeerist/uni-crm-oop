package kz.edu.unicrm.service;
import kz.edu.unicrm.domain.Course;
import kz.edu.unicrm.domain.Mark;
import kz.edu.unicrm.domain.MarkData;
import kz.edu.unicrm.domain.Student;
import kz.edu.unicrm.domain.Teacher;
import kz.edu.unicrm.repository.CourseRepository;
import kz.edu.unicrm.repository.UserRepository;
import java.util.UUID;
public class AcademicService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    public AcademicService(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }
    public void putMark(Teacher teacher, Student student, Course course, MarkData data) {
        Mark mark = new Mark();
        mark.setId(UUID.randomUUID().toString());
        mark.setFirstAttestation(data.getFirstAttestation());
        mark.setSecondAttestation(data.getSecondAttestation());
        mark.setFinalExam(data.getFinalExam());
        mark.setStudent(student);
        mark.setCourse(course);
        student.getMarks().add(mark);
        course.getMarks().add(mark);
        userRepository.save(student);
        courseRepository.save(course);
    }
    public void checkFailLimits(Student student) {
        if (student.getFailCount() >= 3) {
            System.out.println("Student " + student.getUsername() + " exceeded fail limit.");
        }
    }
    public String getTranscript(Student student) {
        StringBuilder builder = new StringBuilder();
        builder.append("Transcript for: ")
                .append(student.getUsername())
                .append(System.lineSeparator());
        for (Mark mark : student.getMarks()) {
            builder.append("Course: ")
                    .append(mark.getCourse().getName())
                    .append(" | Total Mark: ")
                    .append(mark.getTotal())
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
