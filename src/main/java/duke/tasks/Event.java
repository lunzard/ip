package duke.tasks;

public class Event extends Task {

    protected String at;    //start and end time

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String saveAsText(){
        return "E | "+ ((this.isDone)? "1" : "0")+ " | " + this.name + " | " + this.at;
    }
}
