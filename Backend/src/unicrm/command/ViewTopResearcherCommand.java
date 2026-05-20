package unicrm.command;

import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.service.ResearchService;
import unicrm.session.UserSession;

import java.util.List;
import java.util.Map;

public class ViewTopResearcherCommand {

    private static final int TOP_LIMIT = 10;

    private final ResearchService researchService;
    private final UserSession userSession;
    private final LocalizationService localization = LocalizationService.getInstance();

    public ViewTopResearcherCommand(ResearchService researchService, UserSession userSession) {
        this.researchService = researchService;
        this.userSession = userSession;
    }

    public void execute() {
        if (userSession.getCurrentUser() == null) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        List<Map.Entry<String, Integer>> top = researchService.getTopResearchers(TOP_LIMIT);

        if (top.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_PAPERS_FOUND));
            return;
        }

        System.out.println(localization.get(LocalizationKey.TOP_RESEARCHERS_TITLE));
        for (int i = 0; i < top.size(); i++) {
            Map.Entry<String, Integer> entry = top.get(i);
            System.out.printf("%d. %-30s H-Index: %d%n", i + 1, entry.getKey(), entry.getValue());
        }
    }
}
