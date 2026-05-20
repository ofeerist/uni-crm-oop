package unicrm.command;

import unicrm.domain.Employee;
import unicrm.domain.Message;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.UserRepository;
import unicrm.service.CommunicationService;
import unicrm.session.UserSession;

import java.util.List;

public class ViewMessagesCommand {

    private final CommunicationService communicationService;
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final LocalizationService localization = LocalizationService.getInstance();

    public ViewMessagesCommand(
            CommunicationService communicationService,
            UserRepository userRepository,
            UserSession userSession
    ) {
        this.communicationService = communicationService;
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public void execute() {
        User currentUser = userSession.getEffectiveUser();
        if (!(currentUser instanceof Employee)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        String userId = currentUser.getId();

        List<Message> received = communicationService.getReceivedMessages(userId);
        List<Message> sent = communicationService.getSentMessages(userId);

        if (received.isEmpty() && sent.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.NO_MESSAGES));
            return;
        }

        if (!received.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.MESSAGES_RECEIVED_HEADER));
            for (Message msg : received) {
                String senderName = communicationService.resolveUsername(msg.getSenderId(), userRepository);
                System.out.println(localization.format(LocalizationKey.MESSAGE_FROM, senderName));
                System.out.println(localization.format(LocalizationKey.MESSAGE_CONTENT_LABEL, msg.getContent()));
                System.out.println(localization.format(LocalizationKey.MESSAGE_TIME, msg.getTimestamp()));
                System.out.println();
            }
        }

        if (!sent.isEmpty()) {
            System.out.println(localization.get(LocalizationKey.MESSAGES_SENT_HEADER));
            for (Message msg : sent) {
                String receiverName = communicationService.resolveUsername(msg.getReceiverId(), userRepository);
                System.out.println(localization.format(LocalizationKey.MESSAGE_TO, receiverName));
                System.out.println(localization.format(LocalizationKey.MESSAGE_CONTENT_LABEL, msg.getContent()));
                System.out.println(localization.format(LocalizationKey.MESSAGE_TIME, msg.getTimestamp()));
                System.out.println();
            }
        }
    }
}
