package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import junit.framework.Test;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import todos.TodosFunctions;
import todos.TodosRequest;
import todos.TodosResponse;

public class SQSHandler implements RequestHandler<SQSEvent, String> {

    @Override
    public String handleRequest(SQSEvent sqsEvent, Context context) {
        for(SQSEvent.SQSMessage msg : sqsEvent.getRecords()){
            String message = msg.getBody();

            System.out.println(message);

            JSONParser parser = new JSONParser();
            JSONObject responseJson = new JSONObject();
            TodosFunctions functions = new TodosFunctions();

            try {
                JSONObject event = (JSONObject) parser.parse(message);
                TodosResponse todosResponse = functions.addTodo(new TodosRequest(
                        event.get("email").toString(),
                        event.get("task").toString(),
                        event.get("description").toString()
                ));

                responseJson.put("message", todosResponse.getMessage());
                responseJson.put("body", parser.parse(todosResponse.getTodo().toString()));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        SnsClient client = SnsClient.builder().
                region(Region.AP_SOUTH_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();



        return null;
    }
}