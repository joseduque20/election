package edu.election.controller;

import edu.election.controller.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("candidate")
public class CandidateController {
    private List<Candidate> candidates;
    @Autowired
    private CitizenController citizenController;

    //public CandidateController() {
    @PostConstruct
    private void methodInitial(){
        candidates = new ArrayList<>();

        Candidate candidate = new Candidate();
        List<Citizen> citizens = new ArrayList<>();
        citizens = citizenController.getAllCitizen();

        Optional<Citizen> citizenToId =
                citizens.stream()
                        .filter(citizen1 -> citizen1.getId() == 1)
                        .findFirst();
        if(citizenToId.isPresent()){
            candidate.setId(1);
            candidate.setCitizen(citizenToId.get());
            candidate.setParty("Partido de la U");
            candidate.setNumberCard(325);
        }
        candidates.add(candidate);
    }

    @GetMapping("/")
    public List<Candidate> getAllCandidate(){
        return candidates;
    }

    @GetMapping("/{id}")
    public ResponseEntity getCandidateById(@PathVariable int id){
        Optional <Candidate> candidateToReturn =
                candidates.stream()
                        .filter(candidate -> candidate.getId() == id)
                        .findFirst();
        if (candidateToReturn.isPresent()){
            return new ResponseEntity(candidateToReturn.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity addCandidate(@RequestBody Candidate candidate){
        List<Citizen> citizens = new ArrayList<>();
        citizens = citizenController.getAllCitizen();

        Optional<Citizen> citizenToId =
                citizens.stream()
                        .filter(citizen1 -> citizen1.getId() == candidate.getCitizen().getId())
                        .findFirst();
        if(citizenToId.isPresent()){
            candidate.setCitizen(citizenToId.get());
            candidates.add(candidate);
            return new ResponseEntity(HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
