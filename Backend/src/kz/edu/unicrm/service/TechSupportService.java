package kz.edu.unicrm.service;
import kz.edu.unicrm.domain.RequestStatus;
import kz.edu.unicrm.domain.TechRequest;
import kz.edu.unicrm.domain.TechSupportSpecialist;
import kz.edu.unicrm.domain.User;
import kz.edu.unicrm.repository.TechRequestRepository;
import kz.edu.unicrm.repository.UserRepository;
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