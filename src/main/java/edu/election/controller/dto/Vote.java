package edu.election.controller.dto;

public class Vote {
    private int id;
    private Citizen citizen;
    private Candidate candidate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
