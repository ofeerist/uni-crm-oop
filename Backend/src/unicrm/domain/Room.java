package unicrm.domain;

import java.util.Objects;
import java.util.UUID;

public class Room {
    private String id;
    private String name;
    private int capacity;
    private RoomType type;

    public Room() {
        this.id = UUID.randomUUID().toString();
    }

    public Room(String name, int capacity, RoomType type) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public RoomType getType() { return type; }
    public void setType(RoomType type) { this.type = type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Room{id='" + id + "', name='" + name + "', capacity=" + capacity + ", type=" + type + "}";
    }
}
