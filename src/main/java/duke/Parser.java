package duke;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidKeywordException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.util.Scanner;

public class Parser {
    Ui ui = new Ui();
    String userInputLine;
    String command;
    String description;
    String taskName;
    String taskTime;

    /**
     * Get command text from the user input.
     * @param in userInput in Scanner.
     */
    public void processInput(Scanner in) {
        userInputLine = in.nextLine();
        command = processCommand(userInputLine);
    }

    /**
     * Split the first part of userInput.
     * Get command text.
     * @param userInputLine userInput in String.
     * @return command text.
     */
    public String processCommand(String userInputLine) {
        return userInputLine.split(" ")[0];
    }
    /**
     * Execute according to the command.
     * @param command command type.
     * @param tasks Arraylist of tasks.
     */
    public void respondToCommand(String command, TaskList tasks) {
        switch (command.toUpperCase()) {
        case "LIST":
            tasks.listTasks();
            break;
        case "DONE":
            if(checkDescription(command) && checkTaskNum(tasks)) {
                tasks.doneTask(Integer.parseInt(description));
            }
            break;
        case "TODO":
            if(checkDescription(command)) {
                tasks.addTask(new ToDo(description));
            }
            break;
        case "DEADLINE":
            if(checkDescription(command) && checkDeadline()) {
                tasks.addTask(new Deadline(taskName,taskTime));
            }
            break;
        case "EVENT":
            if(checkDescription(command) && checkEvent()) {
                tasks.addTask(new Event(taskName, taskTime));
            }
            break;
        case "DELETE":
            if (checkDescription(command) && checkTaskNum(tasks)){
                tasks.deleteTask(Integer.parseInt(description));
            }
            break;
        case "BYE":
            Duke.isQuit = true;
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
    public boolean checkDescription(String command) {
        boolean isDescriptionExist = true;
        try {
            description = processDescription(command);
        }
        catch (EmptyDescriptionException e) {
            ui.showEmptyDescriptionError(command.toLowerCase());
            isDescriptionExist = false;
        }
        return isDescriptionExist;
    }
    public String processDescription(String command) throws EmptyDescriptionException {
        description = userInputLine.replace(command, " ").trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        return description;
    }
    public Boolean checkTaskNum(TaskList tasks) {
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
    public boolean checkDeadline() {
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

    public void processDeadline(String description) throws InvalidKeywordException,EmptyDescriptionException {
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
    public boolean checkEvent(){
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
    public void processEvent(String description) throws InvalidKeywordException,EmptyDescriptionException {
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
