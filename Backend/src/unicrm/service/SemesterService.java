package unicrm.service;
import unicrm.domain.AcademicSemester;
import unicrm.domain.Season;
import unicrm.repository.AcademicSemesterRepository;

public class SemesterService {

    private final AcademicSemesterRepository semesterRepository;

    public SemesterService(AcademicSemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public AcademicSemester createSemester(Season season, int year) {
        AcademicSemester semester = new AcademicSemester(season, year);
        semesterRepository.save(semester);
        return semester;
    }

    public AcademicSemester findSemester(Season season, int year) {
        String key = season + "-" + year;
        return semesterRepository.findById(key);
    }
}
