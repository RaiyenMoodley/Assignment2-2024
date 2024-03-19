/**
 * Entry class that stores each lines of Knowledge Base
 * Raiyen Moodley
 * MDLRAI001
 * 19/03/2024
 */

public class Entry {
    public String term;
    public String statement;
    public double confidenceScore;

    public Entry(String term, String statement, double confidenceScore){
       this.term = term;
       this.statement = statement;
       this.confidenceScore = confidenceScore;
    }

    //Get and Set Methods for instance variables.
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

}
