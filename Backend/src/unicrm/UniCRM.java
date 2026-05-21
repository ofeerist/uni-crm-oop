package unicrm;

import unicrm.command.*;
import unicrm.domain.*;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.*;
import unicrm.service.*;
import unicrm.session.UserSession;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class UniCRM {

    private static final LocalizationService localization = LocalizationService.getInstance();

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        CourseRepository courseRepository = new CourseRepository();
        CourseOfferingRepository offeringRepository = new CourseOfferingRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
        TechRequestRepository techRepo = new TechRequestRepository();

        AuthService authService = new AuthService(userRepository);
        Scanner scanner = new Scanner(System.in);

        chooseLanguage(scanner);

        List<User> managers = userRepository.findByRole("Manager");
        if (managers.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.ADMIN_NOT_FOUND));
            System.out.print(localization.get(LocalizationKey.ADMIN_USERNAME));
            String username = scanner.nextLine();
            System.out.print(localization.get(LocalizationKey.ADMIN_PASSWORD));
            String password = scanner.nextLine();

            Manager admin = new Manager();
            admin.setId(UUID.randomUUID().toString());
            admin.setUsername(username);
            admin.setPassword(password);
            admin.setType(ManagerType.OR);
            admin.setPreferredLanguage(localization.getCurrentLanguage());
            userRepository.save(admin);
            System.out.println(localization.get(LocalizationKey.ADMIN_CREATED));
        }

        while (true) {
            System.out.println();
            System.out.println(localization.get(LocalizationKey.MAIN_MENU_TITLE));
            System.out.println(localization.get(LocalizationKey.MAIN_MENU_LOGIN));
            System.out.println(localization.get(LocalizationKey.MAIN_MENU_EXIT));
            System.out.print(localization.get(LocalizationKey.MENU_CHOICE));

            String choice = scanner.nextLine();

            if (choice.equals("2")) {
                break;
            }
            if (choice.equals("1")) {
                System.out.print(localization.get(LocalizationKey.ADMIN_USERNAME));
                String username = scanner.nextLine();
                System.out.print(localization.get(LocalizationKey.ADMIN_PASSWORD));
                String password = scanner.nextLine();

                authService.login(username, password);
                User currentUser = UserSession.getInstance().getCurrentUser();

                if (currentUser != null) {
                    localization.setLanguage(resolveUserLanguage(currentUser));
                    System.out.println(localization.format(LocalizationKey.LOGIN_SUCCESS, currentUser.getUsername()));
                    runUserMenu(scanner, userRepository, courseRepository, offeringRepository, enrollmentRepository, techRepo);
                } else {
                    System.out.println(localization.get(LocalizationKey.LOGIN_ERROR));
                }
            }
        }
        scanner.close();
    }

    private static void chooseLanguage(Scanner scanner) {
        System.out.println(localization.get(LocalizationKey.LANGUAGE_SELECT_TITLE));
        System.out.println(localization.get(LocalizationKey.LANGUAGE_SELECT_RU));
        System.out.println(localization.get(LocalizationKey.LANGUAGE_SELECT_EN));
        System.out.println(localization.get(LocalizationKey.LANGUAGE_SELECT_KZ));
        System.out.print(localization.get(LocalizationKey.LANGUAGE_SELECT_CHOICE));

        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            localization.setLanguage(Language.RU);
            return;
        }
        if (choice.equals("2")) {
            localization.setLanguage(Language.EN);
            return;
        }
        if (choice.equals("3")) {
            localization.setLanguage(Language.KZ);
            System.out.println(localization.get(LocalizationKey.LANGUAGE_NOT_SUPPORTED));
            return;
        }
        localization.setLanguage(Language.RU);
    }

    private static void runUserMenu(Scanner scanner, UserRepository userRepository,
                                    CourseRepository courseRepository, CourseOfferingRepository offeringRepository,
                                    EnrollmentRepository enrollmentRepository, TechRequestRepository techRepo) {

        CourseRegistrationService courseRegService = new CourseRegistrationService(userRepository, courseRepository);
        ManagerService managerService = new ManagerService(courseRepository, userRepository);
        EnrollmentService enrollmentService = new EnrollmentService(enrollmentRepository);
        TechSupportService techSupportService = new TechSupportService(techRepo, userRepository);
        CourseOfferingService courseOfferingService = new CourseOfferingService(offeringRepository);
        RoomRepository roomRepository = new RoomRepository();
        ScheduleService scheduleService = new ScheduleService(offeringRepository, roomRepository);
        SemesterService semesterService = new SemesterService(new AcademicSemesterRepository());
        AcademicService academicService = new AcademicService(userRepository, courseRepository);
        CommunicationService communicationService = new CommunicationService(
                new MessageRepository(), new ComplaintRepository(), userRepository);
        JournalRepository journalRepository = new JournalRepository();
        ResearchService researchService = new ResearchService(new ResearchPaperRepository(), journalRepository);
        NewsService newsService = new NewsService(new NewsRepository());

        UserSession userSession = UserSession.getInstance();

        ApproveStudentRegistrationCommand approveRegCmd =
                new ApproveStudentRegistrationCommand(courseRegService, userSession);
        AssignCourseToTeacherCommand assignCourseCmd =
                new AssignCourseToTeacherCommand(managerService, userRepository, courseRepository, userSession);
        ChangeEnrollmentStatusCommand changeEnrollCmd =
                new ChangeEnrollmentStatusCommand(enrollmentService, enrollmentRepository, userSession, scanner);
        ChangeRequestStatusCommand changeReqStatCmd =
                new ChangeRequestStatusCommand(techSupportService, techRepo, userSession);
        CreateCourseOfferingCommand createOfferingCmd =
                new CreateCourseOfferingCommand(courseOfferingService, scheduleService, semesterService, courseRepository, userRepository, userSession, scanner);
        CreateSemesterCommand createSemesterCmd =
                new CreateSemesterCommand(semesterService, userSession, scanner);
        PutMarkCommand putMarkCmd =
                new PutMarkCommand(academicService, userRepository, userSession);
        RegisterForCourseCommand regCourseCmd =
                new RegisterForCourseCommand(courseRegService, courseRepository, userSession);
        RegisterForOfferingCommand regOfferingCmd =
                new RegisterForOfferingCommand(enrollmentService, offeringRepository, userSession, scanner);
        ViewScheduleCommand viewScheduleCmd =
                new ViewScheduleCommand(enrollmentRepository, userSession);
        SendComplaintCommand sendComplaintCmd =
                new SendComplaintCommand(communicationService, userRepository, userSession, scanner);
        SendMessageCommand sendMsgCmd =
                new SendMessageCommand(communicationService, userRepository, userSession, scanner);
        ViewMessagesCommand viewMsgsCmd =
                new ViewMessagesCommand(communicationService, userRepository, userSession);
        ViewNewRequestsCommand viewReqCmd =
                new ViewNewRequestsCommand(techSupportService, userSession, techRepo);
        ViewTranscriptCommand viewTranscriptCmd =
                new ViewTranscriptCommand(academicService, userSession);
        CreateCourseCommand createCourseCmd =
                new CreateCourseCommand(courseRepository, userSession, scanner);
        AddRoomCommand addRoomCmd =
                new AddRoomCommand(roomRepository, userSession, scanner);
        PublishNewsCommand publishNewsCmd =
                new PublishNewsCommand(newsService, userSession, scanner);
        SubscribeToJournalCommand subscribeJournalCmd =
                new SubscribeToJournalCommand(journalRepository, userRepository, userSession, scanner);
        CreateJournalCommand createJournalCmd =
                new CreateJournalCommand(researchService, userSession, scanner);
        PublishPaperCommand publishPaperCmd =
                new PublishPaperCommand(researchService, userSession, scanner);
        PrintMyPapersCommand printMyPapersCmd =
                new PrintMyPapersCommand(researchService, userSession);
        GetCitationCommand getCitationCmd =
                new GetCitationCommand(researchService, userSession, scanner);
        ViewTopResearcherCommand viewTopResearcherCmd =
                new ViewTopResearcherCommand(researchService, userSession);
        BecomeResearcherCommand becomeResearcherCmd =
                new BecomeResearcherCommand(researchService, userRepository, userSession);
        SendTechRequestCommand sendTechRequestCmd =
                new SendTechRequestCommand(techSupportService, userSession, scanner);
        ChangeLanguageCommand changeLangCmd =
                new ChangeLanguageCommand(userRepository, userSession, scanner);
        ViewTeacherCoursesCommand viewTeacherCoursesCmd =
                new ViewTeacherCoursesCommand(offeringRepository, userSession);
        ViewTeacherScheduleCommand viewTeacherScheduleCmd =
                new ViewTeacherScheduleCommand(offeringRepository, userSession);

        Runnable researcherOrBecomeCmd = () -> {
            if (userSession.getCurrentUser() instanceof ResearcherDecorator) publishPaperCmd.execute();
            else becomeResearcherCmd.execute();
        };
        Runnable myPapersIfResearcher = () -> {
            if (userSession.getCurrentUser() instanceof ResearcherDecorator) printMyPapersCmd.execute();
        };

        Map<String, Runnable> managerCommands = new HashMap<>();
        managerCommands.put("1",  () -> addUser(scanner, userRepository));
        managerCommands.put("2",  approveRegCmd::execute);
        managerCommands.put("3",  assignCourseCmd::execute);
        managerCommands.put("4",  changeEnrollCmd::execute);
        managerCommands.put("5",  createOfferingCmd::execute);
        managerCommands.put("6",  createSemesterCmd::execute);
        managerCommands.put("7",  createCourseCmd::execute);
        managerCommands.put("8",  addRoomCmd::execute);
        managerCommands.put("9",  sendMsgCmd::execute);
        managerCommands.put("10", viewMsgsCmd::execute);
        managerCommands.put("11", publishNewsCmd::execute);
        managerCommands.put("12", subscribeJournalCmd::execute);
        managerCommands.put("13", viewTopResearcherCmd::execute);
        managerCommands.put("14", getCitationCmd::execute);
        managerCommands.put("15", sendTechRequestCmd::execute);
        managerCommands.put("16", researcherOrBecomeCmd);
        managerCommands.put("17", myPapersIfResearcher);
        managerCommands.put("18", createJournalCmd::execute);
        managerCommands.put("20", changeLangCmd::execute);

        Map<String, Runnable> studentCommands = new HashMap<>();
        studentCommands.put("1",  regOfferingCmd::execute);
        studentCommands.put("2",  viewScheduleCmd::execute);
        studentCommands.put("3",  viewTranscriptCmd::execute);
        studentCommands.put("4",  subscribeJournalCmd::execute);
        studentCommands.put("5",  viewTopResearcherCmd::execute);
        studentCommands.put("6",  getCitationCmd::execute);
        studentCommands.put("7",  sendTechRequestCmd::execute);
        studentCommands.put("8",  researcherOrBecomeCmd);
        studentCommands.put("9",  myPapersIfResearcher);
        studentCommands.put("20", changeLangCmd::execute);

        Map<String, Runnable> teacherCommands = new HashMap<>();
        teacherCommands.put("1",  putMarkCmd::execute);
        teacherCommands.put("2",  sendComplaintCmd::execute);
        teacherCommands.put("3",  viewTeacherCoursesCmd::execute);
        teacherCommands.put("4",  viewTeacherScheduleCmd::execute);
        teacherCommands.put("5",  subscribeJournalCmd::execute);
        teacherCommands.put("6",  viewTopResearcherCmd::execute);
        teacherCommands.put("7",  getCitationCmd::execute);
        teacherCommands.put("8",  researcherOrBecomeCmd);
        teacherCommands.put("9",  myPapersIfResearcher);
        teacherCommands.put("10", sendMsgCmd::execute);
        teacherCommands.put("11", viewMsgsCmd::execute);
        teacherCommands.put("12", sendTechRequestCmd::execute);
        teacherCommands.put("20", changeLangCmd::execute);

        Map<String, Runnable> techCommands = new HashMap<>();
        techCommands.put("1",  viewReqCmd::execute);
        techCommands.put("2",  changeReqStatCmd::execute);
        techCommands.put("3",  subscribeJournalCmd::execute);
        techCommands.put("4",  viewTopResearcherCmd::execute);
        techCommands.put("5",  getCitationCmd::execute);
        techCommands.put("6",  researcherOrBecomeCmd);
        techCommands.put("7",  myPapersIfResearcher);
        techCommands.put("9",  sendMsgCmd::execute);
        techCommands.put("10", viewMsgsCmd::execute);
        techCommands.put("11", sendTechRequestCmd::execute);
        techCommands.put("20", changeLangCmd::execute);

        while (true) {
            User currentUser = userSession.getCurrentUser();
            if (currentUser == null) {
                break;
            }
            User effectiveUser = userSession.getEffectiveUser();
            boolean isResearcher = currentUser instanceof ResearcherDecorator;

            System.out.println();
            System.out.println(localization.get(LocalizationKey.ADMIN_MENU_TITLE));
            buildMenu(effectiveUser, isResearcher)
                    .forEach((num, label) -> System.out.println(num + ". " + label));
            System.out.println("0. " + localization.get(LocalizationKey.ADMIN_MENU_LOGOUT));
            System.out.print(localization.get(LocalizationKey.MENU_CHOICE));

            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                userSession.clearSession();
                System.out.println(localization.get(LocalizationKey.SESSION_CLOSED));
                break;
            }

            Map<String, Runnable> activeCommands = null;
            if (effectiveUser instanceof Manager) activeCommands = managerCommands;
            else if (effectiveUser instanceof Student) activeCommands = studentCommands;
            else if (effectiveUser instanceof Teacher) activeCommands = teacherCommands;
            else if (effectiveUser instanceof TechSupportSpecialist) activeCommands = techCommands;

            if (activeCommands != null) {
                Runnable cmd = activeCommands.get(choice);
                if (cmd != null) cmd.run();
            }
        }
    }

    private static void addUser(Scanner scanner, UserRepository userRepository) {
        System.out.print(localization.get(LocalizationKey.ENTER_ROLE));
        String role = scanner.nextLine();
        System.out.print(localization.get(LocalizationKey.ENTER_USERNAME));
        String newUsername = scanner.nextLine();
        System.out.print(localization.get(LocalizationKey.ENTER_PASSWORD));
        String newPassword = scanner.nextLine();

        User newUser = createUserByRole(role);
        if (newUser == null) {
            System.out.println(localization.get(LocalizationKey.UNKNOWN_ROLE));
            return;
        }

        newUser.setId(UUID.randomUUID().toString());
        newUser.setUsername(newUsername);
        newUser.setPassword(newPassword);
        newUser.setPreferredLanguage(localization.getCurrentLanguage());

        if (newUser instanceof Manager manager) {
            manager.setType(ManagerType.DEPARTMENT);
        }

        userRepository.save(newUser);
        System.out.println(localization.format(LocalizationKey.USER_CREATED, newUsername));
    }

    private static User createUserByRole(String role) {
        if (role.equalsIgnoreCase("Student")) {
            return new Student();
        }
        if (role.equalsIgnoreCase("Teacher")) {
            return new Teacher();
        }
        if (role.equalsIgnoreCase("Manager")) {
            return new Manager();
        }
        if (role.equalsIgnoreCase("TechSupportSpecialist")) {
            return new TechSupportSpecialist();
        }
        return null;
    }

    private static Language resolveUserLanguage(User user) {
        if (user.getPreferredLanguage() == null) {
            return localization.getCurrentLanguage();
        }
        return user.getPreferredLanguage();
    }

    private static LinkedHashMap<String, String> buildMenu(User effectiveUser, boolean isResearcher) {
        LinkedHashMap<String, String> menu = new LinkedHashMap<>();
        if (effectiveUser instanceof Manager) {
            menu.put("1",  localization.get(LocalizationKey.ADMIN_MENU_ADD_USER));
            menu.put("2",  localization.get(LocalizationKey.MENU_APPROVE_REGISTRATION));
            menu.put("3",  localization.get(LocalizationKey.MENU_ASSIGN_COURSE));
            menu.put("4",  localization.get(LocalizationKey.MENU_CHANGE_ENROLLMENT));
            menu.put("5",  localization.get(LocalizationKey.MENU_CREATE_COURSE_OFFERING));
            menu.put("6",  localization.get(LocalizationKey.MENU_CREATE_SEMESTER));
            menu.put("7",  localization.get(LocalizationKey.MENU_CREATE_COURSE));
            menu.put("8",  localization.get(LocalizationKey.MENU_ADD_ROOM));
            menu.put("9",  localization.get(LocalizationKey.MENU_SEND_MESSAGE));
            menu.put("10", localization.get(LocalizationKey.MENU_VIEW_MESSAGES));
            menu.put("11", localization.get(LocalizationKey.MENU_PUBLISH_NEWS));
            menu.put("12", localization.get(LocalizationKey.MENU_SUBSCRIBE_JOURNAL));
            menu.put("13", localization.get(LocalizationKey.MENU_TOP_RESEARCHERS));
            menu.put("14", localization.get(LocalizationKey.MENU_GET_CITATION));
            menu.put("15", localization.get(LocalizationKey.MENU_SEND_TECH_REQUEST));
            menu.put("16", localization.get(isResearcher ? LocalizationKey.MENU_PUBLISH_PAPER : LocalizationKey.MENU_BECOME_RESEARCHER));
            if (isResearcher) menu.put("17", localization.get(LocalizationKey.MENU_MY_PAPERS));
            menu.put("18", localization.get(LocalizationKey.MENU_CREATE_JOURNAL));
        } else if (effectiveUser instanceof Student) {
            menu.put("1", localization.get(LocalizationKey.MENU_REGISTER_OFFERING));
            menu.put("2", localization.get(LocalizationKey.MENU_VIEW_SCHEDULE));
            menu.put("3", localization.get(LocalizationKey.MENU_VIEW_TRANSCRIPT));
            menu.put("4", localization.get(LocalizationKey.MENU_SUBSCRIBE_JOURNAL));
            menu.put("5", localization.get(LocalizationKey.MENU_TOP_RESEARCHERS));
            menu.put("6", localization.get(LocalizationKey.MENU_GET_CITATION));
            menu.put("7", localization.get(LocalizationKey.MENU_SEND_TECH_REQUEST));
            menu.put("8", localization.get(isResearcher ? LocalizationKey.MENU_PUBLISH_PAPER : LocalizationKey.MENU_BECOME_RESEARCHER));
            if (isResearcher) menu.put("9", localization.get(LocalizationKey.MENU_MY_PAPERS));
        } else if (effectiveUser instanceof Teacher) {
            menu.put("1",  localization.get(LocalizationKey.MENU_PUT_MARK));
            menu.put("2",  localization.get(LocalizationKey.MENU_SEND_COMPLAINT));
            menu.put("3",  localization.get(LocalizationKey.MENU_VIEW_MY_COURSES));
            menu.put("4",  localization.get(LocalizationKey.MENU_VIEW_MY_SCHEDULE));
            menu.put("5",  localization.get(LocalizationKey.MENU_SUBSCRIBE_JOURNAL));
            menu.put("6",  localization.get(LocalizationKey.MENU_TOP_RESEARCHERS));
            menu.put("7",  localization.get(LocalizationKey.MENU_GET_CITATION));
            menu.put("8",  localization.get(isResearcher ? LocalizationKey.MENU_PUBLISH_PAPER : LocalizationKey.MENU_BECOME_RESEARCHER));
            if (isResearcher) menu.put("9", localization.get(LocalizationKey.MENU_MY_PAPERS));
            menu.put("10", localization.get(LocalizationKey.MENU_SEND_MESSAGE));
            menu.put("11", localization.get(LocalizationKey.MENU_VIEW_MESSAGES));
            menu.put("12", localization.get(LocalizationKey.MENU_SEND_TECH_REQUEST));
        } else if (effectiveUser instanceof TechSupportSpecialist) {
            menu.put("1",  localization.get(LocalizationKey.MENU_VIEW_NEW_REQUESTS));
            menu.put("2",  localization.get(LocalizationKey.MENU_CHANGE_REQUEST_STATUS));
            menu.put("3",  localization.get(LocalizationKey.MENU_SUBSCRIBE_JOURNAL));
            menu.put("4",  localization.get(LocalizationKey.MENU_TOP_RESEARCHERS));
            menu.put("5",  localization.get(LocalizationKey.MENU_GET_CITATION));
            menu.put("6",  localization.get(isResearcher ? LocalizationKey.MENU_PUBLISH_PAPER : LocalizationKey.MENU_BECOME_RESEARCHER));
            if (isResearcher) menu.put("7", localization.get(LocalizationKey.MENU_MY_PAPERS));
            menu.put("9",  localization.get(LocalizationKey.MENU_SEND_MESSAGE));
            menu.put("10", localization.get(LocalizationKey.MENU_VIEW_MESSAGES));
            menu.put("11", localization.get(LocalizationKey.MENU_SEND_TECH_REQUEST));
        }
        menu.put("20", localization.get(LocalizationKey.MENU_CHANGE_LANGUAGE));
        return menu;
    }
}
