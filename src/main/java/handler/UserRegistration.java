package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import user.UserFunctions;
import user.UserRequest;

import java.io.*;

public class UserRegistration implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream,
                              Context context) throws IOException {
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONObject responseJson = new JSONObject();
        UserFunctions mapping = new UserFunctions();

        try {
            JSONObject event = (JSONObject) parser.parse(reader);
            String message = mapping.registerUser(new UserRequest(event.get("email").toString(),
                    event.get("password").toString()));

            JSONObject responseBody = new JSONObject();
            responseBody.put("message", message);

            responseJson.put("body", responseBody.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseJson.toString());
        writer.close();
    }
}
