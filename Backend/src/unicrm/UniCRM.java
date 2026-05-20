package unicrm;

import unicrm.command.*;
import unicrm.domain.*;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.*;
import unicrm.service.*;
import unicrm.session.UserSession;

import java.util.List;
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
        SemesterService semesterService = new SemesterService(new AcademicSemesterRepository());
        AcademicService academicService = new AcademicService(userRepository, courseRepository);
        CommunicationService communicationService = new CommunicationService(
                new MessageRepository(), new ComplaintRepository(), userRepository);
        ResearchService researchService = new ResearchService(new ResearchPaperRepository());
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
                new CreateCourseOfferingCommand(courseOfferingService, semesterService, courseRepository, userRepository, userSession, scanner);
        CreateSemesterCommand createSemesterCmd =
                new CreateSemesterCommand(semesterService, userSession, scanner);
        PutMarkCommand putMarkCmd =
                new PutMarkCommand(academicService, userRepository, userSession);
        RegisterForCourseCommand regCourseCmd =
                new RegisterForCourseCommand(courseRegService, courseRepository, userSession);
        RegisterForOfferingCommand regOfferingCmd =
                new RegisterForOfferingCommand(enrollmentService, offeringRepository, userSession);
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
                new AddRoomCommand(new RoomRepository(), userSession, scanner);
        PublishNewsCommand publishNewsCmd =
                new PublishNewsCommand(newsService, userSession, scanner);
        SubscribeToJournalCommand subscribeJournalCmd =
                new SubscribeToJournalCommand(userSession, scanner);
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

        while (true) {
            User currentUser = userSession.getCurrentUser();
            if (currentUser == null) {
                break;
            }
            User effectiveUser = userSession.getEffectiveUser();
            boolean isResearcher = currentUser instanceof ResearcherDecorator;

            System.out.println();
            System.out.println(localization.get(LocalizationKey.ADMIN_MENU_TITLE));

            if (effectiveUser instanceof Manager) {
                System.out.println("1.  " + localization.get(LocalizationKey.ADMIN_MENU_ADD_USER));
                System.out.println("2.  " + localization.get(LocalizationKey.MENU_APPROVE_REGISTRATION));
                System.out.println("3.  " + localization.get(LocalizationKey.MENU_ASSIGN_COURSE));
                System.out.println("4.  " + localization.get(LocalizationKey.MENU_CHANGE_ENROLLMENT));
                System.out.println("5.  " + localization.get(LocalizationKey.MENU_CREATE_COURSE_OFFERING));
                System.out.println("6.  " + localization.get(LocalizationKey.MENU_CREATE_SEMESTER));
                System.out.println("7.  " + localization.get(LocalizationKey.MENU_CREATE_COURSE));
                System.out.println("8.  " + localization.get(LocalizationKey.MENU_ADD_ROOM));
                System.out.println("9.  " + localization.get(LocalizationKey.MENU_SEND_MESSAGE));
                System.out.println("10. " + localization.get(LocalizationKey.MENU_VIEW_MESSAGES));
                System.out.println("11. " + localization.get(LocalizationKey.MENU_PUBLISH_NEWS));
                System.out.println("12. " + localization.get(LocalizationKey.MENU_SUBSCRIBE_JOURNAL));
                System.out.println("13. " + localization.get(LocalizationKey.MENU_TOP_RESEARCHERS));
                System.out.println("14. " + localization.get(LocalizationKey.MENU_GET_CITATION));
                System.out.println("15. " + localization.get(LocalizationKey.MENU_SEND_TECH_REQUEST));
                if (isResearcher) {
                    System.out.println("16. " + localization.get(LocalizationKey.MENU_PUBLISH_PAPER));
                    System.out.println("17. " + localization.get(LocalizationKey.MENU_MY_PAPERS));
                } else {
                    System.out.println("16. " + localization.get(LocalizationKey.MENU_BECOME_RESEARCHER));
                }

            } else if (effectiveUser instanceof Student) {
                System.out.println("1. " + localization.get(LocalizationKey.MENU_REGISTER_COURSE));
                System.out.println("2. " + localization.get(LocalizationKey.MENU_REGISTER_OFFERING));
                System.out.println("3. " + localization.get(LocalizationKey.MENU_VIEW_TRANSCRIPT));
                System.out.println("4. " + localization.get(LocalizationKey.MENU_SUBSCRIBE_JOURNAL));
                System.out.println("5. " + localization.get(LocalizationKey.MENU_TOP_RESEARCHERS));
                System.out.println("6. " + localization.get(LocalizationKey.MENU_GET_CITATION));
                System.out.println("7. " + localization.get(LocalizationKey.MENU_SEND_TECH_REQUEST));
                if (isResearcher) {
                    System.out.println("8. " + localization.get(LocalizationKey.MENU_PUBLISH_PAPER));
                    System.out.println("9. " + localization.get(LocalizationKey.MENU_MY_PAPERS));
                } else {
                    System.out.println("8. " + localization.get(LocalizationKey.MENU_BECOME_RESEARCHER));
                }

            } else if (effectiveUser instanceof Teacher) {
                System.out.println("1. " + localization.get(LocalizationKey.MENU_PUT_MARK));
                System.out.println("2. " + localization.get(LocalizationKey.MENU_SEND_COMPLAINT));
                System.out.println("3. " + localization.get(LocalizationKey.MENU_SUBSCRIBE_JOURNAL));
                System.out.println("4. " + localization.get(LocalizationKey.MENU_TOP_RESEARCHERS));
                System.out.println("5. " + localization.get(LocalizationKey.MENU_GET_CITATION));
                System.out.println("9. " + localization.get(LocalizationKey.MENU_SEND_MESSAGE));
                System.out.println("10. " + localization.get(LocalizationKey.MENU_VIEW_MESSAGES));
                System.out.println("11. " + localization.get(LocalizationKey.MENU_SEND_TECH_REQUEST));
                if (isResearcher) {
                    System.out.println("6. " + localization.get(LocalizationKey.MENU_PUBLISH_PAPER));
                    System.out.println("7. " + localization.get(LocalizationKey.MENU_MY_PAPERS));
                } else {
                    System.out.println("6. " + localization.get(LocalizationKey.MENU_BECOME_RESEARCHER));
                }

            } else if (effectiveUser instanceof TechSupportSpecialist) {
                System.out.println("1. " + localization.get(LocalizationKey.MENU_VIEW_NEW_REQUESTS));
                System.out.println("2. " + localization.get(LocalizationKey.MENU_CHANGE_REQUEST_STATUS));
                System.out.println("3. " + localization.get(LocalizationKey.MENU_SUBSCRIBE_JOURNAL));
                System.out.println("4. " + localization.get(LocalizationKey.MENU_TOP_RESEARCHERS));
                System.out.println("5. " + localization.get(LocalizationKey.MENU_GET_CITATION));
                System.out.println("9. " + localization.get(LocalizationKey.MENU_SEND_MESSAGE));
                System.out.println("10. " + localization.get(LocalizationKey.MENU_VIEW_MESSAGES));
                System.out.println("11. " + localization.get(LocalizationKey.MENU_SEND_TECH_REQUEST));
                if (isResearcher) {
                    System.out.println("6. " + localization.get(LocalizationKey.MENU_PUBLISH_PAPER));
                    System.out.println("7. " + localization.get(LocalizationKey.MENU_MY_PAPERS));
                } else {
                    System.out.println("6. " + localization.get(LocalizationKey.MENU_BECOME_RESEARCHER));
                }
            }

            System.out.println("0. " + localization.get(LocalizationKey.ADMIN_MENU_LOGOUT));
            System.out.print(localization.get(LocalizationKey.MENU_CHOICE));

            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                userSession.clearSession();
                System.out.println(localization.get(LocalizationKey.SESSION_CLOSED));
                break;
            }

            if (effectiveUser instanceof Manager) {
                switch (choice) {
                    case "1"  -> addUser(scanner, userRepository);
                    case "2"  -> approveRegCmd.execute();
                    case "3"  -> assignCourseCmd.execute();
                    case "4"  -> changeEnrollCmd.execute();
                    case "5"  -> createOfferingCmd.execute();
                    case "6"  -> createSemesterCmd.execute();
                    case "7"  -> createCourseCmd.execute();
                    case "8"  -> addRoomCmd.execute();
                    case "9"  -> sendMsgCmd.execute();
                    case "10" -> viewMsgsCmd.execute();
                    case "11" -> publishNewsCmd.execute();
                    case "12" -> subscribeJournalCmd.execute();
                    case "13" -> viewTopResearcherCmd.execute();
                    case "14" -> getCitationCmd.execute();
                    case "15" -> sendTechRequestCmd.execute();
                    case "16" -> {
                        if (isResearcher) publishPaperCmd.execute();
                        else becomeResearcherCmd.execute();
                    }
                    case "17" -> { if (isResearcher) printMyPapersCmd.execute(); }
                }

            } else if (effectiveUser instanceof Student) {
                switch (choice) {
                    case "1" -> regCourseCmd.execute();
                    case "2" -> regOfferingCmd.execute();
                    case "3" -> viewTranscriptCmd.execute();
                    case "4" -> subscribeJournalCmd.execute();
                    case "5" -> viewTopResearcherCmd.execute();
                    case "6" -> getCitationCmd.execute();
                    case "7" -> sendTechRequestCmd.execute();
                    case "8" -> {
                        if (isResearcher) publishPaperCmd.execute();
                        else becomeResearcherCmd.execute();
                    }
                    case "9" -> { if (isResearcher) printMyPapersCmd.execute(); }
                }

            } else if (effectiveUser instanceof Teacher) {
                switch (choice) {
                    case "1"  -> putMarkCmd.execute();
                    case "2"  -> sendComplaintCmd.execute();
                    case "3"  -> subscribeJournalCmd.execute();
                    case "4"  -> viewTopResearcherCmd.execute();
                    case "5"  -> getCitationCmd.execute();
                    case "6"  -> {
                        if (isResearcher) publishPaperCmd.execute();
                        else becomeResearcherCmd.execute();
                    }
                    case "7"  -> { if (isResearcher) printMyPapersCmd.execute(); }
                    case "9"  -> sendMsgCmd.execute();
                    case "10" -> viewMsgsCmd.execute();
                    case "11" -> sendTechRequestCmd.execute();
                }

            } else if (effectiveUser instanceof TechSupportSpecialist) {
                switch (choice) {
                    case "1"  -> viewReqCmd.execute();
                    case "2"  -> changeReqStatCmd.execute();
                    case "3"  -> subscribeJournalCmd.execute();
                    case "4"  -> viewTopResearcherCmd.execute();
                    case "5"  -> getCitationCmd.execute();
                    case "6"  -> {
                        if (isResearcher) publishPaperCmd.execute();
                        else becomeResearcherCmd.execute();
                    }
                    case "7"  -> { if (isResearcher) printMyPapersCmd.execute(); }
                    case "9"  -> sendMsgCmd.execute();
                    case "10" -> viewMsgsCmd.execute();
                    case "11" -> sendTechRequestCmd.execute();
                }
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
}
