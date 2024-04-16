package model;

public class User {
    String name;
    String id;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id + " name=" + name;
    }
}
