package ui;

public class Ui {
    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    public Ui() {
    }

    public void showSplitter() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Print logo as text in the command line.
     */
    public void showLogo() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(HORIZONTAL_LINE + "\n" + logo);
    }

    /**
     * Print greetings at the start of the program.
     */
    public void greet() {
        String greeting = "Hello! I'm Duke.\n"
                + "What can I do for you?\n";
        System.out.println(HORIZONTAL_LINE + "\n" + greeting + HORIZONTAL_LINE);
    }

    /**
     * Print GoodBye words when BYE command is detected.
     */
    public void bye() {
        String byeWord = "Bye. Hope to see you again soon!";
        System.out.println(HORIZONTAL_LINE + "\n" + byeWord + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Print loading messages right before the auto-loading begins to execute.
     */
    public void showLoadingProcess() {
        System.out.println("loading the previous file ...");
    }

    /**
     * Print loading messages right after the auto-loading finishes execution.
     */
    public void showLoadingFinished() {
        System.out.println("finished loading!");
    }

    /**
     * Print reminders after new auto-saved file is created.
     */
    public void showNewFileDetected() {
        System.out.println("working on a new file!");
    }

    /**
     * Print error messages if command type cannot be detected.
     */
    public void printCommandError() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + " pls refer to the commands by typing 'help'.\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Print all available command types.
     */
    public void printHelpInfo() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "LIST, DONE, TODO, DEADLINE, EVENT, FIND, BYE, HELP\n"
                + HORIZONTAL_LINE);
    }

    /**
     * show content of the task newly added.
     *
     * @param taskInfo content of the task
     * @param taskCount total num of tasks in the list
     */
    public void showAddTaskSuccessful(String taskInfo, int taskCount) {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Got it. I've added this task:\n"
                + "  " + taskInfo + "\n"
                + "Now you have " + taskCount + " tasks in the list\n"
                + HORIZONTAL_LINE);
    }

    public void sayShowTaskList() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Here are the tasks in your list:");
    }

    /**
     * Print error messages when the number selected is
     * empty or cannot be converted to an integer.
     */
    public void printInvalidTaskNumError() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! The task number of the one you have done is invalid!\n"
                + Ui.HORIZONTAL_LINE);
    }

    /**
     * Print error messages when the number selected is out of bound.
     */
    public void printNotSetTaskNumError() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! You did not set that task!\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Show the content of the task finished.
     *
     * @param taskInfo content of the task
     */
    public void showTaskDone(String taskInfo) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskInfo);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Show the content of the task deleted.
     *
     * @param taskInfo content of the task
     */
    public void showTaskDeleting(String taskInfo) {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Noted. I've removed this task:\n"
                + taskInfo);
    }

    /**
     * Show number of tasks in the ArrayList.
     *
     * @param taskCount total number of tasks in the list
     */
    public void showTaskNum(int taskCount) {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Now you have " + taskCount + " tasks in the list\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Print error messages when description of the command is empty.
     *
     * @param commandName type of command
     */
    public void showEmptyDescriptionError(String commandName) {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! The description of a " + commandName + " cannot be empty\n"
                + HORIZONTAL_LINE);
    }

    public void showDeadlineKeywordError() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! The key word '/by' is missing or incomplete.\n"
                + HORIZONTAL_LINE);
    }

    public void showDeadlineInputError() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! The deadline name or time should not be empty\n"
                + HORIZONTAL_LINE);
    }

    public void showEventKeywordError() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! The key word '/at' is missing or incomplete.\n"
                + HORIZONTAL_LINE);
    }

    public void showEventInputError() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "☹ OOPS!!! The event name or time should not be empty\n"
                + HORIZONTAL_LINE);
    }

    public void showMissingFile() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "File does not exist, creating new file ...\n"
                + HORIZONTAL_LINE);
    }

    public void showMissingFolder() {
        System.out.println(HORIZONTAL_LINE + "\n"   //how to check folders?
                + "Folder does not exist, creating new folder and file ...\n"
                + HORIZONTAL_LINE);
    }

    public void showDirCreatedSucceed() {
        System.out.println("Directory created successfully");
    }

    public void showDirCreatedFail() {
        System.out.println("Sorry, Directory cannot be created");
    }

    public void showFileCreatedFail() {
        System.out.println("Sorry, file cannot be created");
    }

    public void showMatchingTasks() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Here are the matching tasks in your list:");
    }
}
