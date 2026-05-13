package unicrm.service;
import unicrm.domain.RequestStatus;
import unicrm.domain.TechRequest;
import unicrm.domain.TechSupportSpecialist;
import unicrm.domain.User;
import unicrm.repository.TechRequestRepository;
import unicrm.repository.UserRepository;
import java.util.Date;
import java.util.UUID;

public class TechSupportService {

    private final TechRequestRepository requestRepo;
    private final UserRepository userRepo;

    public TechSupportService(TechRequestRepository requestRepo, UserRepository userRepo) {
        this.requestRepo = requestRepo;
        this.userRepo = userRepo;
    }

    public void createRequest(User author, String description) {
        TechRequest request = new TechRequest();
        request.setId(UUID.randomUUID().toString());
        request.setAuthor(author);
        request.setDescription(description);
        request.setStatus(RequestStatus.NEW);
        request.setCreationDate(new Date());

        requestRepo.save(request);
    }

    public void processRequest(TechSupportSpecialist spec, TechRequest req, RequestStatus newStatus) {
        req.setExecutor(spec);
        req.setStatus(newStatus);

        spec.getHandledRequests().add(req);

        requestRepo.save(req);
        userRepo.save(spec);
    }
}