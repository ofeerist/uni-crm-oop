package unicrm.localization;
import unicrm.domain.Language;
import java.util.EnumMap;
import java.util.Map;
public class EnglishMessageBundle implements MessageBundle {
    private final Map<LocalizationKey, String> messages = new EnumMap<>(LocalizationKey.class);
    public EnglishMessageBundle() {
        messages.put(LocalizationKey.LANGUAGE_SELECT_TITLE, "Select interface language:");
        messages.put(LocalizationKey.LANGUAGE_SELECT_RU, "1. Russian");
        messages.put(LocalizationKey.LANGUAGE_SELECT_EN, "2. English");
        messages.put(LocalizationKey.LANGUAGE_SELECT_KZ, "3. Kazakh");
        messages.put(LocalizationKey.LANGUAGE_SELECT_CHOICE, "Choice: ");
        messages.put(LocalizationKey.LANGUAGE_NOT_SUPPORTED, "This language is not supported yet. Russian will be used.");
        messages.put(LocalizationKey.ADMIN_NOT_FOUND, "No administrator was found in the system. Creating the first administrator account:");
        messages.put(LocalizationKey.ADMIN_USERNAME, "Username: ");
        messages.put(LocalizationKey.ADMIN_PASSWORD, "Password: ");
        messages.put(LocalizationKey.ADMIN_CREATED, "Administrator account has been created successfully.");
        messages.put(LocalizationKey.MAIN_MENU_TITLE, "Main menu:");
        messages.put(LocalizationKey.MAIN_MENU_LOGIN, "1. Log in");
        messages.put(LocalizationKey.MAIN_MENU_EXIT, "2. Exit");
        messages.put(LocalizationKey.MENU_CHOICE, "Choice: ");
        messages.put(LocalizationKey.LOGIN_SUCCESS, "Login successful. Welcome, %s.");
        messages.put(LocalizationKey.LOGIN_ERROR, "Login failed: invalid credentials.");
        messages.put(LocalizationKey.LIMITED_UI_ROLE, "You are logged in as %s. The console interface for this role is limited.");
        messages.put(LocalizationKey.PRESS_ENTER_TO_LOGOUT, "Press Enter to log out...");
        messages.put(LocalizationKey.ADMIN_MENU_TITLE, "Administrator menu:");
        messages.put(LocalizationKey.ADMIN_MENU_ADD_USER, "1. Add a new user");
        messages.put(LocalizationKey.ADMIN_MENU_LOGOUT, "2. Log out");
        messages.put(LocalizationKey.SESSION_CLOSED, "Session has been closed.");
        messages.put(LocalizationKey.ENTER_ROLE, "Enter role (Student / Teacher / Manager): ");
        messages.put(LocalizationKey.ENTER_USERNAME, "Enter username: ");
        messages.put(LocalizationKey.ENTER_PASSWORD, "Enter password: ");
        messages.put(LocalizationKey.UNKNOWN_ROLE, "Unknown role.");
        messages.put(LocalizationKey.USER_CREATED, "User %s has been created and saved successfully.");
        messages.put(LocalizationKey.ROLE_STUDENT, "Student");
        messages.put(LocalizationKey.ROLE_TEACHER, "Teacher");
        messages.put(LocalizationKey.ROLE_MANAGER, "Administrator");

        // SendMessageCommand
        messages.put(LocalizationKey.ENTER_RECEIVER_ID, "Enter recipient's username: ");
        messages.put(LocalizationKey.ENTER_MESSAGE_CONTENT, "Enter message text: ");
        messages.put(LocalizationKey.MESSAGE_SENT_SUCCESS, "Message sent successfully.");
        messages.put(LocalizationKey.RECEIVER_NOT_FOUND, "Recipient not found.");
        messages.put(LocalizationKey.ACCESS_DENIED, "Access denied: insufficient permissions.");

        // CreateCourseCommand
        messages.put(LocalizationKey.ENTER_COURSE_CODE, "Enter course code: ");
        messages.put(LocalizationKey.ENTER_COURSE_NAME, "Enter course name: ");
        messages.put(LocalizationKey.ENTER_CREDITS, "Enter number of credits: ");
        messages.put(LocalizationKey.SELECT_CATEGORY, "Select category (MAJOR / MINOR / FREE_ELECTIVE): ");
        messages.put(LocalizationKey.COURSE_CREATED, "Course '%s' created successfully.");

        // AddRoomCommand
        messages.put(LocalizationKey.ENTER_ROOM_NAME, "Enter room name/number: ");
        messages.put(LocalizationKey.ENTER_CAPACITY, "Enter room capacity: ");
        messages.put(LocalizationKey.SELECT_ROOM_TYPE, "Select room type (LECTURE / PRACTICE / LAB): ");
        messages.put(LocalizationKey.ROOM_ADDED, "Room '%s' added successfully.");

        // PublishNewsCommand
        messages.put(LocalizationKey.ENTER_NEWS_TITLE, "Enter news title: ");
        messages.put(LocalizationKey.ENTER_NEWS_TEXT, "Enter news text: ");
        messages.put(LocalizationKey.NEWS_PUBLISHED, "News published successfully.");

        // Research commands
        messages.put(LocalizationKey.ENTER_JOURNAL_NAME, "Enter journal name: ");
        messages.put(LocalizationKey.SUBSCRIBED_TO_JOURNAL, "You have subscribed to journal '%s'.");
        messages.put(LocalizationKey.ENTER_PAPER_TITLE, "Enter paper title: ");
        messages.put(LocalizationKey.ENTER_PAPER_ABSTRACT, "Enter abstract: ");
        messages.put(LocalizationKey.PAPER_PUBLISHED, "Paper '%s' published successfully.");
        messages.put(LocalizationKey.NO_PAPERS_FOUND, "No papers found.");
        messages.put(LocalizationKey.CITATION_NOT_FOUND, "Paper not found.");
        messages.put(LocalizationKey.TOP_RESEARCHERS_TITLE, "Top researchers by H-Index:");

        // Menu items
        messages.put(LocalizationKey.MENU_SUBSCRIBE_JOURNAL, "Subscribe to journal");
        messages.put(LocalizationKey.MENU_PUBLISH_PAPER, "Publish paper");
        messages.put(LocalizationKey.MENU_MY_PAPERS, "My papers");
        messages.put(LocalizationKey.MENU_GET_CITATION, "Get citation");
        messages.put(LocalizationKey.MENU_TOP_RESEARCHERS, "Top researchers");
        messages.put(LocalizationKey.MENU_CREATE_COURSE, "Create course");
        messages.put(LocalizationKey.MENU_ADD_ROOM, "Add room");
        messages.put(LocalizationKey.MENU_PUBLISH_NEWS, "Publish news");
    }
    @Override
    public Language language() {
        return Language.EN;
    }
    @Override
    public String get(LocalizationKey key) {
        return messages.getOrDefault(key, key.name());
    }
}
