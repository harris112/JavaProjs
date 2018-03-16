package ToDoList;

/**
 * Created by u1774154 on 25/01/2018.
 */
public class Job {

    private String Description;
    private int Priority;
    private boolean Completion;

    public boolean isCompletion() {
        return Completion;
    }

    public void setCompletion(boolean completion) {
        Completion = completion;
    }

    public int getPriority() {

        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public String getDescription() {

        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
