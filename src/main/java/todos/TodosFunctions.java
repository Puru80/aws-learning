package todos;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import user.User;

import java.util.List;
import java.util.UUID;

public class TodosFunctions {

    private final AmazonDynamoDB client;
//            AmazonDynamoDBClientBuilder.standard().build();

    public TodosFunctions() {
        this.client = AmazonDynamoDBClientBuilder.standard().build();;
    }

    /*public static void main(String[] args) {
        System.out.println(addTodo(new TodosRequest(
                "puru.agar99@gmail.com",
                "Code on Sunday",
                "Code the AWS task assigned"
        )));

        System.out.println(completeTodo("e577dc04-0bda-4268-b0b6-7c07e9c6406f"));

        List<Todos> list = listTodos("puru.agar99@gmail.com");

        Gson gson = new Gson();
        String jsonList = gson.toJson(list);
        System.out.println(jsonList);
    }*/

    public TodosResponse addTodo(TodosRequest todosRequest){
        try{
            DynamoDBMapper mapper = new DynamoDBMapper(client);
            Todos todo = new Todos();

            User user = mapper.load(User.class, todosRequest.getEmail());
            if(user==null){
                return new TodosResponse("User Not Found. Please register to add todos.",
                        null);
            }
            else{
                todo.setId(UUID.randomUUID().toString());
                todo.setEmail(todosRequest.getEmail());
                todo.setTask(todosRequest.getTask());
                todo.setDescription(todosRequest.getDescription());
                todo.setCompleted(false);

                mapper.save(todo);

                return new TodosResponse("Todo Added Successfully", todo);
            }

        }catch (AmazonDynamoDBException e){
            e.printStackTrace();
            return new TodosResponse("Some Error Occurred. Please try again.", null);
        }
    }

    public String completeTodo(String todoId){
        try{
            DynamoDBMapper mapper = new DynamoDBMapper(client);
            Todos todo = mapper.load(Todos.class, todoId);

            if(todo == null){
                return "Todo doesn't exist";
            }
            else{
                todo.setCompleted(true);
                mapper.save(todo);

                return "Todo completed";
            }

        }catch (AmazonDynamoDBException e){
            e.printStackTrace();
            return "Error Occurred. Please try again";
        }
    }

    public List<Todos> listTodos(String email){
        try{
            DynamoDBMapper mapper = new DynamoDBMapper(client);
            Todos todo = new Todos();

            User user = mapper.load(User.class, email);
            if(user==null){
                return null;
            }
            else{

                return mapper.scan(Todos.class, new DynamoDBScanExpression());
            }

        }catch (AmazonDynamoDBException e){
            e.printStackTrace();
            return null;
        }
    }

}
