package duke;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidKeywordException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.Scanner;

public class Duke {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final int MAX_TASK_SIZE = 100;
    private static Task[] tasks = new Task[MAX_TASK_SIZE];
    private static int taskCount = 0;
    private static String userInputLine;
    private static String command;  // one question: when to make those variable static?
    private static String description;
    private static String taskName;
    private static String taskTime;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean isQuit = false;

        //when the program starts
        showLogo();
        greet();

        while(!isQuit) {
            processInput(in);

            switch (command.toUpperCase()) {
            case "LIST":
                listTasks();
                break;
            case "DONE":
                if(checkDescription() && checkDoneNum()) {
                    doneTask(Integer.parseInt(description));
                }
                break;
            case "TODO":
                if(checkDescription()) {
                    addTask(new ToDo(description));
                }
                break;
            case "DEADLINE":
                if(checkDescription() && checkDeadline()) {
                    addTask(new Deadline(taskName,taskTime));
                }
                break;
            case "EVENT":
                if(checkDescription() && checkEvent()) {
                    addTask(new Event(taskName, taskTime));
                }
                break;
            case "BYE":
                isQuit = true;
                bye();
                break;
            case "HELP":
                printHelpInfo();
                break;
            default:
                printErrorInfo();
                break;
            }
        }
    }

    private static void processInput(Scanner in) {
            userInputLine = in.nextLine();
            command = processCommand();
    }

    private static void printErrorInfo() {
        System.out.println(HORIZONTAL_LINE
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + " pls refer to the commands by typing 'help'.\n"
                + HORIZONTAL_LINE );
    }
    private static void printHelpInfo() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "LIST, DONE, TODO, DEADLINE, EVENT, BYE, HELP\n"
                + HORIZONTAL_LINE);
    }

    public static void showLogo() {
        String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(HORIZONTAL_LINE+ "\n" + logo);
    }
    public static void greet() {
        String greeting = "Hello! I'm duke.Duke\n"
                        + "What can I do for you?\n";
        System.out.println(HORIZONTAL_LINE + "\n" + greeting + HORIZONTAL_LINE);
    }
    public static void bye() {
        String bye_word = "Bye. Hope to see you again soon!";
        System.out.println(HORIZONTAL_LINE + "\n" + bye_word + "\n" + HORIZONTAL_LINE);
    }

    /*public static void echoCommand(String userInput) {
        System.out.println(HORIZONTAL_LINE + "\n" + userInput + "\n" + HORIZONTAL_LINE);
    }*/

    public static void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount ++;
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Got it. I've added this task:\n"
                + "  " +t.toString() + "\n"
                + "Now you have "+ taskCount + " duke.tasks in the list\n"
                + HORIZONTAL_LINE);
    }
    public static void listTasks() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Here are the duke.tasks in your list:");
        for (int i = 0; i< taskCount; i++) {
            System.out.println((i+1)+"."+tasks[i].toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }
    public static Boolean checkDoneNum() {
        boolean isNumValid = true;
        try{
            int inputNum = Integer.parseInt(description);
            if (inputNum > taskCount || taskCount == 0 || inputNum == 0) {
                throw new DukeException();
            }
        }catch (NumberFormatException e) {
            isNumValid = false;
            System.out.println(HORIZONTAL_LINE+"\n"
                    + "☹ OOPS!!! The task number of the one you have done is invalid!\n"
                    +HORIZONTAL_LINE);
        }catch (DukeException e) {
            isNumValid = false;
            System.out.println(HORIZONTAL_LINE+"\n"
                    + "☹ OOPS!!! You did not set that task!\n"
                    +HORIZONTAL_LINE);
        }
        return isNumValid;
    }
    public static void doneTask(int taskIndex) {
        System.out.println(HORIZONTAL_LINE);
        tasks[taskIndex-1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[taskIndex-1].toString());
        System.out.println(HORIZONTAL_LINE);
    }

    public static String processCommand() {
        return userInputLine.split(" ")[0];
    }
    public static boolean checkDescription() {
        boolean isDescriptionExist = true;
        try {
            processDescription(command);
        }
        catch (EmptyDescriptionException e) {
            System.out.println(HORIZONTAL_LINE + "\n"
                    + "☹ OOPS!!! The description of a " + command.toLowerCase() + " cannot be empty\n"
                    + HORIZONTAL_LINE);
            isDescriptionExist = false;
        }
        return isDescriptionExist;
    }
    public static void processDescription(String command) throws EmptyDescriptionException {
        description = userInputLine.replace(command, " ").trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    public static boolean checkDeadline() {
        boolean isDescriptionValid = true;
        try{
            processDeadline(description);
        }catch (InvalidKeywordException e) {
            System.out.println(HORIZONTAL_LINE + "\n"
                    + "☹ OOPS!!! The key word '/by' is missing or incomplete.\n"
                    + HORIZONTAL_LINE);
            isDescriptionValid = false;
        }catch (EmptyDescriptionException e) {
            System.out.println(HORIZONTAL_LINE + "\n"
                    + "☹ OOPS!!! The deadline name or time should not be empty\n"
                    + HORIZONTAL_LINE);
            isDescriptionValid = false;
        }
        return isDescriptionValid;
    }

    public static void processDeadline(String description) throws InvalidKeywordException,EmptyDescriptionException {
        try {
            taskName = description.substring(0, description.indexOf("/by")).trim();
            taskTime = description.substring(description.indexOf("/by") + 3).trim();
        }catch (IndexOutOfBoundsException e) {
            throw new InvalidKeywordException();
        }
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        else if (taskTime.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }
    public static boolean checkEvent(){
        boolean isDescriptionValid = true;
        try{
            processEvent(description);
        }catch (InvalidKeywordException e) {
            System.out.println(HORIZONTAL_LINE + "\n"
                    + "☹ OOPS!!! The key word '/at' is missing or incomplete.\n"
                    + HORIZONTAL_LINE);
            isDescriptionValid = false;
        }catch (EmptyDescriptionException e) {
            System.out.println(HORIZONTAL_LINE + "\n"
                    + "☹ OOPS!!! The event name or time should not be empty\n"
                    + HORIZONTAL_LINE);
            isDescriptionValid = false;
        }
        return isDescriptionValid;
    }
    public static void processEvent(String description) throws InvalidKeywordException,EmptyDescriptionException {
        try {
            taskName = description.substring(0, description.indexOf("/at")).trim();
            taskTime = description.substring(description.indexOf("/at") + 3).trim();
        }catch (IndexOutOfBoundsException e) {
            throw new InvalidKeywordException();
        }
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        else if (taskTime.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

}
