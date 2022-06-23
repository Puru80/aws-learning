import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import helper.SNSHelper;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

public class Test {
    /*public static void main(String[] args) {
        SNSHelper helper = new SNSHelper();

        SnsClient client = SnsClient.builder().
                region(Region.AP_SOUTH_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

        helper.pubTopic(client, "Hello World! ! ! !",
                "arn:aws:sns:ap-south-1:033211574546:todoNotificationTopic");
    }*/

    /*public static String createSNSTopic(SnsClient snsClient, String topicName) {

        CreateTopicResponse result = null;

        try {
            CreateTopicRequest request = CreateTopicRequest.builder()
                    .name(topicName)
                    .build();

            result = snsClient.createTopic(request);
            return result.topicArn();
        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }

        return "";
    }*/

    /*public static void pubTopic(SnsClient snsClient, String message, String topicArn) {

        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .topicArn(topicArn)
                    .build();

            PublishResponse result = snsClient.publish(request);
            System.out.println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }*/
}
