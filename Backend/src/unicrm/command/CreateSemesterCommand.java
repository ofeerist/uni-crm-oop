package unicrm.command;
import unicrm.domain.Manager;
import unicrm.domain.Season;
import unicrm.domain.User;
import unicrm.service.SemesterService;
import unicrm.session.UserSession;
import java.util.Scanner;

public class CreateSemesterCommand {

    private final SemesterService semesterService;
    private final UserSession userSession;
    private final Scanner scanner;

    public CreateSemesterCommand(
            SemesterService semesterService,
            UserSession userSession,
            Scanner scanner
    ) {
        this.semesterService = semesterService;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();

        if (!(currentUser instanceof Manager)) {
            return;
        }

        System.out.print("Enter season (FALL, SPRING, SUMMER): ");
        String seasonInput = scanner.nextLine();

        System.out.print("Enter year: ");
        String yearInput = scanner.nextLine();

        try {
            Season season = Season.valueOf(seasonInput.toUpperCase());
            int year = Integer.parseInt(yearInput);
            semesterService.createSemester(season, year);
            System.out.println("Semester " + season + " " + year + " created.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid season or year.");
        }
    }
}