package kz.edu.unicrm.localization;
import kz.edu.unicrm.domain.Language;
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
