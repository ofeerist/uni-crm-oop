package unicrm.command;

import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.service.TechSupportService;
import unicrm.session.UserSession;

import java.util.Scanner;

public class SendTechRequestCommand {

    private final TechSupportService techSupportService;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public SendTechRequestCommand(
            TechSupportService techSupportService,
            UserSession userSession,
            Scanner scanner
    ) {
        this.techSupportService = techSupportService;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getEffectiveUser();
        if (currentUser == null) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        System.out.print(localization.get(LocalizationKey.ENTER_REQUEST_DESCRIPTION));
        String description = scanner.nextLine().trim();

        techSupportService.createRequest(currentUser, description);
        System.out.println(localization.get(LocalizationKey.REQUEST_SENT));
    }
}
