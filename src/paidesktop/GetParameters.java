/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package paidesktop;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.xml.ws.WebServiceClient;

/**
 *
 * @author banyaai
 */
public class GetParameters implements WebServiceClient{

    ArrayList<Candidate> candidatesList = new ArrayList<Candidate>();

    public GetParameters() {
        Candidate kandydat1 = new Candidate();
        Candidate kandydat2 = new Candidate();
        Candidate kandydat3 = new Candidate();

        kandydat1.setID(1);
        kandydat1.setName("Jaros³aw Duraj");
        kandydat2.setID(2);
        kandydat2.setName("Maciej Ga³kiewicz");
        kandydat3.setID(3);
        kandydat3.setName("Marcin Niedzielewski");
        candidatesList.add(kandydat1);
        candidatesList.add(kandydat2);
        candidatesList.add(kandydat3);
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
