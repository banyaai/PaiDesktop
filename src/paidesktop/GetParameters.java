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
public class GetParameters{

    ArrayList<Candidate> candidatesList = new ArrayList<Candidate>();
//    String server_url = "http://localhost:3000";
    String server_url = "http://pai-local.heroku.com/";
    //String server_url2 = "http://pai.herokus.com";
    String login = "user";
    String password = "K3JZGDptJmWeN";
    String encodedAuthorization;

    public GetParameters() {
        ReadWriteFile readWriteFile = new ReadWriteFile();
//        this.setServer_url(readWriteFile.getGetParameters().getServer_url());
//        this.setLogin(readWriteFile.getGetParameters().getLogin());
//        this.setPassword(readWriteFile.getGetParameters().getPassword());
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
                System.out.println("glosy " + candidatesList.get(i).getVotes());
            }
        }

//        System.out.println("wysylka");
//        HttpURLConnection httpCon;
//        OutputStream out = null;
//        URL url;
//
//        try {
//            url = new URL(server_url2 + "/api/" + candidate.getId() + "?votes=" + 1 /*candidate.getVotes()*/);
//            System.out.println(url);
//            httpCon = (HttpURLConnection) url.openConnection();
//            httpCon.setDoOutput(true);
//            httpCon.setRequestMethod("PUT");
//            httpCon.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
//            out = httpCon.getOutputStream();
//            out.close();
//            System.out.println("otrzymano: " + httpCon.getResponseMessage());
//            System.out.println("otrzymano: " + httpCon.getResponseCode());
//        } catch (Exception ex) {
//            // ex.printStackTrace();
//            System.out.println("B³¹d po³¹czenia z serwerem, nastêpna próba nast¹pi za 5minut.");
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(GetParameters.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }

    }

    public class SendResult extends Thread {

        Candidate candidate;

        public SendResult() {
            super("SendResult");

        }

        @Override
        public void run() {
            System.out.println("wysylka");
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
//            while (notSend) {
//                try {
//                    url = new URL(server_url2 + "/api/" + candidate.getId() + "?votes=" + 1 /*candidate.getVotes()*/);
//                    System.out.println(url);
//                    httpCon = (HttpURLConnection) url.openConnection();
//                    httpCon.setDoOutput(true);
//                    httpCon.setRequestMethod("PUT");
//                    httpCon.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
//                    out = httpCon.getOutputStream();
//                    out.close();
//                    System.out.println("otrzymano: " + httpCon.getResponseMessage());
//                    System.out.println("otrzymano: " + httpCon.getResponseCode());
//                    if (httpCon.getResponseCode() == 200) {
//                        notSend = false;
//                    }
//                } catch (Exception ex) {
//
//                    // ex.printStackTrace();
//                    System.out.println("B³¹d po³¹czenia z serwerem, nastêpna próba nast¹pi za 5minut.");
//                    try {
//                        this.sleep(300000);
//                        continue;
//                    } catch (InterruptedException ex1) {
//                        Logger.getLogger(GetParameters.class.getName()).log(Level.SEVERE, null, ex1);
//                    }
//                } finally {
//                    if (out != null) {
//                        try {
//                            out.close();
//                        } catch (IOException ex) {
//                            Logger.getLogger(GetParameters.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                }
//            }

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
}
