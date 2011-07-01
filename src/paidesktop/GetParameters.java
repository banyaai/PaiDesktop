/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paidesktop;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.ws.WebServiceClient;
import sun.misc.BASE64Encoder;

/**
 *
 * @author banyaai
 */
public class GetParameters {

    ArrayList<Candidate> candidatesList = new ArrayList<Candidate>();
//    String server_url = "http://localhost:3000";
    String server_url = "http://pai-local.heroku.com/";
    //String server_url2 = "http://pai.herokus.com";
    String login = "user";
    String password = "K3JZGDptJmWeN";
    String encodedAuthorization;
    String votesFile = "glosowanie";

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

    public void loadParameters(String filename) throws Exception{
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String parameter = "";
            String option = "";

            while ((strLine = br.readLine()) != null) {
                parameter = strLine.split(" ")[0];
                option = strLine.split(" ")[2];

                if (parameter.startsWith("server_url")) {
                    System.out.println("server_url = " + option);
                    setServer_url(option);
                }
                if (parameter.startsWith("login")) {
                    System.out.println("login = " + option);
                    setLogin(option);
                }
                if (parameter.startsWith("password")) {
                    System.out.println("password = " + option);
                    setPassword(option);
                }
            }
            if (getServer_url().isEmpty() || getLogin().isEmpty()
                    || getPassword().isEmpty()) {
                JOptionPane.showMessageDialog(null, "B³¹d ³adowania pliku konfiguracyjnego.");
            }
            in.close();

        } catch (FileNotFoundException ex) {
            throw new Exception("Nie znaleziono pliku.");
        } catch (IOException ex) {
            throw new Exception("Blad wejscia/wyjscia.");
        } finally {
            try {
                fstream.close();
            } catch (IOException ex) {
                throw new Exception("Blad wejscia/wyjscia.");
            }
        }
    }

    public void startThread() {
        new SendResult().start();
    }

    private void encodeLoginAndPassword() {
        BASE64Encoder enc = new sun.misc.BASE64Encoder();
        String loginpassword = login + ":" + password;
        encodedAuthorization = enc.encode(loginpassword.getBytes());
    }

    public void sendResults(Candidate candidate) {
        for (int i = 0; i < candidatesList.size(); i++) {
            if (candidate.equals(candidatesList.get(i))) {
                candidatesList.get(i).increaseVotes();
            }
        }
    }

    public class SendResult extends Thread {

        Candidate candidate;

        public SendResult() {
            super("SendResult");

        }

        @Override
        public void run() {
            System.out.println("wysylka");
            SaveVotes saveVotes = new SaveVotes();
            HttpURLConnection httpCon;
            URL url;
            while (true) {
                OutputStream out = null;
                for (int i = 0; i < candidatesList.size(); i++) {
                    if (candidatesList.get(i).getVotes() > 0) {
                        try {
                            url = new URL(server_url + "/api/" + candidatesList.get(i).getId() + "?votes=" + candidatesList.get(i).getVotes());
                            System.out.println(url);
                            httpCon = (HttpURLConnection) url.openConnection();
                            httpCon.setDoOutput(true);
                            httpCon.setRequestMethod("PUT");
                            httpCon.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
                            out = httpCon.getOutputStream();
                            out.close();
                            System.out.println("otrzymano: " + httpCon.getResponseMessage());
                            System.out.println("otrzymano: " + httpCon.getResponseCode());
                            //uaktualnienie wynikow w pliku
                            saveVotes.saveVotes(votesFile, toStringVotes());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("B³¹d po³¹czenia z serwerem, nastêpna próba nast¹pi za 5minut.");
                            try {
                                this.sleep(300000);
                                continue;
                            } catch (InterruptedException ex1) {
                                Logger.getLogger(GetParameters.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        } finally {
                            if (out != null) {
                                try {
                                    out.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(GetParameters.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        candidatesList.get(i).setVotes(0);
                    }
                }
            }
        }
    };

    public String name() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServer_url() {
        return server_url;
    }

    public void setServer_url(String server_url) {
        this.server_url = server_url;
    }

    public ArrayList<Candidate> getCandidatesList() {
        return candidatesList;
    }


    public String toStringVotes() {
        String tmp = "";
        for(int i = 0; i < candidatesList.size(); i++){
            tmp= tmp + candidatesList.get(i).getName() + ", votes " + candidatesList.get(i).getAllVotes() + "\n";
        }
        return tmp;
    }
}
