import java.util.*;
public class Ragebait {
    public static void main(String[] args) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Hello Fatty! I'm Ragebait! What can I do for you? :)\n" +
                        "_________________________________________");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine().trim().toLowerCase();
            System.out.println("____________________________________________________________");
            switch (input) {
                case "list":
                    System.out.println("here you go! an empty list!");
                    break;
                case "blah":
                    System.out.println("Blah blah blah… wow, you’re original!");
                    break;
                case "bye":
                    System.out.println("Finally! I was getting tired of you!");
                    System.out.println("____________________________________________________________");

                    sc.close();
                    return;
            }
            System.out.println("____________________________________________________________");
        }
    }
}
