package duke;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidKeywordException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;


import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;
    private static String userInputLine;
    private static String command;  
    private static String description;
    private static String taskName;
    private static String taskTime;
    private static Ui ui;

    public static void main(String[] args) throws IOException {
        ui = new Ui();

        //auto load
        File autoSavedFileDir = new File("data");
        File autoSavedFile = new File("data/duke.txt");
        ui.showLoadingProcess();
        if(checkFileExistence(autoSavedFileDir,autoSavedFile)){
            ui.showLoadingFinished();
        }
        else {
            ui.showNewFileDetected();
        }

        Scanner in = new Scanner(System.in);
        boolean isQuit = false;

        //when the program starts
        ui.showLogo();
        ui.greet();

        while(!isQuit) {
            processInput(in);

            switch (command.toUpperCase()) {
            case "LIST":
                listTasks();
                break;
            case "DONE":
                if(checkDescription() && checkTaskNum()) {
                    doneTask(Integer.parseInt(description));
                }
                break;
            case "TODO":
                if(checkDescription()) {
                    addTask(new ToDo(description));
                }
                break;
            case "DEADLINE":
                if(checkDescription() && checkDeadline()) {
                    addTask(new Deadline(taskName,taskTime));
                }
                break;
            case "EVENT":
                if(checkDescription() && checkEvent()) {
                    addTask(new Event(taskName, taskTime));
                }
                break;
            case "DELETE":
                if (checkDescription() && checkTaskNum()){
                    deleteTask(Integer.parseInt(description));
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
        autoSave(autoSavedFile);
    }

    private static void processInput(Scanner in) {
            userInputLine = in.nextLine();
            command = processCommand();
    }

    public static void addTask(Task t) {
        tasks.add(t);
        taskCount = tasks.size();
        ui.showAddTaskSuccessful(t.toString(), taskCount);
    }

    public static void addTaskSilent(Task t) {
        tasks.add(t);
        taskCount = tasks.size();
    }

    public static void listTasks() {
        ui.sayShowTaskList();
        for (int i = 0; i< taskCount; i++) {
            System.out.println((i+1)+"."+tasks.get(i).toString());
        }
        ui.showSplitter();
    }

    public static Boolean checkTaskNum() {
        boolean isNumValid = true;
        try{
            int inputNum = Integer.parseInt(description);
            if (inputNum > taskCount || taskCount == 0 || inputNum == 0) {
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
    public static void doneTask(int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
        ui.showTaskDone(tasks.get(taskIndex - 1).toString());
    }

    public static void doneTaskSilent(int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
    }

    public static void deleteTask(int taskIndex) {
        ui.showTaskDeleting(tasks.get(taskIndex - 1).toString());
        tasks.remove(taskIndex - 1);
        taskCount = tasks.size();
        ui.showTaskNum(taskCount);
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

    public static boolean checkFileExistence(File dirName,File fileName){
        boolean isExist = false;
        try{
            autoLoad(fileName);
            isExist = true;
        }catch(FileNotFoundException e){
            if(fileName.isDirectory()) {
                ui.showMissingFile();
            }
            else{
                ui.showMissingFolder();
                createDirectory(dirName);
            }
            createSavedFile(fileName);
        }
        return isExist;
    }
    public static void autoLoad(File fileName) throws FileNotFoundException {
        if(fileName.exists()){
            Scanner rs= new Scanner(fileName);
            while(rs.hasNext()){
                String record = rs.nextLine();
                processRecord(record);
            }
        }
        else {
            throw new FileNotFoundException();
        }
    }
    public static void autoSave(File fileName) throws IOException{
        String filePath = fileName.getPath();
        new FileWriter(fileName, false).close();
        FileWriter fw = new FileWriter(filePath,true);
        for(int i = 0; i< tasks.size(); i++){
            fw.write(tasks.get(i).saveAsText() + System.lineSeparator());
        }
        fw.close();
    }

    public static void createDirectory(File dir){
        boolean isCreated = dir.mkdirs();
        if(isCreated){
            ui.showDirCreatedSucceed();
        }
        else{
            ui.showDirCreatedFail();
        }
    }

    public static void createSavedFile(File file){
        try {
            file.createNewFile();
        }catch (IOException e){
            ui.showFileCreatedFail();
        }
    }

    public static void processRecord(String record){
        String[] recordInfos = record.split(" \\| ");
        switch (recordInfos[0]){
        case "T":
            addTaskSilent(new ToDo(recordInfos[2]));
            break;
        case "D":
            addTaskSilent(new Deadline(recordInfos[2], recordInfos[3]));
            break;
        case "E":
            addTaskSilent(new Event(recordInfos[2], recordInfos[3]));
            break;
        default:
            break;
        }
        if(recordInfos[1].equals("1")) {
            doneTaskSilent(tasks.size());
        }
    }

}
