import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String horizontal_line = "____________________________________________________________";
        Scanner in = new Scanner(System.in);
        String userInputLine;
        String[] userInputParts;
        Task[] tasks = new Task[100];
        int taskCount = 0;
        String exitCommandText = "bye";

        //when the program starts
        showLogo(horizontal_line);
        greet(horizontal_line);
        userInputLine = in.nextLine();

        while(!userInputLine.equalsIgnoreCase(exitCommandText)){
            userInputParts = userInputLine.split(" ");

            if(userInputParts[0].equalsIgnoreCase("list")){
                listTasks(taskCount,tasks,horizontal_line);
            }
            else if(userInputParts[0].equalsIgnoreCase("done")){
                doneTask(Integer.parseInt(userInputParts[1]),tasks,horizontal_line);
            }
            else{
                addTask(userInputLine, horizontal_line,taskCount, tasks);
                taskCount ++;
            }
            userInputLine = in.nextLine();
        }
        bye(horizontal_line);
    }

    public static void showLogo(String horizontalLine){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(horizontalLine+ "\n" + logo);
    }

    public static void greet(String horizontalLine){
        String greeting = "Hello! I'm Duke\n"
                        + "What can I do for you?\n";
        System.out.println(horizontalLine + "\n" + greeting + horizontalLine);
    }
    public static void bye(String horizontalLine){
        String bye_word = "Bye. Hope to see you again soon!\n";
        System.out.println(horizontalLine + "\n" + bye_word + "\n" + horizontalLine);
    }

    public static void echoCommand(String userInput, String horizontalLine){
        System.out.println(horizontalLine + "\n" + userInput + "\n" + horizontalLine);
    }
    public static void addTask(String taskName, String horizontalLine, int taskIndex, Task[] tasks){
        Task t = new Task(taskName);
        tasks[taskIndex] = t;
        System.out.println(horizontalLine + "\n" + "added: " + taskName + "\n" + horizontalLine);
    }
    public static void listTasks(int taskIndex, Task[] tasks, String horizontalLine){
        for(int i = 0; i< taskIndex; i++){
            System.out.println(  (i+1)  + "." + "[" +tasks[i].getStatusIcon() +"]"+ " " + tasks[i].getName());
        }
        System.out.println(horizontalLine);
    }
    public static void doneTask(int taskIndex, Task[] tasks,  String horizontalLine){
        System.out.println(horizontalLine);
        tasks[taskIndex-1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" +tasks[taskIndex -1].getStatusIcon() +"]"+ " " + tasks[taskIndex - 1].getName());
        System.out.println(horizontalLine);
    }
}
