package kz.edu.unicrm.command;

import kz.edu.unicrm.domain.Employee;
import kz.edu.unicrm.domain.User;
import kz.edu.unicrm.repository.UserRepository;
import kz.edu.unicrm.service.CommunicationService;
import kz.edu.unicrm.session.UserSession;

import java.util.List;

public class SendMessageCommand {

    private final CommunicationService communicationService;
    private final UserRepository userRepo;
    private final UserSession userSession;

    public SendMessageCommand(
            CommunicationService communicationService,
            UserRepository userRepo,
            UserSession userSession
    ) {
        this.communicationService = communicationService;
        this.userRepo = userRepo;
        this.userSession = userSession;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();

        if (currentUser instanceof Employee sender) {
            List<User> users = userRepo.findByRole("Employee");

            if (!users.isEmpty()) {
                Employee receiver = (Employee) users.getFirst();

                communicationService.sendMessage(
                        sender,
                        receiver,
                        "Hello from system"
                );
            }
        }
    }
}