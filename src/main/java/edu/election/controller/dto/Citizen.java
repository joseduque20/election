package edu.election.controller.dto;

public class Citizen {
    private int id;
    private String Name;
    private String surname;
    private int age;
    private Gender gender;
    private DocumentType documentType;
    private String documentNumber;
    private VotingPlace votingPlace;
    private String VotingTable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public VotingPlace getVotingPlace() {
        return votingPlace;
    }

    public void setVotingPlace(VotingPlace votingPlace) {
        this.votingPlace = votingPlace;
    }

    public String getVotingTable() {
        return VotingTable;
    }

    public void setVotingTable(String votingTable) {
        VotingTable = votingTable;
    }
}
