package duke;

import duke.tasks.TaskList;
import java.util.Scanner;
import java.io.IOException;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    public static boolean isQuit;

    public Duke(){
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
        storage.init(tasks);
        parser = new Parser();
        isQuit = false;
    }

    public void run() throws IOException {
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

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

}
