package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class APIGatewayHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    /*@Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        System.out.println("Hi ! ! ! ! I am invoked by APIGateway");
    }*/

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
        APIGatewayProxyResponseEvent event = new APIGatewayProxyResponseEvent();

        event.setStatusCode(200);
        event.setBody(apiGatewayProxyRequestEvent.getBody());
        event.setHeaders(null);
        event.setIsBase64Encoded(true);

        return event;
    }
}
