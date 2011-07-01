/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paidesktop;

/**
 *
 * @author banyaai
 */
public class Candidate {

    private int id;
    private String name;
    private String party;
    private int votes;
    private int allVotes;

    public Candidate() {
    }

    public void increaseVotes() {
        votes++;
        allVotes++;
    }

    public int getId() {
        return id;
    }

    public String getParty() {
        return party;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", " + party;
    }

    public int getAllVotes() {
        return allVotes;
    }
}
