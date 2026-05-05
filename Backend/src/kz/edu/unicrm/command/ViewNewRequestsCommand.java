package kz.edu.unicrm.command;
import kz.edu.unicrm.domain.RequestStatus;
import kz.edu.unicrm.domain.TechRequest;
import kz.edu.unicrm.domain.TechSupportSpecialist;
import kz.edu.unicrm.domain.User;
import kz.edu.unicrm.repository.TechRequestRepository;
import kz.edu.unicrm.service.TechSupportService;
import kz.edu.unicrm.session.UserSession;
import java.util.List;

public class ViewNewRequestsCommand {

    private final TechSupportService techSupportService;
    private final UserSession userSession;
    private final TechRequestRepository requestRepository;

    public ViewNewRequestsCommand(
            TechSupportService techSupportService,
            UserSession userSession,
            TechRequestRepository requestRepository
    ) {
        this.techSupportService = techSupportService;
        this.userSession = userSession;
        this.requestRepository = requestRepository;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();

        if (currentUser instanceof TechSupportSpecialist) {
            List<TechRequest> requests = requestRepository.findAll();

            for (TechRequest request : requests) {
                if (request.getStatus() == RequestStatus.NEW) {
                    System.out.println(request);
                }
            }
        }
    }
}