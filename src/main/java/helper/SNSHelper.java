package helper;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

public class SNSHelper {

//    ARN: arn:aws:sns:ap-south-1:033211574546:todoNotificationTopic

    public String pubTopic(SnsClient snsClient, String message, String topicArn) {
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .topicArn(topicArn)
                    .build();

            PublishResponse result = snsClient.publish(request);
//            System.out.println(result.messageId() + " Message sent. Status is " +
//                    result.sdkHttpResponse().statusCode());

            return result.messageId();

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }

        return "Some Error Occurred. Please try again";
    }
}
