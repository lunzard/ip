package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private Ui ui;

    String autoSavedFolderName;
    String autoSavedFileName;
    File autoSavedFileDir;
    File autoSavedFile;

    public Storage() {
        ui = new Ui();
        this.autoSavedFolderName = "data";
        this.autoSavedFileName = "data/duke.txt";
        this.autoSavedFileDir = new File(autoSavedFolderName);
        this.autoSavedFile = new File(autoSavedFileName);
    }

    public void init(TaskList tasks){
        ui.showLoadingProcess();
        if(checkFileExistence(autoSavedFileDir,autoSavedFile, tasks)){
            ui.showLoadingFinished();
        }
        else {
            ui.showNewFileDetected();
        }
    }


    public boolean checkFileExistence(File dirName,File fileName, TaskList tasks){
        boolean isExist = false;
        try{
            autoLoad(fileName, tasks);
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

    public void autoLoad(File fileName,TaskList tasks) throws FileNotFoundException {
        if(fileName.exists()){
            Scanner rs= new Scanner(fileName);
            while(rs.hasNext()){
                String record = rs.nextLine();
                processRecord(record, tasks);
            }
        }
        else {
            throw new FileNotFoundException();
        }
    }
    public void autoSave(File fileName, TaskList tasks) throws IOException{
        String filePath = fileName.getPath();
        new FileWriter(fileName, false).close();
        FileWriter fw = new FileWriter(filePath,true);
        for(int i = 0; i< tasks.tasks.size(); i++){
            fw.write(tasks.tasks.get(i).saveAsText() + System.lineSeparator());
        }
        fw.close();
    }
    public void createDirectory(File dir){
        boolean isCreated = dir.mkdirs();
        if(isCreated){
            ui.showDirCreatedSucceed();
        }
        else{
            ui.showDirCreatedFail();
        }
    }

    public void createSavedFile(File file){
        try {
            file.createNewFile();
        }catch (IOException e){
            ui.showFileCreatedFail();
        }
    }

    public void processRecord(String record, TaskList tasks){
        String[] recordInfos = record.split(" \\| ");
        switch (recordInfos[0]){
        case "T":
            tasks.addTaskSilent(new ToDo(recordInfos[2]));
            break;
        case "D":
            tasks.addTaskSilent(new Deadline(recordInfos[2], recordInfos[3]));
            break;
        case "E":
            tasks.addTaskSilent(new Event(recordInfos[2], recordInfos[3]));
            break;
        default:
            break;
        }
        if(recordInfos[1].equals("1")) {
            tasks.doneTaskSilent(tasks.tasks.size());
        }
    }
}
