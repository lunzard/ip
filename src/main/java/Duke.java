import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String horizontal_line = "____________________________________________________________";
        Scanner in = new Scanner(System.in);
        String userInputLine;

        String exitCommandText = "bye";

        showLogo(horizontal_line);
        greet(horizontal_line);

        userInputLine = in.nextLine();
        while(!userInputLine.equalsIgnoreCase(exitCommandText)){
            echoCommand(userInputLine,horizontal_line);
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
}
