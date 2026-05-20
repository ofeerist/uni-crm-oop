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
        messages.put(LocalizationKey.ADMIN_MENU_ADD_USER, "Add a new user");
        messages.put(LocalizationKey.ADMIN_MENU_LOGOUT, "Log out");
        messages.put(LocalizationKey.SESSION_CLOSED, "Session has been closed.");
        messages.put(LocalizationKey.ENTER_ROLE, "Enter role (Student / Teacher / Manager): ");
        messages.put(LocalizationKey.ENTER_USERNAME, "Enter username: ");
        messages.put(LocalizationKey.ENTER_PASSWORD, "Enter password: ");
        messages.put(LocalizationKey.UNKNOWN_ROLE, "Unknown role.");
        messages.put(LocalizationKey.USER_CREATED, "User %s has been created and saved successfully.");
        messages.put(LocalizationKey.ROLE_STUDENT, "Student");
        messages.put(LocalizationKey.ROLE_TEACHER, "Teacher");
        messages.put(LocalizationKey.ROLE_MANAGER, "Administrator");

        messages.put(LocalizationKey.ENTER_RECEIVER_ID, "Enter recipient's username: ");
        messages.put(LocalizationKey.ENTER_MESSAGE_CONTENT, "Enter message text: ");
        messages.put(LocalizationKey.MESSAGE_SENT_SUCCESS, "Message sent successfully.");
        messages.put(LocalizationKey.RECEIVER_NOT_FOUND, "Recipient not found.");
        messages.put(LocalizationKey.ACCESS_DENIED, "Access denied: insufficient permissions.");

        messages.put(LocalizationKey.ENTER_COURSE_CODE, "Enter course code: ");
        messages.put(LocalizationKey.ENTER_COURSE_NAME, "Enter course name: ");
        messages.put(LocalizationKey.ENTER_CREDITS, "Enter number of credits: ");
        messages.put(LocalizationKey.SELECT_CATEGORY, "Select category (MAJOR / MINOR / FREE_ELECTIVE): ");
        messages.put(LocalizationKey.COURSE_CREATED, "Course '%s' created successfully.");

        messages.put(LocalizationKey.ENTER_ROOM_NAME, "Enter room name/number: ");
        messages.put(LocalizationKey.ENTER_CAPACITY, "Enter room capacity: ");
        messages.put(LocalizationKey.SELECT_ROOM_TYPE, "Select room type (LECTURE / PRACTICE / LAB): ");
        messages.put(LocalizationKey.ROOM_ADDED, "Room '%s' added successfully.");

        messages.put(LocalizationKey.ENTER_NEWS_TITLE, "Enter news title: ");
        messages.put(LocalizationKey.ENTER_NEWS_TEXT, "Enter news text: ");
        messages.put(LocalizationKey.NEWS_PUBLISHED, "News published successfully.");

        messages.put(LocalizationKey.ENTER_JOURNAL_NAME, "Enter journal name: ");
        messages.put(LocalizationKey.SUBSCRIBED_TO_JOURNAL, "You have subscribed to journal '%s'.");
        messages.put(LocalizationKey.ENTER_PAPER_TITLE, "Enter paper title: ");
        messages.put(LocalizationKey.ENTER_PAPER_ABSTRACT, "Enter abstract: ");
        messages.put(LocalizationKey.PAPER_PUBLISHED, "Paper '%s' published successfully.");
        messages.put(LocalizationKey.NO_PAPERS_FOUND, "No papers found.");
        messages.put(LocalizationKey.CITATION_NOT_FOUND, "Paper not found.");
        messages.put(LocalizationKey.TOP_RESEARCHERS_TITLE, "Top researchers by H-Index:");

        messages.put(LocalizationKey.MENU_SUBSCRIBE_JOURNAL, "Subscribe to journal");
        messages.put(LocalizationKey.MENU_PUBLISH_PAPER, "Publish paper");
        messages.put(LocalizationKey.MENU_MY_PAPERS, "My papers");
        messages.put(LocalizationKey.MENU_GET_CITATION, "Get citation");
        messages.put(LocalizationKey.MENU_TOP_RESEARCHERS, "Top researchers");
        messages.put(LocalizationKey.MENU_CREATE_COURSE, "Create course");
        messages.put(LocalizationKey.MENU_ADD_ROOM, "Add room");
        messages.put(LocalizationKey.MENU_PUBLISH_NEWS, "Publish news");

        messages.put(LocalizationKey.MENU_APPROVE_REGISTRATION, "Approve student registration");
        messages.put(LocalizationKey.MENU_ASSIGN_COURSE, "Assign course to teacher");
        messages.put(LocalizationKey.MENU_CHANGE_ENROLLMENT, "Change enrollment status");
        messages.put(LocalizationKey.MENU_CHANGE_REQUEST_STATUS, "Change request status");
        messages.put(LocalizationKey.MENU_CREATE_COURSE_OFFERING, "Create course offering");
        messages.put(LocalizationKey.MENU_CREATE_SEMESTER, "Create semester");
        messages.put(LocalizationKey.MENU_PUT_MARK, "Put mark");
        messages.put(LocalizationKey.MENU_REGISTER_COURSE, "Register for course");
        messages.put(LocalizationKey.MENU_REGISTER_OFFERING, "Register for course offering");
        messages.put(LocalizationKey.MENU_SEND_COMPLAINT, "Send complaint");
        messages.put(LocalizationKey.MENU_SEND_MESSAGE, "Send message");
        messages.put(LocalizationKey.MENU_VIEW_NEW_REQUESTS, "View new requests");
        messages.put(LocalizationKey.MENU_VIEW_TRANSCRIPT, "View transcript");

        messages.put(LocalizationKey.NO_ENROLLMENTS_FOUND, "No enrollments found.");
        messages.put(LocalizationKey.CHOOSE_STATUS, "Choose status:");
        messages.put(LocalizationKey.ENROLLMENT_APPROVED, "Enrollment approved.");
        messages.put(LocalizationKey.ENROLLMENT_REJECTED, "Enrollment rejected.");

        messages.put(LocalizationKey.MENU_VIEW_MESSAGES, "View messages");
        messages.put(LocalizationKey.NO_MESSAGES, "No messages.");
        messages.put(LocalizationKey.MESSAGES_SENT_HEADER, "--- Sent messages ---");
        messages.put(LocalizationKey.MESSAGES_RECEIVED_HEADER, "--- Received messages ---");
        messages.put(LocalizationKey.MESSAGE_FROM, "From: %s");
        messages.put(LocalizationKey.MESSAGE_TO, "To: %s");
        messages.put(LocalizationKey.MESSAGE_CONTENT_LABEL, "Content: %s");
        messages.put(LocalizationKey.MESSAGE_TIME, "Time: %s");

        messages.put(LocalizationKey.MENU_BECOME_RESEARCHER, "Become researcher");
        messages.put(LocalizationKey.BECAME_RESEARCHER, "You are now a researcher! Paper publishing commands are now available.");
        messages.put(LocalizationKey.ALREADY_RESEARCHER, "You are already a researcher.");

        messages.put(LocalizationKey.MENU_SEND_TECH_REQUEST, "Send tech request");
        messages.put(LocalizationKey.ENTER_REQUEST_DESCRIPTION, "Enter problem description: ");
        messages.put(LocalizationKey.REQUEST_SENT, "Tech request sent successfully.");
        messages.put(LocalizationKey.NO_REQUESTS_FOUND, "No requests found.");

        messages.put(LocalizationKey.H_INDEX_LABEL, "H-Index: %d");

        messages.put(LocalizationKey.COMPLAINT_SELECT_STUDENT, "Select a student:");
        messages.put(LocalizationKey.ENTER_STUDENT_NUMBER, "Enter student number: ");
        messages.put(LocalizationKey.NO_STUDENTS_FOUND, "No students found.");
        messages.put(LocalizationKey.COMPLAINT_SELECT_URGENCY, "Select violation severity:");
        messages.put(LocalizationKey.COMPLAINT_ENTER_REASON, "Enter violation description: ");
        messages.put(LocalizationKey.COMPLAINT_SENT, "Complaint sent successfully.");
        messages.put(LocalizationKey.INVALID_CHOICE, "Invalid choice.");

        messages.put(LocalizationKey.MENU_CHANGE_LANGUAGE, "Change interface language");

        messages.put(LocalizationKey.OFFERING_SELECT_COURSE, "Select a course:");
        messages.put(LocalizationKey.OFFERING_SELECT_TEACHER, "Select a teacher:");
        messages.put(LocalizationKey.ENTER_OFFERING_CAPACITY, "Enter group capacity: ");
        messages.put(LocalizationKey.ENTER_SEMESTER_SEASON, "Enter season (FALL / SPRING / SUMMER): ");
        messages.put(LocalizationKey.ENTER_SEMESTER_YEAR, "Enter year: ");
        messages.put(LocalizationKey.SEMESTER_NOT_FOUND, "Semester not found. Create a semester first.");
        messages.put(LocalizationKey.NO_COURSES_FOUND, "No courses found.");
        messages.put(LocalizationKey.NO_TEACHERS_FOUND, "No teachers found.");
        messages.put(LocalizationKey.NO_ROOMS_FOUND, "No rooms found. Add rooms before creating a schedule.");
        messages.put(LocalizationKey.OFFERING_CREATED, "Course offering created successfully.");
        messages.put(LocalizationKey.SCHEDULE_HEADER, "--- Generated schedule ---");
        messages.put(LocalizationKey.LESSON_FORMAT, "%s | %s | Room: %s");
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
