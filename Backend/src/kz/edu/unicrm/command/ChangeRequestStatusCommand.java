package kz.edu.unicrm.command;
import kz.edu.unicrm.domain.RequestStatus;
import kz.edu.unicrm.domain.TechRequest;
import kz.edu.unicrm.domain.TechSupportSpecialist;
import kz.edu.unicrm.domain.User;
import kz.edu.unicrm.repository.TechRequestRepository;
import kz.edu.unicrm.service.TechSupportService;
import kz.edu.unicrm.session.UserSession;
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