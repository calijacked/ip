package main.java;
import java.util.*;
public class Ragebait {
    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = "data/ragebait.txt";

    public static void main(String[] args) {
        Storage storage = new Storage("data/ragebait.txt");
        ArrayList<Task> taskList = storage.load();
        System.out.println(
                "____________________________________________________________\n" +
                        "Hello Fatty! I'm Ragebait! What can I do for you? :)\n" +
                        "____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine().trim();
            String inputArr[] = input.split(" ", 2);
            String command = inputArr[0].toLowerCase();

            System.out.println("____________________________________________________________");
            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < taskList.size(); i++) {
                        System.out.println(String.valueOf(i+1) + "." + taskList.get(i).toString());
                    }
                    break;
                case "bye":
                    System.out.println("Finally! I was getting tired of you!");
                    System.out.println("____________________________________________________________");
                    sc.close();
                    storage.save(taskList);
                    return;
                case "mark":
                    if (inputArr.length < 2) {
                        System.out.println("Specify the task number to mark you bozo!");
                        break;
                    }
                    try {
                        int markIndex = Integer.parseInt(inputArr[1]) - 1;
                        if (markIndex < 0 || taskList.size() <= markIndex) {
                            System.out.println("THIS DOES NOT EXIST BRO");
                        } else {
                            taskList.get(markIndex).markDone();
                            System.out.println("NICE LA! You managed to accomplish something in your life for once.");
                            System.out.println(taskList.get(markIndex).toString());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number!");
                        break;
                    }
                    break;
                case "unmark":
                    if (inputArr.length < 2) {
                        System.out.println("Specify the task number to unmark you bozo!");
                        break;
                    }
                    try {
                        int unmarkIndex = Integer.parseInt(inputArr[1]) - 1;
                        if (unmarkIndex < 0 || taskList.size() <= unmarkIndex) {
                            System.out.println("THIS DOES NOT EXIST!");
                        } else {
                            taskList.get(unmarkIndex).markUndone();
                            System.out.println("WHY ARE YOU SKIVING.");
                            System.out.println(taskList.get(unmarkIndex).toString());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter an integer!");
                        break;
                    }
                    break;

                case "todo":
                    if (inputArr.length < 2) {
                        System.out.println("You can't be doing nothing all day long!");
                        break;
                    }
                    ToDo currTodo = new ToDo(inputArr[1]);
                    taskList.add(currTodo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(currTodo.toString());
                    System.out.println(printTotal(taskList));
                    break;

                case "deadline":
                    if (inputArr.length < 2) {
                        System.out.println("No deadlines?! So free??");
                        break;
                    }

                    if (!inputArr[1].contains("/by")) {
                        System.out.println("Please include a /by date!");
                        break;
                    }
                    String deadline[] = inputArr[1].split("/", 2);
                    Deadline currDeadline = new Deadline(deadline[0], deadline[1]);
                    taskList.add(currDeadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(currDeadline.toString());
                    System.out.println(printTotal(taskList));
                    break;

                case "event":
                    if (inputArr.length < 2) {
                        System.out.println("Go touch some grass...");
                        break;
                    }

                    if (!inputArr[1].contains("/from") || !inputArr[1].contains("/to")) {
                        System.out.println("Please include a /from or /to date!");
                        break;
                    }
                    try {
                        String event[] = inputArr[1].split("/", 3);
                        Event currEvent = new Event(event[0], event[1], event[2]);
                        taskList.add(currEvent);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(currEvent.toString());
                        System.out.println(printTotal(taskList));
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please type the event like: description /from start /to end");
                    }
                    break;
                case "delete":
                    if (inputArr.length < 2) {
                        System.out.println("Specify the task number to delete you lazy bozo!");
                        break;
                    }
                    try {
                        int deleteIndex = Integer.parseInt(inputArr[1]) - 1;
                        if (deleteIndex < 0 || taskList.size() <= deleteIndex) {
                            System.out.println("I CAN'T DELETE! THIS DOES NOT EXIST!");
                            break;
                        } else {
                            System.out.println("Are you done? or just lazy? Removed:");
                            System.out.println(taskList.get(deleteIndex).toString());
                            taskList.remove(deleteIndex);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter an integer!");
                        break;
                    }
                    System.out.println(printTotal(taskList));
                    break;

                default:
                    System.out.println("Please enter a command. (todo, list, deadline, event, mark, unmark, delete or bye)");

            }
            System.out.println("____________________________________________________________");
        }

        }
     public static String printTotal(ArrayList<Task> taskArr) {
        return "Now you have " + taskArr.size() + " task(s) in the list.";
     }

}
