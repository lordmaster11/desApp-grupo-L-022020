package ar.edu.unq.desapp.grupol022020.services;

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class ArsatService {
	
   public static void main(String[] args) {
       try {
           URL url = new URL("http://prod.arsat.apim.junar.com/plan-federal-de-internet/v1/puntos/futuros.json/?auth_key=NzAxkj6q2uag6CX0pr9DrQxxlEDN1PBclAosN9Jn");
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		    conn.setRequestMethod("GET");

		    if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
			    throw new RuntimeException("Failed : HTTP error code : "
				    + conn.getResponseCode());
		    }

		    BufferedReader br = new BufferedReader(new InputStreamReader(
			    (conn.getInputStream())));

		    String output;
		    System.out.println("Output from Server .... ");
		    while ((output = br.readLine()) != null) {
			    System.out.println(output);
		    }

		    conn.disconnect();

	    } catch (MalformedURLException e) {
		    e.printStackTrace();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
   }
}*/
