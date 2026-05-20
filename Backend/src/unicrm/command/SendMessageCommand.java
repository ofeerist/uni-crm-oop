package unicrm.command;

import unicrm.domain.Employee;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.UserRepository;
import unicrm.service.CommunicationService;
import unicrm.session.UserSession;

import java.util.Scanner;

public class SendMessageCommand {

    private final CommunicationService communicationService;
    private final UserRepository userRepo;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public SendMessageCommand(
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
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Employee sender)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        System.out.print(localization.get(LocalizationKey.ENTER_RECEIVER_ID));
        String receiverUsername = scanner.nextLine().trim();

        System.out.print(localization.get(LocalizationKey.ENTER_MESSAGE_CONTENT));
        String content = scanner.nextLine();

        User found = userRepo.findAll().stream()
                .filter(u -> u.getUsername().equals(receiverUsername))
                .findFirst()
                .orElse(null);

        if (found == null || !(found instanceof Employee receiver)) {
            System.out.println(localization.get(LocalizationKey.RECEIVER_NOT_FOUND));
            return;
        }

        if (receiver.equals(sender)) {
            System.out.println(localization.get(LocalizationKey.RECEIVER_NOT_FOUND));
            return;
        }

        communicationService.sendMessage(sender, receiver, content);
        System.out.println(localization.get(LocalizationKey.MESSAGE_SENT_SUCCESS));
    }
}
