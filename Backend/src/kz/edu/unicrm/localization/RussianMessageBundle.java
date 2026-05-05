package kz.edu.unicrm.localization;
import kz.edu.unicrm.domain.Language;
import java.util.EnumMap;
import java.util.Map;
public class RussianMessageBundle implements MessageBundle {
    private final Map<LocalizationKey, String> messages = new EnumMap<>(LocalizationKey.class);
    public RussianMessageBundle() {
        messages.put(LocalizationKey.LANGUAGE_SELECT_TITLE, "Выберите язык интерфейса:");
        messages.put(LocalizationKey.LANGUAGE_SELECT_RU, "1. Русский");
        messages.put(LocalizationKey.LANGUAGE_SELECT_EN, "2. English");
        messages.put(LocalizationKey.LANGUAGE_SELECT_KZ, "3. Қазақ тілі");
        messages.put(LocalizationKey.LANGUAGE_SELECT_CHOICE, "Выбор: ");
        messages.put(LocalizationKey.LANGUAGE_NOT_SUPPORTED, "Этот язык пока не поддерживается. Будет использован русский язык.");
        messages.put(LocalizationKey.ADMIN_NOT_FOUND, "Администратор в системе не найден. Создание первой учетной записи администратора:");
        messages.put(LocalizationKey.ADMIN_USERNAME, "Имя пользователя: ");
        messages.put(LocalizationKey.ADMIN_PASSWORD, "Пароль: ");
        messages.put(LocalizationKey.ADMIN_CREATED, "Учетная запись администратора успешно создана.");
        messages.put(LocalizationKey.MAIN_MENU_TITLE, "Главное меню:");
        messages.put(LocalizationKey.MAIN_MENU_LOGIN, "1. Войти");
        messages.put(LocalizationKey.MAIN_MENU_EXIT, "2. Выход");
        messages.put(LocalizationKey.MENU_CHOICE, "Выбор: ");
        messages.put(LocalizationKey.LOGIN_SUCCESS, "Вход выполнен успешно. Добро пожаловать, %s.");
        messages.put(LocalizationKey.LOGIN_ERROR, "Ошибка входа: неверные учетные данные.");
        messages.put(LocalizationKey.LIMITED_UI_ROLE, "Вы вошли как %s. Консольный интерфейс для этой роли ограничен.");
        messages.put(LocalizationKey.PRESS_ENTER_TO_LOGOUT, "Нажмите Enter для выхода из учетной записи...");
        messages.put(LocalizationKey.ADMIN_MENU_TITLE, "Меню администратора:");
        messages.put(LocalizationKey.ADMIN_MENU_ADD_USER, "1. Добавить нового пользователя");
        messages.put(LocalizationKey.ADMIN_MENU_LOGOUT, "2. Выйти из учетной записи");
        messages.put(LocalizationKey.SESSION_CLOSED, "Сессия завершена.");
        messages.put(LocalizationKey.ENTER_ROLE, "Укажите роль (Student / Teacher / Manager): ");
        messages.put(LocalizationKey.ENTER_USERNAME, "Введите имя пользователя: ");
        messages.put(LocalizationKey.ENTER_PASSWORD, "Введите пароль: ");
        messages.put(LocalizationKey.UNKNOWN_ROLE, "Неизвестная роль.");
        messages.put(LocalizationKey.USER_CREATED, "Пользователь %s успешно создан и сохранен.");
        messages.put(LocalizationKey.ROLE_STUDENT, "Студент");
        messages.put(LocalizationKey.ROLE_TEACHER, "Преподаватель");
        messages.put(LocalizationKey.ROLE_MANAGER, "Администратор");
    }
    @Override
    public Language language() {
        return Language.RU;
    }
    @Override
    public String get(LocalizationKey key) {
        return messages.getOrDefault(key, key.name());
    }
}
