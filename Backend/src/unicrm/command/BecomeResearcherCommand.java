package unicrm.command;

import unicrm.domain.ResearcherDecorator;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.UserRepository;
import unicrm.service.ResearchService;
import unicrm.session.UserSession;

public class BecomeResearcherCommand {

    private final ResearchService researchService;
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final LocalizationService localization = LocalizationService.getInstance();

    public BecomeResearcherCommand(
            ResearchService researchService,
            UserRepository userRepository,
            UserSession userSession
    ) {
        this.researchService = researchService;
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }
        if (currentUser instanceof ResearcherDecorator) {
            System.out.println(localization.get(LocalizationKey.ALREADY_RESEARCHER));
            return;
        }

        ResearcherDecorator decorator = researchService.becomeResearcher(currentUser, userRepository);
        userSession.setCurrentUser(decorator);
        System.out.println(localization.get(LocalizationKey.BECAME_RESEARCHER));
    }
}
