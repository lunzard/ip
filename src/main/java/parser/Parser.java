package parser;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidKeywordException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Parser {
    Ui ui = new Ui();
    public String userInputLine;
    public String command;
    String description;
    String taskName;
    String taskTime;
    String formattedTime;
    String formattedDate;
    LocalDate standardDate;

    /**
     * Get command text from the user input.
     *
     * @param in userInput in Scanner
     */
    public void processInput(Scanner in) {
        userInputLine = in.nextLine();
        command = processCommand(userInputLine);
    }

    /**
     * Split the first part of userInput.
     * Get command text.
     *
     * @param userInputLine userInput in String
     * @return command text
     */
    public String processCommand(String userInputLine) {
        return userInputLine.split(" ")[0];
    }

    /**
     * Execute according to the command.
     *
     * @param command command type
     * @param tasks Arraylist of tasks
     */
    public void respondToCommand(String command, TaskList tasks) {
        switch (command.toUpperCase()) {
        case "LIST":
            tasks.listTasks();
            break;
        case "DONE":
            if (checkDescription(command) && checkTaskNum(tasks)) {
                tasks.doneTask(Integer.parseInt(description));
            }
            break;
        case "TODO":
            if (checkDescription(command)) {
                tasks.addTask(new ToDo(description));
            }
            break;
        case "DEADLINE":
            if (checkDescription(command) && checkDeadline()) {
                tasks.addTask(new Deadline(taskName,taskTime));
            }
            break;
        case "EVENT":
            if (checkDescription(command) && checkEvent()) {
                tasks.addTask(new Event(taskName, taskTime));
            }
            break;
        case "DELETE":
            if (checkDescription(command) && checkTaskNum(tasks)) {
                tasks.deleteTask(Integer.parseInt(description));
            }
            break;
        case "FIND":
            if (checkDescription(command)) {
                tasks.findMatchingTasks(description);
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

    /**
     * Check Existence of the description of the command.
     * @param command the command type
     * @return TRUE if description exists;
     * FALSE if description does not exist
     */
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

    /**
     * Extract command description from userInput.
     * @param command the command type
     * @return command description in String
     */
    public String processDescription(String command) throws EmptyDescriptionException {
        description = userInputLine.replace(command, " ").trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        return description;
    }

    /**
     * Check validity of selected task number text of DONE command.
     * @param tasks the TaskList object instantiated when DUKE runs
     * @return TRUE if task number text is valid
     * @return FALSE if task number text is
     * invalid, cannot be converted to int, or out of bound
     */
    public Boolean checkTaskNum(TaskList tasks) {
        boolean isNumValid = true;
        try {
            int inputNum = Integer.parseInt(description);
            if (inputNum > tasks.taskCount || tasks.taskCount == 0 || inputNum == 0) {
                throw new DukeException();
            }
        } catch (NumberFormatException e) {
            isNumValid = false;
            ui.printInvalidTaskNumError();
        } catch (DukeException e) {
            isNumValid = false;
            ui.printNotSetTaskNumError();
        }
        return isNumValid;
    }

    /**
     * Check validity of the DEADLINE command's description.
     * @return TRUE if description is valid
     * @return FALSE if description
     * has invalid keyword, or is empty
     */
    public boolean checkDeadline() {
        boolean isDescriptionValid = true;
        try {
            processDeadline(description);
        } catch (InvalidKeywordException e) {
            ui.showDeadlineKeywordError();
            isDescriptionValid = false;
        } catch (EmptyDescriptionException e) {
            ui.showDeadlineInputError();
            isDescriptionValid = false;
        }
        return isDescriptionValid;
    }

    /**
     * Get both taskName and taskTime from the DEADLINE command's description.
     * @param description content of the DEADLINE command
     */
    public void processDeadline(String description)
            throws InvalidKeywordException,EmptyDescriptionException {
        try {
            taskName = description.substring(0, description.indexOf("/by")).trim();
            taskTime = description.substring(description.indexOf("/by") + 3).trim();
            if (checkDateAndTimeFormat()) {
                taskTime = formattedDate + ", " + formattedTime;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidKeywordException();
        }
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        else if (taskTime.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Check validity of the EVENT command's description.
     * @return TRUE if description is valid
     * @return FALSE if description
     * has invalid keyword, or is empty
     */
    public boolean checkEvent() {
        boolean isDescriptionValid = true;
        try {
            processEvent(description);
        } catch (InvalidKeywordException e) {
            ui.showEventKeywordError();
            isDescriptionValid = false;
        } catch (EmptyDescriptionException e) {
            ui.showEventInputError();
            isDescriptionValid = false;
        }
        return isDescriptionValid;
    }

    /**
     * Get both taskName and taskTime from the EVENT command's description.
     * @param description content of the EVENT command
     */
    public void processEvent(String description)
            throws InvalidKeywordException,EmptyDescriptionException {
        try {
            taskName = description.substring(0, description.indexOf("/at")).trim();
            taskTime = description.substring(description.indexOf("/at") + 3).trim();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidKeywordException();
        }
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        else if (taskTime.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Get both taskName and taskTime from the DEADLINE command's description.
     *
     * @return TRUE if both Date and Time are in correct format
     * and are successfully converted to standardDate type;
     * False if any part of them is in wrong format
     */
    public boolean checkDateAndTimeFormat() {
        boolean isFormative = false;
        boolean isDateFormative = false;
        boolean isTimeFormative = false;
        if (!taskTime.contains(" ")) {
            return isFormative;
        }
        String unformattedDate = taskTime.split(" ")[0];
        String unformattedTime = taskTime.split(" ")[1];
        String[] dates = unformattedDate.split("/");
        if (Integer.parseInt(dates[0]) <= 31
                && Integer.parseInt(dates[1]) <= 12
                && Integer.parseInt(dates[2]) <= 2050
                && Integer.parseInt(dates[2]) >= 1950) {
            standardDate = LocalDate.parse(dates[2] + "-" + dates[1] + "-"
                    + (((Integer.parseInt(dates[0])) < 10) ? ("0" + dates[0]) : dates[0]));
            isDateFormative = true;
        }
        if (unformattedTime.length() == 4
                && Integer.parseInt(unformattedTime.substring(0, 2)) <= 24
                && Integer.parseInt(unformattedTime.substring(2, 4)) <= 59) {
            formattedTime = unformattedTime;
            isTimeFormative = true;
        }
        if (isDateFormative && isTimeFormative) {
            isFormative = true;
            formattedDate = standardDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return  isFormative;
    }

}
