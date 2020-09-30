package duke;


import duke.tasks.TaskList;
import java.util.Scanner;
import java.io.IOException;

public class Duke {

    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;
    private static Parser parser;
    public static boolean isQuit = false;

    public static void main(String[] args) throws IOException {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
        storage.init(tasks);
        parser = new Parser();


        Scanner in = new Scanner(System.in);

        //when the program starts
        ui.showLogo();
        ui.greet();

        while(!isQuit) {
            parser.processInput(in);
            parser.respondToCommand(parser.command, tasks);
        }
        storage.autoSave(storage.autoSavedFile,tasks);
    }


}
