package duke;

public class Ui {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public Ui() {
    }

    public void showSplitter(){
        System.out.println(HORIZONTAL_LINE);
    }
    public void showLogo() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(HORIZONTAL_LINE+ "\n" + logo);
    }
    public void greet() {
        String greeting = "Hello! I'm duke.Duke\n"
                + "What can I do for you?\n";
        System.out.println(HORIZONTAL_LINE + "\n" + greeting + HORIZONTAL_LINE);
    }
    public void bye() {
        String bye_word = "Bye. Hope to see you again soon!";
        System.out.println(HORIZONTAL_LINE + "\n" + bye_word + "\n" + HORIZONTAL_LINE);
    }

    public void showLoadingProcess(){
        System.out.println("loading the previous file ...");
    }

    public void showLoadingFinished(){
        System.out.println("finished loading!");
    }
    public void showNewFileDetected(){
        System.out.println("working on a new file!");
    }
    public void printCommandError() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + " pls refer to the commands by typing 'help'.\n"
                + HORIZONTAL_LINE );
    }
    public void printHelpInfo() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "LIST, DONE, TODO, DEADLINE, EVENT, BYE, HELP\n"
                + HORIZONTAL_LINE);
    }

    public void showAddTaskSuccessful(String taskInfo, int taskCount){
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Got it. I've added this task:\n"
                + "  " +taskInfo + "\n"
                + "Now you have "+ taskCount + " duke.tasks in the list\n"
                + HORIZONTAL_LINE);
    }
    public void sayShowTaskList(){
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Here are the duke.tasks in your list:");
    }
    public void printInvalidTaskNumError(){
        System.out.println(HORIZONTAL_LINE+"\n"
                + "☹ OOPS!!! The task number of the one you have done is invalid!\n"
                + Ui.HORIZONTAL_LINE);
    }
    public void printNotSetTaskNumError(){
        System.out.println(HORIZONTAL_LINE+"\n"
                + "☹ OOPS!!! You did not set that task!\n"
                + HORIZONTAL_LINE);
    }
    public void showTaskDone(String taskInfo){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskInfo);
        System.out.println(HORIZONTAL_LINE);
    }
    public void showTaskDeleting(String taskInfo){
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Noted. I've removed this task:\n"
                + taskInfo);
    }
    public void showTaskNum(int taskCount){
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Now you have "+ taskCount + " duke.tasks in the list\n"
                + HORIZONTAL_LINE);
    }

    public void showEmptyDescriptionError(String commandName){
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! The description of a " + commandName + " cannot be empty\n"
                + HORIZONTAL_LINE);
    }

    public void showDeadlineKeywordError(){
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! The key word '/by' is missing or incomplete.\n"
                + HORIZONTAL_LINE);
    }
    public void showDeadlineInputError(){
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! The deadline name or time should not be empty\n"
                + HORIZONTAL_LINE);
    }
    public void showEventKeywordError(){
        System.out.println(Ui.HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! The key word '/at' is missing or incomplete.\n"
                + Ui.HORIZONTAL_LINE);
    }
    public void showEventInputError(){
        System.out.println(Ui.HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! The event name or time should not be empty\n"
                + Ui.HORIZONTAL_LINE);
    }
    public void showMissingFile(){
        System.out.println(Ui.HORIZONTAL_LINE + "\n"
                + "File does not exist, creating new file ...\n"
                + Ui.HORIZONTAL_LINE);
    }
    public void showMissingFolder(){
        System.out.println(Ui.HORIZONTAL_LINE + "\n"   //how to check folders?
                + "Folder does not exist, creating new folder and file ...\n"
                + Ui.HORIZONTAL_LINE);
    }
    public void showDirCreatedSucceed(){
        System.out.println("Directory created successfully");
    }
    public void showDirCreatedFail(){
        System.out.println("Sorry, Directory cannot be created");
    }
    public void showFileCreatedFail(){
        System.out.println("Sorry, file cannot be created");
    }
}
