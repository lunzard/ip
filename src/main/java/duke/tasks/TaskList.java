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

    public static void addTask(Task t) {
        tasks.add(t);
        taskCount = tasks.size();
        ui.showAddTaskSuccessful(t.toString(), taskCount);
    }
    public static void addTaskSilent(Task t) {
        tasks.add(t);
        taskCount = tasks.size();
    }

    public static void doneTask(int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
        ui.showTaskDone(tasks.get(taskIndex - 1).toString());
    }

    public static void doneTaskSilent(int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
    }

    public static void listTasks() {
        ui.sayShowTaskList();
        for (int i = 0; i< taskCount; i++) {
            System.out.println((i+1)+"."+tasks.get(i).toString());
        }
        ui.showSplitter();
    }
    public static void deleteTask(int taskIndex) {
        ui.showTaskDeleting(tasks.get(taskIndex - 1).toString());
        tasks.remove(taskIndex - 1);
        taskCount = tasks.size();
        ui.showTaskNum(taskCount);
    }
}
