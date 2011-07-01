/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paidesktop;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 *
 * @author banyaai
 */
public class ReadWriteFile {

    private String localFilename = new String("paidesktop.config");
    private String login;
    private String server_url;
    private String password;

    public void readConfig() throws Exception {
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("/home/banyaai/paidesktop.config");
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

}
