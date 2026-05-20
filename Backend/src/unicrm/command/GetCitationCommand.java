package unicrm.command;

import unicrm.domain.ResearchPaper;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.service.ResearchService;
import unicrm.session.UserSession;

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

        System.out.print(localization.get(LocalizationKey.ENTER_PAPER_TITLE));
        String title = scanner.nextLine().trim();

        ResearchPaper paper = researchService.findPaperByTitle(title);
        if (paper == null) {
            System.out.println(localization.get(LocalizationKey.CITATION_NOT_FOUND));
            return;
        }

        System.out.println(researchService.generateCitation(paper));
    }
}
