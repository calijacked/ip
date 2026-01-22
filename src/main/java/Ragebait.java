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
            String input = sc.nextLine().trim();
            String inputArr[] = input.split(" ", 2);
            String command = inputArr[0].toLowerCase();

            System.out.println("____________________________________________________________");
            switch (command) {
                case "list":
                    for(int i = 0; i < taskList.size(); i++) {
                        System.out.println(String.valueOf(i+1) + ". " + taskList.get(i).toString());
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
                    System.out.println(taskList.get(markIndex).toString());
                    break;
                case "unmark":
                    if (inputArr.length < 2) {
                        System.out.println("Specify the task number to unmark!");
                        break;
                    }
                    int unmarkIndex = Integer.parseInt(inputArr[1]) - 1;
                    taskList.get(unmarkIndex).markUndone();
                    System.out.println("WHY ARE YOU SKIVING.");
                    System.out.println(taskList.get(unmarkIndex).toString());
                    break;

                case "todo":
                    ToDo currTodo = new ToDo(inputArr[1]);
                    taskList.add(currTodo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(currTodo.toString());
                    System.out.println(printTotal(taskList));
                    break;

                case "deadline":
                    String deadline[] = inputArr[1].split("/", 2);
                    Deadline currDeadline = new Deadline(deadline[0], deadline[1]);
                    taskList.add(currDeadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(currDeadline.toString());
                    System.out.println(printTotal(taskList));
                    break;

                case "event":
                    String event[] = inputArr[1].split("/", 3);
                    Event currEvent = new Event(event[0], event[1], event[2]);
                    taskList.add(currEvent);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(currEvent.toString());
                    System.out.println(printTotal(taskList));
                    break;



                default:
                    System.out.println("Please enter a command. (todo, list, deadline, event, blah or bye)");

            }
            System.out.println("____________________________________________________________");
        }

        }
     public static String printTotal(ArrayList<Task> taskArr) {
        return "Now you have " + taskArr.size() + " task(s) in the list.";
     }
}
