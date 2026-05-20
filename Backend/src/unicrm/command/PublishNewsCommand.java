package unicrm.command;

import unicrm.domain.Manager;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.service.NewsService;
import unicrm.session.UserSession;

import java.util.Scanner;

public class PublishNewsCommand {

    private final NewsService newsService;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public PublishNewsCommand(NewsService newsService, UserSession userSession, Scanner scanner) {
        this.newsService = newsService;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Manager)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        System.out.print(localization.get(LocalizationKey.ENTER_NEWS_TITLE));
        String title = scanner.nextLine().trim();

        System.out.print(localization.get(LocalizationKey.ENTER_NEWS_TEXT));
        String text = scanner.nextLine().trim();

        newsService.publishNews(currentUser.getUsername(), title, text);
        System.out.println(localization.get(LocalizationKey.NEWS_PUBLISHED));
    }
}
