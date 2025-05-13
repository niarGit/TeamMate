package manager;

import model.Member;
import dao.MemberDAO;
import java.util.*;
import java.io.*;

/**
 * TeamManager demonstrates:
 * - Collections Framework (List)
 * - Generics
 * - Exception handling
 * - File I/O
 * - Serialization
 */
public class TeamManager {
    // Collection: List of Member objects (Collections Framework)
    private List<Member> members = new ArrayList<>();
    private MemberDAO memberDAO = new MemberDAO(); // Database handler

    // Adds a new member only if not already in the list
    public void addMember(Member member) {
        if (!members.contains(member)) {
            members.add(member); // Using Collection.add()
            memberDAO.addMember(member); // Save to DB
        }
    }

    // Prints all members
    public void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("No members yet.");
            return;
        }
        // Lambda and method reference - part of Java 8 Collection API
        members.forEach(System.out::println);
    }

    // Searches a member by name
    public void searchByName(String name) {
        members.stream()
               .filter(m -> m.getName().equalsIgnoreCase(name))
               .findFirst()
               .ifPresentOrElse(
                   System.out::println,
                   () -> System.out.println("Member not found.")
               );
    }

    // Serialization: Saves the members list to a file
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(members); // Unsafe cast warning due to generics
            System.out.println("Data saved.");
        } catch (IOException e) {
            // Exception Handling
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Deserialization: Loads the members list from a file
    //@SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        //try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            //members = (List<Member>) ois.readObject(); // Type casting after deserialization
            //System.out.println("Data loaded.");
        try{
            members = memberDAO.getAllMembers();
            System.out.println("[DB] Members loaded from database.");

        } catch (Exception e) {
            // Broad Exception handling for demo purposes
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // Returns the current list (for threading or DB use)
    public List<Member> getMembers() {
        return members;
    }
}
