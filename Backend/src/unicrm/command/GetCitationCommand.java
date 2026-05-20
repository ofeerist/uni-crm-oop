package unicrm.command;

import unicrm.domain.ResearchPaper;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.service.ResearchService;
import unicrm.session.UserSession;

import java.util.List;
import java.util.Scanner;

public class GetCitationCommand {

    private final ResearchService researchService;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public GetCitationCommand(ResearchService researchService, UserSession userSession, Scanner scanner) {
        this.researchService = researchService;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        if (userSession.getCurrentUser() == null) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        List<ResearchPaper> papers = researchService.getAllPapers();
        if (papers.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_PAPERS_FOUND));
            return;
        }

        System.out.println(localization.get(LocalizationKey.ALL_PAPERS_HEADER));
        for (int i = 0; i < papers.size(); i++) {
            ResearchPaper p = papers.get(i);
            String journal = p.getJournalName() != null ? " [" + p.getJournalName() + "]" : "";
            System.out.printf("%d. %s (%s)%s%n", i + 1, p.getTitle(), p.getAuthorUsername(), journal);
        }
        System.out.print(localization.get(LocalizationKey.SELECT_PAPER));
        String input = scanner.nextLine().trim();

        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }
        if (index < 0 || index >= papers.size()) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }

        System.out.println(researchService.generateCitation(papers.get(index)));
    }
}
