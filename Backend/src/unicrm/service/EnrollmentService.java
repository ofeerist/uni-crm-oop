package unicrm.service;
import unicrm.domain.CourseOffering;
import unicrm.domain.Enrollment;
import unicrm.domain.EnrollmentStatus;
import unicrm.domain.Student;
import unicrm.repository.EnrollmentRepository;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment registerStudent(Student student, CourseOffering offering) {
        Enrollment enrollment = new Enrollment(student, offering);
        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    public void approveEnrollment(Enrollment enrollment) {
        enrollment.setStatus(EnrollmentStatus.APPROVED);
        enrollmentRepository.save(enrollment);
    }

    public void rejectEnrollment(Enrollment enrollment) {
        enrollment.setStatus(EnrollmentStatus.REJECTED);
        enrollmentRepository.save(enrollment);
    }
}
