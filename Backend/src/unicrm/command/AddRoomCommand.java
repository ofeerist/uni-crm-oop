package unicrm.command;

import unicrm.domain.Manager;
import unicrm.domain.Room;
import unicrm.domain.RoomType;
import unicrm.domain.User;
import unicrm.localization.LocalizationKey;
import unicrm.localization.LocalizationService;
import unicrm.repository.RoomRepository;
import unicrm.session.UserSession;

import java.util.Scanner;

public class AddRoomCommand {

    private final RoomRepository roomRepository;
    private final UserSession userSession;
    private final Scanner scanner;
    private final LocalizationService localization = LocalizationService.getInstance();

    public AddRoomCommand(RoomRepository roomRepository, UserSession userSession, Scanner scanner) {
        this.roomRepository = roomRepository;
        this.userSession = userSession;
        this.scanner = scanner;
    }

    public void execute() {
        User currentUser = userSession.getCurrentUser();
        if (!(currentUser instanceof Manager)) {
            System.out.println(localization.get(LocalizationKey.ACCESS_DENIED));
            return;
        }

        System.out.print(localization.get(LocalizationKey.ENTER_ROOM_NAME));
        String name = scanner.nextLine().trim();

        System.out.print(localization.get(LocalizationKey.ENTER_CAPACITY));
        int capacity = 0;
        try {
            capacity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Setting capacity to 0.");
        }

        System.out.print(localization.get(LocalizationKey.SELECT_ROOM_TYPE));
        String typeInput = scanner.nextLine().trim().toUpperCase();
        RoomType roomType;
        try {
            roomType = RoomType.valueOf(typeInput);
        } catch (IllegalArgumentException e) {
            roomType = RoomType.LECTURE;
            System.out.println("Unknown type. Using LECTURE.");
        }

        Room room = new Room(name, capacity, roomType);
        roomRepository.save(room);

        System.out.println(localization.format(LocalizationKey.ROOM_ADDED, name));
    }
}
