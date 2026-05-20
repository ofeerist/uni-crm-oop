package unicrm.command;

import unicrm.domain.Student;
import unicrm.domain.Teacher;
import unicrm.domain.UrgencyLevel;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.UserRepository;
import unicrm.service.CommunicationService;
import unicrm.session.UserSession;

import java.util.List;
import java.util.Scanner;

public class SendComplaintCommand {

    private final CommunicationService communicationService;
    private final UserRepository userRepo;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public SendComplaintCommand(
            CommunicationService communicationService,
            UserRepository userRepo,
            UserSession userSession,
            Scanner scanner
    ) {
        this.communicationService = communicationService;
        this.userRepo = userRepo;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getEffectiveUser();
        if (!(currentUser instanceof Teacher teacher)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        List<User> studentUsers = userRepo.findByRole("Student");
        if (studentUsers.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_STUDENTS_FOUND));
            return;
        }

        System.out.println(localization.get(LocalizationKey.COMPLAINT_SELECT_STUDENT));
        for (int i = 0; i < studentUsers.size(); i++) {
            System.out.println((i + 1) + ". " + studentUsers.get(i).getUsername());
        }
        System.out.print(localization.get(LocalizationKey.ENTER_STUDENT_NUMBER));
        String studentInput = scanner.nextLine().trim();

        int studentIndex;
        try {
            studentIndex = Integer.parseInt(studentInput) - 1;
        } catch (NumberFormatException e) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        if (studentIndex < 0 || studentIndex >= studentUsers.size()) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        Student student = (Student) studentUsers.get(studentIndex);

        System.out.println(localization.get(LocalizationKey.COMPLAINT_SELECT_URGENCY));
        UrgencyLevel[] levels = UrgencyLevel.values();
        for (int i = 0; i < levels.length; i++) {
            System.out.println((i + 1) + ". " + levels[i]);
        }
        System.out.print(localization.get(LocalizationKey.MENU_CHOICE));
        String urgencyInput = scanner.nextLine().trim();

        int urgencyIndex;
        try {
            urgencyIndex = Integer.parseInt(urgencyInput) - 1;
        } catch (NumberFormatException e) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        if (urgencyIndex < 0 || urgencyIndex >= levels.length) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        UrgencyLevel urgency = levels[urgencyIndex];

        System.out.print(localization.get(LocalizationKey.COMPLAINT_ENTER_REASON));
        String reason = scanner.nextLine().trim();

        communicationService.sendComplaint(teacher, student, urgency, reason);
        System.out.println(localization.get(LocalizationKey.COMPLAINT_SENT));
    }
}
