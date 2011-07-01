/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paidesktop;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.WebServiceClient;
import sun.misc.BASE64Encoder;

/**
 *
 * @author banyaai
 */
public class GetParameters implements WebServiceClient {

    ArrayList<Candidate> candidatesList = new ArrayList<Candidate>();
//    String server_url = "http://localhost:3000";
    String server_url = "http://pai.heroku.com";
    String login = "user";
    String password = "K3JZGDptJmWeN";
    String encodedAuthorization;

    public GetParameters() {
        encodeLoginAndPassword();
        URL url;
        try {
            url = new URL(server_url + "/api");
            URLConnection httpCon = url.openConnection();
            httpCon.setRequestProperty("Accept", "application/json");
            httpCon.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
            BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            String inputLine;
            String result = new String();

            while ((inputLine = in.readLine()) != null) {
                result += inputLine;
            }
            in.close();

            // Parse Json to candidates array
            Gson gson = new Gson();
            Candidate[] candidates = gson.fromJson(result, Candidate[].class);
            for (int i = 0; i < candidates.length; i++) {
                candidatesList.add(candidates[i]);
            }
        } catch (Exception ex) {
            Logger.getLogger(GetParameters.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void encodeLoginAndPassword() {
        BASE64Encoder enc = new sun.misc.BASE64Encoder();
        String loginpassword = login + ":" + password;
        encodedAuthorization = enc.encode(loginpassword.getBytes());
    }

    public void sendResults(Candidate candidate) {
        System.out.println("wysylka");
        HttpURLConnection httpCon;
        OutputStream out = null;
        URL url;
        try {
            url = new URL(server_url + "/api/" + candidate.getId() + "?votes=" + 1 /*candidate.getVotes()*/);
            System.out.println(url);
            httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            httpCon.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
            out = httpCon.getOutputStream();
            out.close();
            System.out.println("otrzymano: " + httpCon.getResponseMessage());
            System.out.println("otrzymano: " + httpCon.getResponseCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(GetParameters.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String name() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String targetNamespace() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String wsdlLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Class<? extends Annotation> annotationType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<Candidate> getCandidatesList() {
        return candidatesList;
    }
}
