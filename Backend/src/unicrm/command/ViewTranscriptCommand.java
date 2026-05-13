package unicrm.command;
import unicrm.domain.Student;
import unicrm.domain.User;
import unicrm.service.AcademicService;
import unicrm.session.UserSession;
public class ViewTranscriptCommand {
    private final AcademicService academicService;
    private final UserSession userSession;
    public ViewTranscriptCommand(AcademicService academicService, UserSession userSession) {
        this.academicService = academicService;
        this.userSession = userSession;
    }
    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (currentUser instanceof Student student) {
            String transcript = academicService.getTranscript(student);
            System.out.println(transcript);
        }
    }
}
