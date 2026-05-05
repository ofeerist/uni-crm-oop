package kz.edu.unicrm.util;
import kz.edu.unicrm.domain.Course;
import java.util.Comparator;
public class CourseNameComparator implements Comparator<Course> {
    @Override
    public int compare(Course first, Course second) {
        return first.getName().compareToIgnoreCase(second.getName());
    }
}
