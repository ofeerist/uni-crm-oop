package unicrm.service;

import unicrm.domain.CourseOffering;
import unicrm.domain.Lesson;
import unicrm.domain.LessonType;
import unicrm.domain.Room;
import unicrm.domain.RoomType;
import unicrm.domain.Teacher;
import unicrm.repository.CourseOfferingRepository;
import unicrm.repository.RoomRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScheduleService {

    private static final List<String> LECTURE_SLOTS = List.of(
            "MON 08:00", "WED 08:00", "FRI 08:00",
            "MON 10:00", "WED 10:00", "FRI 10:00",
            "MON 12:00", "WED 12:00", "FRI 12:00",
            "MON 14:00", "WED 14:00", "FRI 14:00",
            "MON 16:00", "WED 16:00", "FRI 16:00"
    );

    private static final List<String> PRACTICE_SLOTS = List.of(
            "TUE 08:00", "THU 08:00",
            "TUE 10:00", "THU 10:00",
            "TUE 12:00", "THU 12:00",
            "TUE 14:00", "THU 14:00",
            "TUE 16:00", "THU 16:00"
    );

    private final CourseOfferingRepository offeringRepo;
    private final RoomRepository roomRepo;

    public ScheduleService(CourseOfferingRepository offeringRepo, RoomRepository roomRepo) {
        this.offeringRepo = offeringRepo;
        this.roomRepo = roomRepo;
    }

    /**
     * Generates one LECTURE and one PRACTICE lesson for the given offering.
     * Slots and rooms are chosen to avoid conflicts with existing offerings.
     *
     * @return true if at least one lesson was scheduled
     */
    public boolean generateSchedule(CourseOffering offering) {
        List<Room> rooms = roomRepo.findAll();
        if (rooms.isEmpty()) return false;

        List<CourseOffering> allOfferings = offeringRepo.findAll();
        Set<String> teacherBusy = getTeacherBusySlots(offering.getInstructor(), allOfferings);

        // LECTURE
        Room lectureRoom = findRoom(rooms, RoomType.LECTURE, offering.getCapacity());
        String lectureSlot = findFreeSlot(LECTURE_SLOTS, teacherBusy, lectureRoom, allOfferings);
        if (lectureSlot != null && lectureRoom != null) {
            offering.addLesson(new Lesson(offering, lectureRoom, LessonType.LECTURE, lectureSlot));
            teacherBusy.add(lectureSlot);
        }

        // PRACTICE (smaller group — half capacity)
        int practiceCapacity = Math.max(1, offering.getCapacity() / 2);
        Room practiceRoom = findPracticeRoom(rooms, practiceCapacity);
        String practiceSlot = findFreeSlot(PRACTICE_SLOTS, teacherBusy, practiceRoom, allOfferings);
        if (practiceSlot != null && practiceRoom != null) {
            offering.addLesson(new Lesson(offering, practiceRoom, LessonType.PRACTICE, practiceSlot));
        }

        return !offering.getLessons().isEmpty();
    }

    private Room findRoom(List<Room> rooms, RoomType preferredType, int minCapacity) {
        return rooms.stream()
                .filter(r -> r.getType() == preferredType && r.getCapacity() >= minCapacity)
                .findFirst()
                .orElseGet(() -> rooms.stream()
                        .filter(r -> r.getCapacity() >= minCapacity)
                        .findFirst()
                        .orElse(rooms.getFirst()));
    }

    private Room findPracticeRoom(List<Room> rooms, int minCapacity) {
        return rooms.stream()
                .filter(r -> (r.getType() == RoomType.PRACTICE || r.getType() == RoomType.LAB)
                        && r.getCapacity() >= minCapacity)
                .findFirst()
                .orElseGet(() -> rooms.stream()
                        .filter(r -> r.getCapacity() >= minCapacity)
                        .findFirst()
                        .orElse(rooms.getFirst()));
    }

    private String findFreeSlot(List<String> slots, Set<String> teacherBusy,
                                Room room, List<CourseOffering> allOfferings) {
        Set<String> roomBusy = room != null ? getRoomBusySlots(room, allOfferings) : Set.of();
        return slots.stream()
                .filter(s -> !teacherBusy.contains(s) && !roomBusy.contains(s))
                .findFirst()
                .orElse(null);
    }

    private Set<String> getTeacherBusySlots(Teacher teacher, List<CourseOffering> allOfferings) {
        Set<String> busy = new HashSet<>();
        for (CourseOffering o : allOfferings) {
            if (o.getInstructor() != null && o.getInstructor().equals(teacher)) {
                for (Lesson l : o.getLessons()) {
                    busy.add(l.getTimeSlot());
                }
            }
        }
        return busy;
    }

    private Set<String> getRoomBusySlots(Room room, List<CourseOffering> allOfferings) {
        Set<String> busy = new HashSet<>();
        for (CourseOffering o : allOfferings) {
            for (Lesson l : o.getLessons()) {
                if (l.getRoom() != null && l.getRoom().equals(room)) {
                    busy.add(l.getTimeSlot());
                }
            }
        }
        return busy;
    }
}
