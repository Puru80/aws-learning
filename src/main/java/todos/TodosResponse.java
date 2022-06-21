package todos;

public class TodosResponse {
    private String message;
    private Todos todo;

    public TodosResponse(String message, Todos todo) {
        this.message = message;
        this.todo = todo;
    }

    public String getMessage() {
        return message;
    }

    public Todos getTodo() {
        return todo;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTodo(Todos todo) {
        this.todo = todo;
    }

    @Override
    public String toString() {
        return "{" +
                "\"message=\" " + message + '\"' +
                ", \"todo\"= " + todo +
                "\"}";
    }
}
