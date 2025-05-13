package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Member class demonstrates:
 * - Encapsulation (OOP)
 * - Serializable (for file saving/loading)
 * - Overrides from Object class (equals, hashCode, toString)
 */
public class Member implements Serializable {
    private int id; // New field for database use (optional)
    private String name;
    private String role;
    private int age;

    // Constructor without ID (used before saving to DB)
    public Member(String name, String role, int age) {
        this.name = name;
        this.role = role;
        this.age = age;
    }

    // Constructor with ID (used when loading from DB)
    public Member(int id, String name, String role, int age) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.age = age;
    }

    // Getters - Encapsulation
    public int getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public int getAge() { return age; }

    // Setters for id (e.g., after auto-increment insert)
    public void setId(int id) {
        this.id = id;
    }

    // toString() - From Object class
    @Override
    public String toString() {
        return "[" + id + "] " + name + " (" + role + "), Age: " + age;
    }

    // equals() - Based on name for uniqueness (can be adjusted)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Member)) return false;
        Member other = (Member) obj;
        return this.name.equalsIgnoreCase(other.name);
    }

    // hashCode() - Consistent with equals
    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }
}
