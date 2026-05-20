package unicrm.command;

import unicrm.domain.Language;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.UserRepository;
import unicrm.session.UserSession;

import java.util.Scanner;

public class ChangeLanguageCommand {

    private final UserRepository userRepo;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public ChangeLanguageCommand(UserRepository userRepo, UserSession userSession, Scanner scanner) {
        this.userRepo = userRepo;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        System.out.println(localization.get(LocalizationKey.LANGUAGE_SELECT_TITLE));
        System.out.println(localization.get(LocalizationKey.LANGUAGE_SELECT_RU));
        System.out.println(localization.get(LocalizationKey.LANGUAGE_SELECT_EN));
        System.out.println(localization.get(LocalizationKey.LANGUAGE_SELECT_KZ));
        System.out.print(localization.get(LocalizationKey.LANGUAGE_SELECT_CHOICE));

        String choice = scanner.nextLine().trim();
        Language language = switch (choice) {
            case "1" -> Language.RU;
            case "2" -> Language.EN;
            case "3" -> Language.KZ;
            default  -> null;
        };

        if (language == null) {
            System.out.println(localization.get(LocalizationKey.INVALID_CHOICE));
            return;
        }

        if (language == Language.KZ) {
            System.out.println(localization.get(LocalizationKey.LANGUAGE_NOT_SUPPORTED));
        }

        localization.setLanguage(language);

        User currentUser = userSession.getCurrentUser();
        currentUser.setPreferredLanguage(language);
        userRepo.save(currentUser);
    }
}
