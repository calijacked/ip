import java.lang.reflect.Array;
import java.util.*;
public class Ragebait {
    public static void main(String[] args) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Hello Fatty! I'm Ragebait! What can I do for you? :)\n" +
                        "_________________________________________");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            String input = sc.nextLine().trim().toLowerCase();
            String inputArr[] = input.split(" ", 2);
            String command = inputArr[0];

            System.out.println("____________________________________________________________");
            switch (command) {
                case "list":
                    for(int i = 0; i < taskList.size(); i++) {
                        System.out.println(String.valueOf(i+1) + ". " + taskList.get(i).getStatusIcon());
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
                case "mark":
                    if (inputArr.length < 2) {
                        System.out.println("Specify the task number to mark!");
                        break;
                    }
                    int markIndex = Integer.parseInt(inputArr[1]) - 1;
                    taskList.get(markIndex).markDone();
                    System.out.println("NICE LA! You managed to accomplish something in your life for once.");
                    System.out.println(taskList.get(markIndex).getStatusIcon());
                    break;


                case "unmark":
                    if (inputArr.length < 2) {
                        System.out.println("Specify the task number to unmark!");
                        break;
                    }
                    int unmarkIndex = Integer.parseInt(inputArr[1]) - 1;
                    taskList.get(unmarkIndex).markUndone();
                    System.out.println("WHY ARE YOU SKIVING.");
                    System.out.println(taskList.get(unmarkIndex).getStatusIcon());
                    break;



                default:
                    taskList.add(new Task(input));
                    System.out.println("added: " + input);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
