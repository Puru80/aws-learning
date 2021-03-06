package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import helper.SNSHelper;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import todos.TodosFunctions;
import todos.TodosRequest;
import todos.TodosResponse;

//Handler to add TodoItem from SQS Trigger
public class SQSHandler implements RequestHandler<SQSEvent, String> {

    @Override
    public String handleRequest(SQSEvent sqsEvent, Context context) {
        JSONArray arr = new JSONArray();

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
                responseJson.put("todoId", todosResponse.getTodoId());

                arr.put(responseJson);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //Publishing message to SNS Topic
        SnsClient client = SnsClient.builder().
                region(Region.AP_SOUTH_1)
                .build();

        SNSHelper helper = new SNSHelper();
        helper.pubTopic(client, arr.toString(),
                "arn:aws:sns:ap-south-1:033211574546:todoNotificationTopic");

        return null;
    }
}