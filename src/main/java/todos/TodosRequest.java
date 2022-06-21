package todos;

public class TodosRequest {
    private String email;
    private String task;
    private String description;

    public TodosRequest(String email, String task, String description) {
        this.email = email;
        this.task = task;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public String getTask() {
        return task;
    }

    public String getDescription() {
        return description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
