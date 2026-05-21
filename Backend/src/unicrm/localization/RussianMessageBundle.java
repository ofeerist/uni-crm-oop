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
        messages.put(LocalizationKey.ADMIN_MENU_ADD_USER, "Добавить нового пользователя");
        messages.put(LocalizationKey.ADMIN_MENU_LOGOUT, "Выйти из учетной записи");
        messages.put(LocalizationKey.SESSION_CLOSED, "Сессия завершена.");
        messages.put(LocalizationKey.ENTER_ROLE, "Укажите роль (Student / Teacher / Manager): ");
        messages.put(LocalizationKey.ENTER_USERNAME, "Введите имя пользователя: ");
        messages.put(LocalizationKey.ENTER_PASSWORD, "Введите пароль: ");
        messages.put(LocalizationKey.UNKNOWN_ROLE, "Неизвестная роль.");
        messages.put(LocalizationKey.USER_CREATED, "Пользователь %s успешно создан и сохранен.");
        messages.put(LocalizationKey.ROLE_STUDENT, "Студент");
        messages.put(LocalizationKey.ROLE_TEACHER, "Преподаватель");
        messages.put(LocalizationKey.ROLE_MANAGER, "Администратор");

        messages.put(LocalizationKey.ENTER_RECEIVER_ID, "Введите имя пользователя получателя: ");
        messages.put(LocalizationKey.ENTER_MESSAGE_CONTENT, "Введите текст сообщения: ");
        messages.put(LocalizationKey.MESSAGE_SENT_SUCCESS, "Сообщение успешно отправлено.");
        messages.put(LocalizationKey.RECEIVER_NOT_FOUND, "Получатель не найден.");
        messages.put(LocalizationKey.ACCESS_DENIED, "Доступ запрещён: недостаточно прав.");

        messages.put(LocalizationKey.ENTER_COURSE_CODE, "Введите код курса: ");
        messages.put(LocalizationKey.ENTER_COURSE_NAME, "Введите название курса: ");
        messages.put(LocalizationKey.ENTER_CREDITS, "Введите количество кредитов: ");
        messages.put(LocalizationKey.SELECT_CATEGORY, "Выберите категорию (MAJOR / MINOR / FREE_ELECTIVE): ");
        messages.put(LocalizationKey.COURSE_CREATED, "Курс '%s' успешно создан.");

        messages.put(LocalizationKey.ENTER_ROOM_NAME, "Введите название/номер аудитории: ");
        messages.put(LocalizationKey.ENTER_CAPACITY, "Введите вместимость аудитории: ");
        messages.put(LocalizationKey.SELECT_ROOM_TYPE, "Выберите тип аудитории (LECTURE / PRACTICE / LAB): ");
        messages.put(LocalizationKey.ROOM_ADDED, "Аудитория '%s' успешно добавлена.");

        messages.put(LocalizationKey.ENTER_NEWS_TITLE, "Введите заголовок новости: ");
        messages.put(LocalizationKey.ENTER_NEWS_TEXT, "Введите текст новости: ");
        messages.put(LocalizationKey.NEWS_PUBLISHED, "Новость успешно опубликована.");

        messages.put(LocalizationKey.ENTER_JOURNAL_NAME, "Введите название журнала: ");
        messages.put(LocalizationKey.SUBSCRIBED_TO_JOURNAL, "Вы подписались на журнал '%s'.");
        messages.put(LocalizationKey.ENTER_PAPER_TITLE, "Введите название статьи: ");
        messages.put(LocalizationKey.ENTER_PAPER_ABSTRACT, "Введите аннотацию: ");
        messages.put(LocalizationKey.PAPER_PUBLISHED, "Статья '%s' успешно опубликована.");
        messages.put(LocalizationKey.NO_PAPERS_FOUND, "Статьи не найдены.");
        messages.put(LocalizationKey.CITATION_NOT_FOUND, "Статья не найдена.");
        messages.put(LocalizationKey.TOP_RESEARCHERS_TITLE, "Топ исследователей по H-Index:");

        messages.put(LocalizationKey.MENU_SUBSCRIBE_JOURNAL, "Подписаться на журнал");
        messages.put(LocalizationKey.MENU_PUBLISH_PAPER, "Опубликовать статью");
        messages.put(LocalizationKey.MENU_MY_PAPERS, "Мои статьи");
        messages.put(LocalizationKey.MENU_GET_CITATION, "Получить цитату");
        messages.put(LocalizationKey.MENU_TOP_RESEARCHERS, "Топ исследователей");
        messages.put(LocalizationKey.MENU_CREATE_COURSE, "Создать курс");
        messages.put(LocalizationKey.MENU_ADD_ROOM, "Добавить аудиторию");
        messages.put(LocalizationKey.MENU_PUBLISH_NEWS, "Опубликовать новость");

        messages.put(LocalizationKey.MENU_APPROVE_REGISTRATION, "Одобрить заявку студента");
        messages.put(LocalizationKey.MENU_ASSIGN_COURSE, "Назначить курс преподавателю");
        messages.put(LocalizationKey.MENU_CHANGE_ENROLLMENT, "Изменить статус зачисления");
        messages.put(LocalizationKey.MENU_CHANGE_REQUEST_STATUS, "Изменить статус запроса");
        messages.put(LocalizationKey.MENU_CREATE_COURSE_OFFERING, "Создать курсовое предложение");
        messages.put(LocalizationKey.MENU_CREATE_SEMESTER, "Создать семестр");
        messages.put(LocalizationKey.MENU_PUT_MARK, "Выставить оценку");
        messages.put(LocalizationKey.MENU_REGISTER_COURSE, "Записаться на курс");
        messages.put(LocalizationKey.MENU_REGISTER_OFFERING, "Записаться на курсовое предложение");
        messages.put(LocalizationKey.MENU_SEND_COMPLAINT, "Отправить жалобу");
        messages.put(LocalizationKey.MENU_SEND_MESSAGE, "Отправить сообщение");
        messages.put(LocalizationKey.MENU_VIEW_NEW_REQUESTS, "Просмотреть новые запросы");
        messages.put(LocalizationKey.MENU_VIEW_TRANSCRIPT, "Просмотреть академическую справку");

        messages.put(LocalizationKey.NO_ENROLLMENTS_FOUND, "Записи на курсы не найдены.");
        messages.put(LocalizationKey.CHOOSE_STATUS, "Выберите статус:");
        messages.put(LocalizationKey.ENROLLMENT_APPROVED, "Запись одобрена.");
        messages.put(LocalizationKey.ENROLLMENT_REJECTED, "Запись отклонена.");

        messages.put(LocalizationKey.MENU_VIEW_MESSAGES, "Просмотреть сообщения");
        messages.put(LocalizationKey.NO_MESSAGES, "Сообщений нет.");
        messages.put(LocalizationKey.MESSAGES_SENT_HEADER, "--- Отправленные сообщения ---");
        messages.put(LocalizationKey.MESSAGES_RECEIVED_HEADER, "--- Полученные сообщения ---");
        messages.put(LocalizationKey.MESSAGE_FROM, "От: %s");
        messages.put(LocalizationKey.MESSAGE_TO, "Кому: %s");
        messages.put(LocalizationKey.MESSAGE_CONTENT_LABEL, "Текст: %s");
        messages.put(LocalizationKey.MESSAGE_TIME, "Время: %s");

        messages.put(LocalizationKey.MENU_BECOME_RESEARCHER, "Стать исследователем");
        messages.put(LocalizationKey.BECAME_RESEARCHER, "Вы теперь исследователь! Вам открыты команды публикации статей.");
        messages.put(LocalizationKey.ALREADY_RESEARCHER, "Вы уже являетесь исследователем.");

        messages.put(LocalizationKey.MENU_SEND_TECH_REQUEST, "Отправить технический запрос");
        messages.put(LocalizationKey.ENTER_REQUEST_DESCRIPTION, "Введите описание проблемы: ");
        messages.put(LocalizationKey.REQUEST_SENT, "Технический запрос успешно отправлен.");
        messages.put(LocalizationKey.NO_REQUESTS_FOUND, "Запросов не найдено.");

        messages.put(LocalizationKey.H_INDEX_LABEL, "H-индекс: %d");

        messages.put(LocalizationKey.COMPLAINT_SELECT_STUDENT, "Выберите студента:");
        messages.put(LocalizationKey.ENTER_STUDENT_NUMBER, "Введите номер студента: ");
        messages.put(LocalizationKey.NO_STUDENTS_FOUND, "Студенты не найдены.");
        messages.put(LocalizationKey.COMPLAINT_SELECT_URGENCY, "Выберите степень нарушения:");
        messages.put(LocalizationKey.COMPLAINT_ENTER_REASON, "Введите описание нарушения: ");
        messages.put(LocalizationKey.COMPLAINT_SENT, "Жалоба успешно отправлена.");
        messages.put(LocalizationKey.INVALID_CHOICE, "Неверный выбор.");

        messages.put(LocalizationKey.MENU_CHANGE_LANGUAGE, "Сменить язык интерфейса");

        messages.put(LocalizationKey.OFFERING_SELECT_COURSE, "Выберите курс:");
        messages.put(LocalizationKey.OFFERING_SELECT_TEACHER, "Выберите преподавателя:");
        messages.put(LocalizationKey.ENTER_OFFERING_CAPACITY, "Введите вместимость группы: ");
        messages.put(LocalizationKey.ENTER_SEMESTER_SEASON, "Введите сезон (FALL / SPRING / SUMMER): ");
        messages.put(LocalizationKey.ENTER_SEMESTER_YEAR, "Введите год: ");
        messages.put(LocalizationKey.SEMESTER_NOT_FOUND, "Семестр не найден. Сначала создайте семестр.");
        messages.put(LocalizationKey.NO_COURSES_FOUND, "Курсы не найдены.");
        messages.put(LocalizationKey.NO_TEACHERS_FOUND, "Преподаватели не найдены.");
        messages.put(LocalizationKey.NO_ROOMS_FOUND, "Аудитории не найдены. Добавьте аудитории перед созданием расписания.");
        messages.put(LocalizationKey.OFFERING_CREATED, "Курсовое предложение успешно создано.");
        messages.put(LocalizationKey.SCHEDULE_HEADER, "--- Сгенерированное расписание ---");
        messages.put(LocalizationKey.LESSON_FORMAT, "%s | %s | Аудитория: %s");

        messages.put(LocalizationKey.MENU_CREATE_JOURNAL, "Создать журнал");
        messages.put(LocalizationKey.JOURNAL_CREATED, "Журнал '%s' успешно создан.");
        messages.put(LocalizationKey.NO_JOURNALS_FOUND, "Журналы не найдены.");
        messages.put(LocalizationKey.JOURNAL_ALREADY_EXISTS, "Журнал с таким названием уже существует.");
        messages.put(LocalizationKey.PAPER_SELECT_JOURNAL, "Выберите журнал для публикации:");
        messages.put(LocalizationKey.PAPER_SELECT_CITATIONS, "Выберите цитируемые статьи (номера через запятую, или Enter чтобы пропустить):");
        messages.put(LocalizationKey.ALL_PAPERS_HEADER, "--- Все статьи ---");
        messages.put(LocalizationKey.SELECT_PAPER, "Выберите статью: ");
        messages.put(LocalizationKey.SELECT_JOURNAL, "Введите номер журнала: ");
        messages.put(LocalizationKey.JOURNAL_SUBSCRIBE_HEADER, "Выберите журнал для подписки:");

        messages.put(LocalizationKey.SELECT_OFFERING_HEADER, "Выберите курсовое предложение:");
        messages.put(LocalizationKey.NO_OFFERINGS_FOUND, "Курсовые предложения не найдены.");
        messages.put(LocalizationKey.ENROLLMENT_CREATED_PENDING, "Заявка на зачисление создана. Статус: ОЖИДАНИЕ.");
        messages.put(LocalizationKey.MENU_VIEW_SCHEDULE, "Просмотреть расписание");
        messages.put(LocalizationKey.NO_SCHEDULE_FOUND, "Расписание не найдено.");
        messages.put(LocalizationKey.OFFERING_SCHEDULE_HEADER, "--- %s | %s | %s ---");

        messages.put(LocalizationKey.MENU_VIEW_MY_COURSES, "Мои курсы");
        messages.put(LocalizationKey.MENU_VIEW_MY_SCHEDULE, "Моё расписание");
        messages.put(LocalizationKey.NO_COURSES_ASSIGNED, "У вас нет назначенных курсов.");
        messages.put(LocalizationKey.TEACHER_COURSE_ITEM, "%s [%s] — %s %s (вместимость: %d)");

        messages.put(LocalizationKey.ENTER_FIRST_ATTESTATION, "Введите оценку за 1-ю аттестацию: ");
        messages.put(LocalizationKey.ENTER_SECOND_ATTESTATION, "Введите оценку за 2-ю аттестацию: ");
        messages.put(LocalizationKey.ENTER_FINAL_EXAM, "Введите оценку за финальный экзамен: ");
        messages.put(LocalizationKey.MARK_SAVED, "Оценка успешно сохранена.");
        messages.put(LocalizationKey.NO_ENROLLED_STUDENTS, "Нет зачисленных студентов на данный курс.");
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
