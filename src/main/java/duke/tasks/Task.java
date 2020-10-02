package duke.tasks;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }


    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * print entire information of task in string.
     *
     * @return full info of task
     */
    @Override
    public String toString(){
        return "[" +this.getStatusIcon() +"]"+ " " + this.getName();
    }

    /**
     * get tick or cross depending on isDone status of the task.
     *
     * @return icon of tick or cross
     */
    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * convert task as simplified text in autoSaved file
     *
     * @return
     */
    public String saveAsText(){
        return "T | "+ ((this.isDone)? "1" : "0")+ " | " + this.name;
    }
}
