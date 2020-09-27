package duke.tasks;

public class ToDo extends Task{

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    @Override
    public String saveAsText(){
        return "T | "+ ((this.isDone)? "1" : "0")+ " | " + this.name;
    }
}
