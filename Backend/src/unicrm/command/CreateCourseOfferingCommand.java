package unicrm.command;
import unicrm.domain.AcademicSemester;
import unicrm.domain.Course;
import unicrm.domain.Manager;
import unicrm.domain.Teacher;
import unicrm.domain.User;
import unicrm.repository.CourseRepository;
import unicrm.repository.UserRepository;
import unicrm.service.CourseOfferingService;
import unicrm.service.SemesterService;
import unicrm.session.UserSession;
import java.util.List;
import java.util.Scanner;

public class CreateCourseOfferingCommand {

    private final CourseOfferingService offeringService;
    private final SemesterService semesterService;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final Scanner scanner;

    public CreateCourseOfferingCommand(
            CourseOfferingService offeringService,
            SemesterService semesterService,
            CourseRepository courseRepository,
            UserRepository userRepository,
            UserSession userSession,
            Scanner scanner
    ) {
        this.offeringService = offeringService;
        this.semesterService = semesterService;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Manager)) {
            return;
        }
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        Course course = courses.getFirst();
        System.out.print("Enter season (FALL, SPRING, SUMMER): ");
        String seasonInput = scanner.nextLine();

        System.out.print("Enter year: ");
        int year = Integer.parseInt(scanner.nextLine());

        AcademicSemester semester =
                semesterService.findSemester(
                        Enum.valueOf(unicrm.domain.Season.class,
                                seasonInput.toUpperCase()),year);

        if (semester == null) {
            System.out.println("Semester not found.");
            return;
        }

        List<User> teachers = userRepository.findByRole("Teacher");

        if (teachers.isEmpty()) {
            System.out.println("No teachers found.");
            return;
        }

        Teacher teacher = (Teacher) teachers.getFirst();

        System.out.print("Enter capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());

        offeringService.createOffering(
                course,
                semester,
                teacher,
                capacity
        );

        System.out.println("Course offering created.");
    }
}