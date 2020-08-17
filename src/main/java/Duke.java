public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // the horizontal line is together with 1 line of spacing
        String horizontal_line = "____________________________________________________________";

        System.out.println("Hello from\n" + logo);
        System.out.println(horizontal_line);
        greet();
        System.out.println(horizontal_line);
        bye();
        System.out.println(horizontal_line);
    }

    public static void greet(){
        String greeting = "Hello! I'm Duke\n"
                        + "What can I do for you?";
        System.out.println(greeting);
    }
    public static void bye(){
        String bye_word = "Bye. Hope to see you again soon!";
        System.out.println(bye_word);
    }
}
