/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paidesktop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author banyaai
 */
public class SaveVotes {

    public void saveVotes(String filename, String votes) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write(votes);
            out.close();
        } catch (IOException e) {
            
        }
    }
}
