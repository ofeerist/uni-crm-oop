package kz.edu.unicrm.util;
import kz.edu.unicrm.domain.Student;
import java.util.Comparator;
public class StudentCreditsComparator implements Comparator<Student> {
    @Override
    public int compare(Student first, Student second) {
        return Integer.compare(first.getCurrentCredits(), second.getCurrentCredits());
    }
}
