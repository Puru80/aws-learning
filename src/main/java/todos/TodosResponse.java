package todos;

public class TodosResponse {
    private String message;
    private String todoId;

    public TodosResponse(String message, String todoId) {
        this.message = message;
        this.todoId = todoId;
    }

    public String getMessage() {
        return message;
    }

    public String getTodoId() {
        return todoId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"message=\" " + message + '\"' +
                ", \"todo\"= " + todoId +
                "\"}";
    }
}
