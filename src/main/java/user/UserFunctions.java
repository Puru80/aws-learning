package user;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;

public class UserFunctions {
    private final AmazonDynamoDB client ;

    public UserFunctions() {
        this.client = AmazonDynamoDBClientBuilder.standard().build();
    }

    //    Test Function
    /*public static void main(String[] args) {
        System.out.println(registerUser(new UserRequest("user3@gmail.com",
                "password")));

        System.out.println(userLogin(new UserRequest("puru.agar99@gmail.com",
                "password")));
    }*/

    public String registerUser(UserRequest userRequest){

        try{
            DynamoDBMapper mapper = new DynamoDBMapper(client);

            User user = mapper.load(User.class, userRequest.getEmail());
            if(user==null){
                user = new User(userRequest.getEmail(), userRequest.getPassword());
                user.setLoggedIn(false);
            }
            else{
                return "User already exists";
            }

            mapper.save(user);
            return "User Registered";

        }catch (AmazonDynamoDBException e){
            e.printStackTrace();
            return "Error Occurred. Please try again";
        }
    }

    public String userLogin(UserRequest userRequest){

        try{
            DynamoDBMapper mapper = new DynamoDBMapper(client);

            User user = mapper.load(User.class, userRequest.getEmail());
            if(user==null){
                return "User does not exist";
            }
            else{
                if(user.getPassword().equals(userRequest.getPassword())) {
                    user.setLoggedIn(true);
                    mapper.save(user);
                    return "User login successful";
                }
                else
                    return "Incorrect Password, Please try again";
            }

        }catch (AmazonDynamoDBException e){
            e.printStackTrace();
            return "Error Occurred. Please try again";
        }
    }

}
