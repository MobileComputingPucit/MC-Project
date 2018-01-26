/*
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

*/
/**
 * Created by Speedy on 13/01/2018.
 *//*

public class HTTPURLConnectionHelper {

    // In this method do 2 works, first convert signup object into JSON object
    //and 2nd send the POST call to web api using url;
    public static String POST(String url, Signup su)
    {
        try {
            //build the connection
            URL obj = new URL("https:/google.com");
            HttpURLConnection connection =(HttpURLConnection)obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);  // send post request
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            String json = "";

            //build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("Account_id", su.getAccount_id());
            jsonObject.accumulate("Email", su.getEmail());
            jsonObject.accumulate("Username", su.getUsername());
            jsonObject.accumulate("Password", su.getPassword());
            jsonObject.accumulate("Phone_no", su.getPhone_no());
            jsonObject.accumulate("Gender", su.getGender());
            jsonObject.accumulate("Date_of_birth", su.getDate_of_Birth());
            jsonObject.accumulate("Entry_date", su.getEntry_date());

           // Writer writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
           // writer.write(String.valueOf(jsonObject));
// json data
           // writer.close();
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(String.valueOf(jsonObject));
            wr.flush();
            wr.close();

            return "abc";
        }
        catch(Exception e) {
            return "abc";
        }
    }

}
*/
