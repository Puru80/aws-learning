package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import todos.TodosFunctions;
import todos.TodosRequest;
import todos.TodosResponse;

import java.io.*;

public class TodosHandler implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream,
                              Context context) throws IOException {
        /*JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONObject responseJson = new JSONObject();
        TodosFunctions functions = new TodosFunctions();

        try {
            JSONObject event = (JSONObject) parser.parse(reader);
            TodosResponse todosResponse = functions.addTodo(new TodosRequest(
                    event.get("email").toString(),
                    event.get("task").toString(),
                    event.get("description").toString()
            ));

            responseJson.put("message", todosResponse.getMessage());
            responseJson.put("body", todosResponse.getTodo().toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseJson.toString());
        writer.close();*/
    }
}
