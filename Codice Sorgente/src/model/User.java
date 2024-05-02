package model;

import java.io.Serializable;

public class User implements Serializable {
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
