package unicrm.domain;

public class Room {
    private String number;
    private int capacity;
    private RoomType type;

    public Room(String number, int capacity, RoomType type) {
        this.number = number;
        this.capacity = capacity;
        this.type = type;
    }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public RoomType getType() { return type; }
    public void setType(RoomType type) { this.type = type; }
}