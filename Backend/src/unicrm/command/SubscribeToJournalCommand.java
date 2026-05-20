package unicrm.command;

import unicrm.domain.UniversityJournal;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.JournalRepository;
import unicrm.repository.UserRepository;
import unicrm.session.UserSession;

import java.util.List;
import java.util.Scanner;

public class SubscribeToJournalCommand {

    private final JournalRepository journalRepository;
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public SubscribeToJournalCommand(JournalRepository journalRepository, UserRepository userRepository,
                                     UserSession userSession, Scanner scanner) {
        this.journalRepository = journalRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        List<UniversityJournal> journals = journalRepository.findAll();
        if (journals.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_JOURNALS_FOUND));
            return;
        }

        System.out.println(localization.get(LocalizationKey.JOURNAL_SUBSCRIBE_HEADER));
        for (int i = 0; i < journals.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, journals.get(i).getName());
        }
        System.out.print(localization.get(LocalizationKey.SELECT_JOURNAL));
        String input = scanner.nextLine().trim();

        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        if (index < 0 || index >= journals.size()) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }

        UniversityJournal journal = journals.get(index);
        boolean alreadySubscribed = currentUser.getJournalSubscriptions().stream()
                .anyMatch(j -> j.getId().equals(journal.getId()));

        if (!alreadySubscribed) {
            currentUser.getJournalSubscriptions().add(journal);
            userRepository.save(currentUser);
        }

        System.out.println(localization.format(LocalizationKey.SUBSCRIBED_TO_JOURNAL, journal.getName()));
    }
}
