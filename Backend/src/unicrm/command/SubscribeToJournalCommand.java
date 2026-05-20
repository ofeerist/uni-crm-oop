package unicrm.command;

import unicrm.domain.UniversityJournal;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.session.UserSession;

import java.util.Scanner;

public class SubscribeToJournalCommand {

    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public SubscribeToJournalCommand(UserSession userSession, Scanner scanner) {
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        System.out.print(localization.get(LocalizationKey.ENTER_JOURNAL_NAME));
        String journalName = scanner.nextLine().trim();

        boolean alreadySubscribed = currentUser.getJournalSubscriptions().stream()
                .anyMatch(j -> j.getName().equalsIgnoreCase(journalName));

        if (!alreadySubscribed) {
            currentUser.getJournalSubscriptions().add(new UniversityJournal(journalName));
        }

        System.out.println(localization.format(LocalizationKey.SUBSCRIBED_TO_JOURNAL, journalName));
    }
}
