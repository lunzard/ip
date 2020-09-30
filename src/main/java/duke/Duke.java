package duke;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidKeywordException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;


import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static String userInputLine;
    private static String command;  
    private static String description;
    private static String taskName;
    private static String taskTime;
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;

    public static void main(String[] args) throws IOException {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        storage.init(tasks);

        Scanner in = new Scanner(System.in);
        boolean isQuit = false;

        //when the program starts
        ui.showLogo();
        ui.greet();

        while(!isQuit) {
            processInput(in);

            switch (command.toUpperCase()) {
            case "LIST":
                tasks.listTasks();
                break;
            case "DONE":
                if(checkDescription() && checkTaskNum()) {
                    tasks.doneTask(Integer.parseInt(description));
                }
                break;
            case "TODO":
                if(checkDescription()) {
                    tasks.addTask(new ToDo(description));
                }
                break;
            case "DEADLINE":
                if(checkDescription() && checkDeadline()) {
                    tasks.addTask(new Deadline(taskName,taskTime));
                }
                break;
            case "EVENT":
                if(checkDescription() && checkEvent()) {
                    tasks.addTask(new Event(taskName, taskTime));
                }
                break;
            case "DELETE":
                if (checkDescription() && checkTaskNum()){
                    tasks.deleteTask(Integer.parseInt(description));
                }
                break;
            case "BYE":
                isQuit = true;
                ui.bye();
                break;
            case "HELP":
                ui.printHelpInfo();
                break;
            default:
                ui.printCommandError();
                break;
            }
        }
        storage.autoSave(storage.autoSavedFile,tasks);
    }

    private static void processInput(Scanner in) {
            userInputLine = in.nextLine();
            command = processCommand();
    }


    public static Boolean checkTaskNum() {
        boolean isNumValid = true;
        try{
            int inputNum = Integer.parseInt(description);
            if (inputNum > tasks.taskCount || tasks.taskCount == 0 || inputNum == 0) {
                throw new DukeException();
            }
        }catch (NumberFormatException e) {
            isNumValid = false;
            ui.printInvalidTaskNumError();
        }catch (DukeException e) {
            isNumValid = false;
            ui.printNotSetTaskNumError();
        }
        return isNumValid;
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
            ui.showEmptyDescriptionError(command.toLowerCase());
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
            ui.showDeadlineKeywordError();
            isDescriptionValid = false;
        }catch (EmptyDescriptionException e) {
            ui.showDeadlineInputError();
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
            ui.showEventKeywordError();
            isDescriptionValid = false;
        }catch (EmptyDescriptionException e) {
            ui.showEventInputError();
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
