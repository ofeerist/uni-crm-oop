package unicrm.command;

import unicrm.domain.Manager;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.service.ResearchService;
import unicrm.session.UserSession;

import java.util.Scanner;

public class CreateJournalCommand {

    private final ResearchService researchService;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public CreateJournalCommand(ResearchService researchService, UserSession userSession, Scanner scanner) {
        this.researchService = researchService;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        if (!(userSession.getEffectiveUser() instanceof Manager)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        System.out.print(localization.get(LocalizationKey.ENTER_JOURNAL_NAME));
        String name = scanner.nextLine().trim();

        if (researchService.journalExists(name)) {
            System.out.println(localization.get(LocalizationKey.JOURNAL_ALREADY_EXISTS));
            return;
        }

        researchService.createJournal(name);
        System.out.println(localization.format(LocalizationKey.JOURNAL_CREATED, name));
    }
}
