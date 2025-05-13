package utils;

import manager.TeamManager;

/**
 * AutoSaveTask demonstrates:
 * - Multithreading using Runnable
 */
public class AutoSaveTask implements Runnable {

    private TeamManager manager;

    public AutoSaveTask(TeamManager manager) {
        this.manager = manager;
    }

    // Multithreading: Background saving task
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60000); // Sleep for 60 seconds
                manager.saveToFile("autosave.dat");
                System.out.println("[AutoSave] Data saved.");
            } catch (InterruptedException e) {
                System.out.println("[AutoSave] Interrupted.");
                break;
            }
        }
    }
}
