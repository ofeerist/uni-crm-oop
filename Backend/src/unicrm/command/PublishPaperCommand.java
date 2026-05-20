package unicrm.command;

import unicrm.domain.Teacher;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.service.ResearchService;
import unicrm.session.UserSession;

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
        if (!(currentUser instanceof Teacher)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        System.out.print(localization.get(LocalizationKey.ENTER_PAPER_TITLE));
        String title = scanner.nextLine().trim();

        System.out.print(localization.get(LocalizationKey.ENTER_PAPER_ABSTRACT));
        String paperAbstract = scanner.nextLine().trim();

        researchService.publishPaper(currentUser.getUsername(), title, paperAbstract);
        System.out.println(localization.format(LocalizationKey.PAPER_PUBLISHED, title));
    }
}
