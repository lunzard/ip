package duke.tasks;

public class Deadline extends Task{

    protected String by;    //end time

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
