package unicrm.command;
import unicrm.domain.ResearchProject;
import unicrm.domain.User;
import unicrm.repository.ResearchProjectRepository;
import unicrm.session.UserSession;
import unicrm.exception.NotResearcherException;
import java.util.List;
import java.util.Scanner;


public class JoinResearchProjectCommand {

    private final ResearchProjectRepository researchProjectRepository;
    private final UserSession userSession;
    private final Scanner scanner;

    public JoinResearchProjectCommand(
            ResearchProjectRepository researchProjectRepository,
            UserSession userSession,
            Scanner scanner
    ) {
        this.researchProjectRepository = researchProjectRepository;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            System.out.println("You must log in first.");
            return;
        }
        try {
            if (!currentUser.isResearcher()) {
                throw new NotResearcherException("Only researchers can join research projects.");
            }
        } catch (NotResearcherException e) {
            System.out.println(e.getMessage());
            return;
        }
        List<ResearchProject> projects = researchProjectRepository.findAll();
        if (projects.isEmpty()) {
            System.out.println("No research projects available.");
            return;
        }

        System.out.println("Select research project:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i).getTopic());
        }
        System.out.print("Choice: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }
        if (choice < 1 || choice > projects.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        ResearchProject selectedProject = projects.get(choice - 1);
        selectedProject.addParticipant(currentUser.getId());
        researchProjectRepository.saveAll(projects);
        System.out.println("You joined the research project successfully.");
    }
}
