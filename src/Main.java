import java.util.Scanner;
import manager.TeamManager;
import model.Member;
import utils.AutoSaveTask;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

        // Create a TeamManager instance (OOP)
        TeamManager manager = new TeamManager();

        // Start the auto-save thread (Multithreading)
        Thread autoSaveThread = new Thread(new AutoSaveTask(manager));
        autoSaveThread.setDaemon(true); // Run in background
        autoSaveThread.start();

        // Load previously saved data
        manager.loadFromFile("teammates.dat");

        while (true) {
            // Console Menu
            System.out.println("\n--- Teammate Manager ---");
            System.out.println("1. Add Member");
            System.out.println("2. View Members");
            System.out.println("3. Search by Name");
            System.out.println("4. Save Now");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Role: ");
                    String role = scanner.nextLine();
                    System.out.print("Age: ");
                    int age = Integer.parseInt(scanner.nextLine());

                    Member member = new Member(name, role, age);
                    manager.addMember(member); // Using OOP
                    break;

                case "2":
                    manager.viewMembers();
                    break;

                case "3":
                    System.out.print("Search name: ");
                    String search = scanner.nextLine();
                    manager.searchByName(search);
                    break;

                case "4":
                    manager.saveToFile("teammates.dat");
                    break;

                case "5":
                    // Save before exit
                    manager.saveToFile("teammates.dat");
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }}}
}
