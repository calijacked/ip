import java.lang.reflect.Array;
import java.util.*;
public class Ragebait {
    public static void main(String[] args) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Hello Fatty! I'm Ragebait! What can I do for you? :)\n" +
                        "_________________________________________");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> userText = new ArrayList<>();
        while (true) {
            String input = sc.nextLine().trim().toLowerCase();
            System.out.println("____________________________________________________________");
            switch (input) {
                case "list":
                    for(int i = 0; i < userText.size(); i++) {
                        System.out.println(String.valueOf(i+1) + ". " + userText.get(i));
                    }
                    break;
                case "blah":
                    System.out.println("Blah blah blah… wow, you’re original!");
                    break;
                case "bye":
                    System.out.println("Finally! I was getting tired of you!");
                    System.out.println("____________________________________________________________");
                    sc.close();
                    return;
                default:
                    userText.add(input);
                    System.out.println("added: " + input);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
