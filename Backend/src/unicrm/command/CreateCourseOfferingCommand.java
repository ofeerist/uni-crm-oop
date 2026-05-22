package unicrm.command;

import unicrm.domain.AcademicSemester;
import unicrm.domain.Course;
import unicrm.domain.CourseOffering;
import unicrm.domain.Lesson;
import unicrm.domain.Manager;
import unicrm.domain.Season;
import unicrm.domain.Teacher;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.CourseRepository;
import unicrm.repository.UserRepository;
import unicrm.service.CourseOfferingService;
import unicrm.service.ScheduleService;
import unicrm.service.SemesterService;
import unicrm.session.UserSession;

import java.util.List;
import java.util.Scanner;

public class CreateCourseOfferingCommand {

    private final CourseOfferingService offeringService;
    private final ScheduleService scheduleService;
    private final SemesterService semesterService;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public CreateCourseOfferingCommand(
            CourseOfferingService offeringService,
            ScheduleService scheduleService,
            SemesterService semesterService,
            CourseRepository courseRepository,
            UserRepository userRepository,
            UserSession userSession,
            Scanner scanner
    ) {
        this.offeringService = offeringService;
        this.scheduleService = scheduleService;
        this.semesterService = semesterService;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Manager)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_COURSES_FOUND));
            return;
        }
        System.out.println(localization.get(LocalizationKey.OFFERING_SELECT_COURSE));
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            System.out.println((i + 1) + ". " + c.getName() + " [" + c.getCourseId() + ", " + c.getCredits() + " cr.]");
        }
        System.out.print(localization.get(LocalizationKey.MENU_CHOICE));
        int courseIdx = parseIndex(scanner.nextLine(), courses.size());
        if (courseIdx < 0) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        Course course = courses.get(courseIdx);

        List<User> teachers = userRepository.findByRole("Teacher");
        if (teachers.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_TEACHERS_FOUND));
            return;
        }
        System.out.println(localization.get(LocalizationKey.OFFERING_SELECT_TEACHER));
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i + 1) + ". " + teachers.get(i).getUsername());
        }
        System.out.print(localization.get(LocalizationKey.MENU_CHOICE));
        int teacherIdx = parseIndex(scanner.nextLine(), teachers.size());
        if (teacherIdx < 0) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        Teacher teacher = (Teacher) teachers.get(teacherIdx);

        System.out.print(localization.get(LocalizationKey.ENTER_SEMESTER_SEASON));
        String seasonInput = scanner.nextLine().trim().toUpperCase();
        Season season;
        try {
            season = Season.valueOf(seasonInput);
        } catch (IllegalArgumentException e) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }

        System.out.print(localization.get(LocalizationKey.ENTER_SEMESTER_YEAR));
        int year;
        try {
            year = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }

        AcademicSemester semester = semesterService.findSemester(season, year);
        if (semester == null) {
            System.out.println(localization.get(LocalizationKey.SEMESTER_NOT_FOUND));
            return;
        }

        // Enter Capacity
        System.out.print(localization.get(LocalizationKey.ENTER_OFFERING_CAPACITY));
        int capacity;
        try {
            capacity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }

        CourseOffering offering = offeringService.createOffering(course, semester, teacher, capacity);
        boolean scheduled = scheduleService.generateSchedule(offering);

        if (!scheduled) {
            System.out.println(localization.get(LocalizationKey.NO_ROOMS_FOUND));
        }

        offeringService.save(offering);
        System.out.println(localization.get(LocalizationKey.OFFERING_CREATED));

        List<Lesson> lessons = offering.getLessons();
        if (!lessons.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.SCHEDULE_HEADER));
            for (Lesson lesson : lessons) {
                System.out.println(localization.format(
                        LocalizationKey.LESSON_FORMAT,
                        lesson.getType(),
                        lesson.getTimeSlot(),
                        lesson.getRoom() != null ? lesson.getRoom().getName() : "—"
                ));
            }
        }
    }

    private int parseIndex(String input, int max) {
        try {
            int idx = Integer.parseInt(input.trim()) - 1;
            return (idx >= 0 && idx < max) ? idx : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
