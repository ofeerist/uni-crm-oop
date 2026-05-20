package unicrm.service;
import unicrm.domain.Complaint;
import unicrm.domain.Employee;
import unicrm.domain.Message;
import unicrm.domain.Student;
import unicrm.domain.Teacher;
import unicrm.domain.UrgencyLevel;
import unicrm.domain.User;
import unicrm.repository.ComplaintRepository;
import unicrm.repository.MessageRepository;
import unicrm.repository.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CommunicationService {

    private final MessageRepository messageRepo;
    private final ComplaintRepository complaintRepo;
    private final UserRepository userRepo;

    public CommunicationService(MessageRepository messageRepo,
                                ComplaintRepository complaintRepo,
                                UserRepository userRepo) {
        this.messageRepo = messageRepo;
        this.complaintRepo = complaintRepo;
        this.userRepo = userRepo;
    }

    public void sendMessage(Employee sender, Employee receiver, String content) {
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setSenderId(sender.getId());
        message.setReceiverId(receiver.getId());
        message.setContent(content);
        message.setTimestamp(new Date());

        sender.getSentMessages().add(message);
        receiver.getReceivedMessages().add(message);

        messageRepo.save(message);
        userRepo.save(sender);
        userRepo.save(receiver);
    }

    public List<Message> getSentMessages(String userId) {
        return messageRepo.findAll().stream()
                .filter(m -> Objects.equals(m.getSenderId(), userId))
                .toList();
    }

    public List<Message> getReceivedMessages(String userId) {
        return messageRepo.findAll().stream()
                .filter(m -> Objects.equals(m.getReceiverId(), userId))
                .toList();
    }

    public String resolveUsername(String userId, UserRepository repo) {
        User user = repo.findById(userId);
        return user != null ? user.getUsername() : userId;
    }

    public void sendComplaint(Teacher teacher,
                              Student student,
                              UrgencyLevel urgencyLevel,
                              String reason) {
        Complaint complaint = new Complaint();
        complaint.setId(UUID.randomUUID().toString());
        complaint.setTeacherId(teacher.getId());
        complaint.setStudentId(student.getId());
        complaint.setUrgency(urgencyLevel);
        complaint.setReason(reason);
        complaint.setCreationDate(new Date());

        complaintRepo.save(complaint);
    }
}
