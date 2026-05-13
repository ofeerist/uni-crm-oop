package unicrm;
import unicrm.domain.Language;
import unicrm.domain.Manager;
import unicrm.domain.ManagerType;
import unicrm.domain.Student;
import unicrm.domain.Teacher;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.CourseRepository;
import unicrm.repository.UserRepository;
import unicrm.service.AuthService;
import unicrm.session.UserSession;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
public class UniCRM {
    private static final LocalizationService localization = LocalizationService.getInstance();
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        CourseRepository courseRepository = new CourseRepository();
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
                    if (currentUser instanceof Manager) {
                        runAdminMenu(scanner, userRepository);
                    } else {
                        System.out.println(localization.format(LocalizationKey.LIMITED_UI_ROLE, localizedRoleName(currentUser)));
                        System.out.println(localization.get(LocalizationKey.PRESS_ENTER_TO_LOGOUT));
                        scanner.nextLine();
                        authService.logout();
                    }
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
    private static void runAdminMenu(Scanner scanner, UserRepository userRepository) {
        while (true) {
            System.out.println();
            System.out.println(localization.get(LocalizationKey.ADMIN_MENU_TITLE));
            System.out.println(localization.get(LocalizationKey.ADMIN_MENU_ADD_USER));
            System.out.println(localization.get(LocalizationKey.ADMIN_MENU_LOGOUT));
            System.out.print(localization.get(LocalizationKey.MENU_CHOICE));
            String choice = scanner.nextLine();
            if (choice.equals("2")) {
                UserSession.getInstance().clearSession();
                System.out.println(localization.get(LocalizationKey.SESSION_CLOSED));
                break;
            }
            if (choice.equals("1")) {
                System.out.print(localization.get(LocalizationKey.ENTER_ROLE));
                String role = scanner.nextLine();
                System.out.print(localization.get(LocalizationKey.ENTER_USERNAME));
                String newUsername = scanner.nextLine();
                System.out.print(localization.get(LocalizationKey.ENTER_PASSWORD));
                String newPassword = scanner.nextLine();
                User newUser = createUserByRole(role);
                if (newUser == null) {
                    System.out.println(localization.get(LocalizationKey.UNKNOWN_ROLE));
                    continue;
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
        }
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
        return null;
    }
    private static Language resolveUserLanguage(User user) {
        if (user.getPreferredLanguage() == null) {
            return localization.getCurrentLanguage();
        }
        return user.getPreferredLanguage();
    }
    private static String localizedRoleName(User user) {
        if (user instanceof Student) {
            return localization.get(LocalizationKey.ROLE_STUDENT);
        }
        if (user instanceof Teacher) {
            return localization.get(LocalizationKey.ROLE_TEACHER);
        }
        if (user instanceof Manager) {
            return localization.get(LocalizationKey.ROLE_MANAGER);
        }
        return user.getClass().getSimpleName();
    }
}
