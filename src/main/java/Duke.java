import java.util.Scanner;


public class Duke {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    private static String userInputLine;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean isQuit = false;
        String command;
        String description;

        //when the program starts
        showLogo();
        greet();

        while(!isQuit){
            userInputLine = in.nextLine();
            command = processCommand();
            description = processDescription(command);

            switch (command.toUpperCase()) {
            case "LIST":
                listTasks();
                break;
            case "DONE":
                doneTask(Integer.parseInt(description));
                break;
            case "TODO":
                addTask(new ToDo(description));
                break;
            case "DEADLINE":
                String deadlineTime = processDeadlineTime(description);
                String deadlineName =processDeadlineName(description);
                addTask(new Deadline(deadlineName,deadlineTime));
                break;
            case "EVENT":
                String eventTime = processEventTime(description);
                String eventName =processEventName(description);
                addTask(new Deadline(eventName,eventTime));
                break;
            case "BYE":
                isQuit = true;
                bye();
                break;
            case "HELP":
                System.out.println(HORIZONTAL_LINE + "\n"
                        + "LIST, DONE, TODO, DEADLINE, EVENT, BYE, HELP"
                        + HORIZONTAL_LINE);
                break;
            default:
                System.out.println("The input is invalid, pls refer to the commands by typing 'help'. ");
                break;
            }
        }
    }

    public static void showLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(HORIZONTAL_LINE+ "\n" + logo);
    }

    public static void greet(){
        String greeting = "Hello! I'm Duke\n"
                        + "What can I do for you?\n";
        System.out.println(HORIZONTAL_LINE + "\n" + greeting + HORIZONTAL_LINE);
    }
    public static void bye(){
        String bye_word = "Bye. Hope to see you again soon!";
        System.out.println(HORIZONTAL_LINE + "\n" + bye_word + "\n" + HORIZONTAL_LINE);
    }

    /*public static void echoCommand(String userInput){
        System.out.println(HORIZONTAL_LINE + "\n" + userInput + "\n" + HORIZONTAL_LINE);
    }*/

    public static void addTask(Task t){
        tasks[taskCount] = t;
        taskCount ++;
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Got it. I've added this task: \n"
                + "  " +t.toString() + "\n"
                + "Now you have "+ taskCount + " tasks in the list \n"
                + HORIZONTAL_LINE);
    }
    public static void listTasks(){
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Here are the tasks in your list: ");
        for(int i = 0; i< taskCount; i++){
            System.out.println((i+1)+"."+tasks[i].toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }
    public static void doneTask(int taskIndex){
        System.out.println(HORIZONTAL_LINE);
        tasks[taskIndex-1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[taskIndex-1].toString());
        System.out.println(HORIZONTAL_LINE);
    }

    public static String processCommand(){
        return userInputLine.split(" ")[0];
    }
    public static String processDescription(String command){
        return userInputLine.replace(command, " ").trim();
    }
    public static String processDeadlineTime(String description){
        return description.substring(description.indexOf("/by") + 3).trim();
    }
    public static String processDeadlineName(String description){
        return description.substring(0, description.indexOf("/by")).trim();
    }
    public static String processEventTime(String description){
        return description.substring(description.indexOf("/at") + 3).trim();
    }
    public static String processEventName(String description){
        return description.substring(0, description.indexOf("/at")).trim();
    }

}
