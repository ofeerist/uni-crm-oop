package unicrm.command;
import unicrm.domain.ResearchProject;
import unicrm.repository.ResearchProjectRepository;
import java.util.Scanner;

public class CreateResearchProjectCommand {

    private final ResearchProjectRepository researchProjectRepository;
    private final Scanner scanner;

    public CreateResearchProjectCommand(ResearchProjectRepository researchProjectRepository, Scanner scanner) {
        this.researchProjectRepository = researchProjectRepository;
        this.scanner = scanner;
    }

    public void execute() {
        System.out.print("Enter research project topic: ");
        String topic = scanner.nextLine();

        ResearchProject project = new ResearchProject(topic);

        researchProjectRepository.addProject(project);

        System.out.println("Research project created successfully.");
    }
}