package unicrm.command;

import unicrm.domain.ResearchPaper;
import unicrm.domain.ResearcherDecorator;
import unicrm.domain.UniversityJournal;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.service.ResearchService;
import unicrm.session.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PublishPaperCommand {

    private final ResearchService researchService;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public PublishPaperCommand(ResearchService researchService, UserSession userSession, Scanner scanner) {
        this.researchService = researchService;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof ResearcherDecorator)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        // Select journal
        List<UniversityJournal> journals = researchService.getAllJournals();
        if (journals.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_JOURNALS_FOUND));
            return;
        }

        System.out.println(localization.get(LocalizationKey.PAPER_SELECT_JOURNAL));
        for (int i = 0; i < journals.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, journals.get(i).getName());
        }
        System.out.print(localization.get(LocalizationKey.SELECT_JOURNAL));
        String journalInput = scanner.nextLine().trim();
        int journalIndex;
        try {
            journalIndex = Integer.parseInt(journalInput) - 1;
        } catch (NumberFormatException e) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        if (journalIndex < 0 || journalIndex >= journals.size()) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        String journalName = journals.get(journalIndex).getName();

        // Enter title and abstract
        System.out.print(localization.get(LocalizationKey.ENTER_PAPER_TITLE));
        String title = scanner.nextLine().trim();

        System.out.print(localization.get(LocalizationKey.ENTER_PAPER_ABSTRACT));
        String paperAbstract = scanner.nextLine().trim();

        // Optionally select citations
        List<ResearchPaper> allPapers = researchService.getAllPapers();
        List<String> citedIds = new ArrayList<>();
        if (!allPapers.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.PAPER_SELECT_CITATIONS));
            System.out.println(localization.get(LocalizationKey.ALL_PAPERS_HEADER));
            for (int i = 0; i < allPapers.size(); i++) {
                ResearchPaper p = allPapers.get(i);
                System.out.printf("%d. %s (%s)%n", i + 1, p.getTitle(), p.getAuthorUsername());
            }
            String citationInput = scanner.nextLine().trim();
            if (!citationInput.isEmpty()) {
                for (String part : citationInput.split(",")) {
                    try {
                        int idx = Integer.parseInt(part.trim()) - 1;
                        if (idx >= 0 && idx < allPapers.size()) {
                            citedIds.add(allPapers.get(idx).getId());
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        researchService.publishPaper(currentUser.getUsername(), title, paperAbstract, journalName, citedIds);
        System.out.println(localization.format(LocalizationKey.PAPER_PUBLISHED, title));
    }
}
