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


    /**
     * Add new task to the taskList(Arraylist).
     * @param t new task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
        taskCount = tasks.size();
        ui.showAddTaskSuccessful(t.toString(), taskCount);
    }

    /**
     * Add new task to the taskList(Arraylist) without any UI displayed.
     * Usually used for Auto-Load and Auto-Save.
     * @param t new task to be added.
     */
    public void addTaskSilent(Task t) {
        tasks.add(t);
        taskCount = tasks.size();
    }


    /**
     * Finished task by marking a tick.
     * @param taskIndex the index of task done out of the taskList.
     */
    public static void doneTask(int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
        ui.showTaskDone(tasks.get(taskIndex - 1).toString());
    }
    /**
     * Finished task by marking a tick, without any UI displayed.
     * Usually used for Auto-Load and Auto-Save.
     * @param taskIndex the index of task done out of the taskList.
     */
    public static void doneTaskSilent(int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
    }

    /**
     * Print all tasks in taskList in a column.
     */
    public void listTasks() {
        ui.sayShowTaskList();
        for (int i = 0; i< taskCount; i++) {
            System.out.println((i+1)+"."+tasks.get(i).toString());
        }
        ui.showSplitter();
    }

    /**
     * Remove task of given index from the taskList, and update the List.
     * @param taskIndex he index of task removed out of the taskList.
     */
    public void deleteTask(int taskIndex) {
        ui.showTaskDeleting(tasks.get(taskIndex - 1).toString());
        tasks.remove(taskIndex - 1);
        taskCount = tasks.size();
        ui.showTaskNum(taskCount);
    }

    public void findMatchingTasks(String keyWord){
        ui.showMatchingTasks();
        for (int i = 0; i< taskCount; i++) {
            if(tasks.get(i).toString().contains(keyWord)){
                System.out.println((i+1)+"."+tasks.get(i).toString());
            }
        }
        ui.showSplitter();
    }
}
