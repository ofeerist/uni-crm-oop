package unicrm.command;

import unicrm.domain.ResearchPaper;
import unicrm.domain.ResearcherDecorator;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.service.ResearchService;
import unicrm.session.UserSession;

import java.util.List;

public class PrintMyPapersCommand {

    private final ResearchService researchService;
    private final UserSession userSession;
    private final LocalizationService localization = LocalizationService.getInstance();

    public PrintMyPapersCommand(ResearchService researchService, UserSession userSession) {
        this.researchService = researchService;
        this.userSession = userSession;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof ResearcherDecorator)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        List<ResearchPaper> papers = researchService.getPapersForUser(currentUser.getUsername());
        int hIndex = researchService.calculateHIndex(currentUser.getUsername());
        System.out.println(localization.format(LocalizationKey.H_INDEX_LABEL, hIndex));

        if (papers.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_PAPERS_FOUND));
            return;
        }

        for (int i = 0; i < papers.size(); i++) {
            ResearchPaper p = papers.get(i);
            String journal = p.getJournalName() != null ? " [" + p.getJournalName() + "]" : "";
            System.out.printf("%d. %s%s [citations: %d]%n", i + 1, p.getTitle(), journal, p.getCitations());
            System.out.println("   " + p.getPaperAbstract());
        }
    }
}
