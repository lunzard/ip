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

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" +this.getStatusIcon() +"]"+ " " + this.getName();
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}
