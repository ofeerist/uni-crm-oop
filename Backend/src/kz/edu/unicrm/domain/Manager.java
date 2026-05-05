package kz.edu.unicrm.domain;
public class Manager extends Employee {
    private ManagerType type;
    public ManagerType getType() {
        return type;
    }
    public void setType(ManagerType type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "Manager{username='" + getUsername() + "', type=" + type + "}";
    }
}
