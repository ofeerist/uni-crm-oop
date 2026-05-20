package unicrm.service;
import unicrm.domain.AcademicSemester;
import unicrm.domain.Course;
import unicrm.domain.CourseOffering;
import unicrm.domain.Teacher;
import unicrm.repository.CourseOfferingRepository;

public class CourseOfferingService {

    private final CourseOfferingRepository offeringRepository;

    public CourseOfferingService(CourseOfferingRepository offeringRepository) {
        this.offeringRepository = offeringRepository;
    }

    public CourseOffering createOffering(
            Course course,
            AcademicSemester semester,
            Teacher instructor,
            int capacity
    ) {

        CourseOffering offering =
                new CourseOffering(
                        course,
                        semester,
                        instructor,
                        capacity
                );
        offeringRepository.save(offering);
        return offering;
    }
}
