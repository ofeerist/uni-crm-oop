package unicrm.command;
import unicrm.domain.RequestStatus;
import unicrm.domain.TechRequest;
import unicrm.domain.TechSupportSpecialist;
import unicrm.domain.User;
import unicrm.repository.TechRequestRepository;
import unicrm.service.TechSupportService;
import unicrm.session.UserSession;
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
