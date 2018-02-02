package Chain;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
public void sendOptions(){
    String url = "http://localhost:9000/main?";
    try {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("OPTIONS");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type","application/json");
        String urlParameters = "param1=12345";
        // Send post request
        String postJsonData = "{'id':5,'countryName':'USA','population':8000}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        String reqType = con.getRequestMethod();
        System.out.println("Sending '"+reqType+"' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        System.out.println(response.toString());
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

public void sendPost(){
    String url = "http://localhost:9000/main?";
    try {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type","application/json");
        String urlParameters = "param1=12345";
        // Send post request
        String postJsonData = "{'id':5,'countryName':'USA','population':8000}";
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        String reqType = con.getRequestMethod();
        System.out.println("Sending '"+reqType+"' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        System.out.println(response.toString());
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
    public static void main(String[] args){
        Test t = new Test();
        t.sendOptions();
       // t.sendPost();
    }
}
