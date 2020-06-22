package edu.election.controller.dto;

public class Candidate {
    private int id;
    private Citizen citizen;
    private String party;
    private int numberCard;

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

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public int getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(int numberCard) {
        this.numberCard = numberCard;
    }
}
