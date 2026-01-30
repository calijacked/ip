package ui;
import java.util.Scanner;

public class UI {
    private final Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello Fatty! I'm Ragebait! What can I do for you? :)");
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void close() {
        sc.close();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
