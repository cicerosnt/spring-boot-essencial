package br.cicerosnt.project3.javaclient;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JavaClientTest {

    public static void main(String[] args) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String user = "cicerosnt", pass = "0ias";

        try {
              URL url = new URL("http://localhost:8080/v1/protected/students/findById/30");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", "Basic "+encodeUserNamePassword(user,pass));
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonSB = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                jsonSB.append(line);
            }
            System.out.println(jsonSB.toString());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            IOUtils.closeQuietly(reader);
            if (connection!=null){
                connection.disconnect();
            }
        }
    }

    private static String encodeUserNamePassword(String user, String password){
        String userPass = user+":"+password;

        return new String(Base64.encodeBase64(userPass.getBytes()));
    }
}
