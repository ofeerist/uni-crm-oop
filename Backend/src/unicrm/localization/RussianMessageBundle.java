package unicrm.localization;
import unicrm.domain.Language;
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

        // SendMessageCommand
        messages.put(LocalizationKey.ENTER_RECEIVER_ID, "Введите имя пользователя получателя: ");
        messages.put(LocalizationKey.ENTER_MESSAGE_CONTENT, "Введите текст сообщения: ");
        messages.put(LocalizationKey.MESSAGE_SENT_SUCCESS, "Сообщение успешно отправлено.");
        messages.put(LocalizationKey.RECEIVER_NOT_FOUND, "Получатель не найден.");
        messages.put(LocalizationKey.ACCESS_DENIED, "Доступ запрещён: недостаточно прав.");

        // CreateCourseCommand
        messages.put(LocalizationKey.ENTER_COURSE_CODE, "Введите код курса: ");
        messages.put(LocalizationKey.ENTER_COURSE_NAME, "Введите название курса: ");
        messages.put(LocalizationKey.ENTER_CREDITS, "Введите количество кредитов: ");
        messages.put(LocalizationKey.SELECT_CATEGORY, "Выберите категорию (MAJOR / MINOR / FREE_ELECTIVE): ");
        messages.put(LocalizationKey.COURSE_CREATED, "Курс '%s' успешно создан.");

        // AddRoomCommand
        messages.put(LocalizationKey.ENTER_ROOM_NAME, "Введите название/номер аудитории: ");
        messages.put(LocalizationKey.ENTER_CAPACITY, "Введите вместимость аудитории: ");
        messages.put(LocalizationKey.SELECT_ROOM_TYPE, "Выберите тип аудитории (LECTURE / PRACTICE / LAB): ");
        messages.put(LocalizationKey.ROOM_ADDED, "Аудитория '%s' успешно добавлена.");

        // PublishNewsCommand
        messages.put(LocalizationKey.ENTER_NEWS_TITLE, "Введите заголовок новости: ");
        messages.put(LocalizationKey.ENTER_NEWS_TEXT, "Введите текст новости: ");
        messages.put(LocalizationKey.NEWS_PUBLISHED, "Новость успешно опубликована.");

        // Research commands
        messages.put(LocalizationKey.ENTER_JOURNAL_NAME, "Введите название журнала: ");
        messages.put(LocalizationKey.SUBSCRIBED_TO_JOURNAL, "Вы подписались на журнал '%s'.");
        messages.put(LocalizationKey.ENTER_PAPER_TITLE, "Введите название статьи: ");
        messages.put(LocalizationKey.ENTER_PAPER_ABSTRACT, "Введите аннотацию: ");
        messages.put(LocalizationKey.PAPER_PUBLISHED, "Статья '%s' успешно опубликована.");
        messages.put(LocalizationKey.NO_PAPERS_FOUND, "Статьи не найдены.");
        messages.put(LocalizationKey.CITATION_NOT_FOUND, "Статья не найдена.");
        messages.put(LocalizationKey.TOP_RESEARCHERS_TITLE, "Топ исследователей по H-Index:");

        // Menu items
        messages.put(LocalizationKey.MENU_SUBSCRIBE_JOURNAL, "Подписаться на журнал");
        messages.put(LocalizationKey.MENU_PUBLISH_PAPER, "Опубликовать статью");
        messages.put(LocalizationKey.MENU_MY_PAPERS, "Мои статьи");
        messages.put(LocalizationKey.MENU_GET_CITATION, "Получить цитату");
        messages.put(LocalizationKey.MENU_TOP_RESEARCHERS, "Топ исследователей");
        messages.put(LocalizationKey.MENU_CREATE_COURSE, "Создать курс");
        messages.put(LocalizationKey.MENU_ADD_ROOM, "Добавить аудиторию");
        messages.put(LocalizationKey.MENU_PUBLISH_NEWS, "Опубликовать новость");
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
