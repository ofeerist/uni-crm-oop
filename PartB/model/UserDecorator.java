package PartB.model;

public abstract class UserDecorator extends User {

    protected User decoratedUser;

    public UserDecorator(User user) {
        super(
            user.getUserId(),
            user.getFirstName(),
            user.getLastName(),
            user.getPassword(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getRegistrationDate()
        );

        this.decoratedUser = user;
    }

    public User getDecoratedUser() {
        return decoratedUser;
    }

    public void setDecoratedUser(User decoratedUser) {
        this.decoratedUser = decoratedUser;
    }

    @Override
    public boolean login(String email, String password) {
        return decoratedUser.login(email, password);
    }

    @Override
    public void logout() {
        decoratedUser.logout();
    }

    @Override
    public void viewNews() {
        decoratedUser.viewNews();
    }

    @Override
    public String toString() {
        return "UserDecorator{" +
                "decoratedUser=" + decoratedUser +
                '}';
    }
}