package unicrm.command;

import unicrm.domain.Course;
import unicrm.domain.CourseCategory;
import unicrm.domain.Manager;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.CourseRepository;
import unicrm.session.UserSession;

import java.util.Scanner;

public class CreateCourseCommand {

    private final CourseRepository courseRepository;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public CreateCourseCommand(CourseRepository courseRepository, UserSession userSession, Scanner scanner) {
        this.courseRepository = courseRepository;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Manager)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        System.out.print(localization.get(LocalizationKey.ENTER_COURSE_CODE));
        String courseId = scanner.nextLine().trim();

        System.out.print(localization.get(LocalizationKey.ENTER_COURSE_NAME));
        String name = scanner.nextLine().trim();

        System.out.print(localization.get(LocalizationKey.ENTER_CREDITS));
        int credits = 0;
        try {
            credits = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Setting credits to 0.");
        }

        System.out.print(localization.get(LocalizationKey.SELECT_CATEGORY));
        String categoryInput = scanner.nextLine().trim().toUpperCase();
        CourseCategory category;
        try {
            category = CourseCategory.valueOf(categoryInput);
        } catch (IllegalArgumentException e) {
            category = CourseCategory.FREE_ELECTIVE;
            System.out.println("Unknown category. Using FREE_ELECTIVE.");
        }

        Course course = new Course();
        course.setCourseId(courseId);
        course.setName(name);
        course.setCredits(credits);
        course.setCategory(category);
        courseRepository.save(course);

        System.out.println(localization.format(LocalizationKey.COURSE_CREATED, name));
    }
}
