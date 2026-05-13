package unicrm.command;
import unicrm.domain.RequestStatus;
import unicrm.domain.TechRequest;
import unicrm.domain.TechSupportSpecialist;
import unicrm.domain.User;
import unicrm.repository.TechRequestRepository;
import unicrm.service.TechSupportService;
import unicrm.session.UserSession;
import java.util.List;

public class ChangeRequestStatusCommand {

    private final TechSupportService techSupportService;
    private final TechRequestRepository techRepo;
    private final UserSession userSession;

    public ChangeRequestStatusCommand(
            TechSupportService techSupportService,
            TechRequestRepository techRepo,
            UserSession userSession
    ) {
        this.techSupportService = techSupportService;
        this.techRepo = techRepo;
        this.userSession = userSession;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();

        if (currentUser instanceof TechSupportSpecialist specialist) {
            List<TechRequest> requests = techRepo.findAll();

            if (!requests.isEmpty()) {
                TechRequest request = requests.getFirst();

                techSupportService.processRequest(
                        specialist,
                        request,
                        RequestStatus.ACCEPTED
                );
            }
        }
    }
}