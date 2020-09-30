package duke.tasks;

import duke.Ui;

import java.util.ArrayList;

public class TaskList {
    private static Ui ui;

    public static ArrayList<Task> tasks;
    public static int taskCount;

    public TaskList() {
        ui = new Ui();
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    public void addTask(Task t) {
        tasks.add(t);
        taskCount = tasks.size();
        ui.showAddTaskSuccessful(t.toString(), taskCount);
    }
    public void addTaskSilent(Task t) {
        tasks.add(t);
        taskCount = tasks.size();
    }

    public void doneTask(int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
        ui.showTaskDone(tasks.get(taskIndex - 1).toString());
    }

    public void doneTaskSilent(int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
    }

    public void listTasks() {
        ui.sayShowTaskList();
        for (int i = 0; i< taskCount; i++) {
            System.out.println((i+1)+"."+tasks.get(i).toString());
        }
        ui.showSplitter();
    }
    public void deleteTask(int taskIndex) {
        ui.showTaskDeleting(tasks.get(taskIndex - 1).toString());
        tasks.remove(taskIndex - 1);
        taskCount = tasks.size();
        ui.showTaskNum(taskCount);
    }

    public void findMatchingTasks(String keyWord){
        ui.showMatchingTasks();
        for (int i = 0; i< taskCount; i++) {
            if(tasks.get(i).toString().indexOf(keyWord) >= 0){
                System.out.println((i+1)+"."+tasks.get(i).toString());
            }
        }
        ui.showSplitter();
    }
}
