package edu.election.controller;

import edu.election.controller.dto.Candidate;
import edu.election.controller.dto.Citizen;
import edu.election.controller.dto.Vote;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("vote")
public class VoteController {
    private List<Vote> votes;

    public VoteController() {
        votes = new ArrayList<>();
        Vote vote = new Vote();
        vote.setId(1);

        CitizenController citizenController = new CitizenController();
        List<Citizen> citizens = new ArrayList<>();
        citizens = citizenController.getAllCitizen();
        Optional<Citizen> citizenToId =
                citizens.stream()
                        .filter(citizen1 -> citizen1.getId() == 1)
                        .findFirst();
        if(citizenToId.isPresent()){
            vote.setCitizen(citizenToId.get());
        }

        CandidateController candidateController = new CandidateController();
        List<Candidate> candidates = new ArrayList<>();
        candidates = candidateController.getAllCandidate();
        Optional<Candidate> candidateToId =
                candidates.stream()
                        .filter(candidate1 -> candidate1.getId() == 2)
                        .findFirst();
        if(candidateToId.isPresent()){
            vote.setCandidate(candidateToId.get());
        }

        votes.add(vote);
    }

    @GetMapping("/")
    public List<Vote> getAllVote(){
        return votes;
    }

    @GetMapping("/{id}")
    public ResponseEntity getVoteById(@PathVariable int id){
        Optional <Vote> voteToReturn =
                votes.stream()
                        .filter(vote -> vote.getId() == id)
                        .findFirst();
        if (voteToReturn.isPresent()){
            return new ResponseEntity(voteToReturn.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("/")
    public ResponseEntity addVote(@RequestBody Vote vote){

        CitizenController citizenController = new CitizenController();
        List<Citizen> citizens = new ArrayList<>();
        citizens = citizenController.getAllCitizen();
        Optional<Citizen> citizenToId =
                citizens.stream()
                        .filter(citizen1 -> citizen1.getId() == vote.getCitizen().getId())
                        .findFirst();

        if(citizenToId.isPresent()){
            vote.setCitizen(citizenToId.get());
            CandidateController candidateController = new CandidateController();
            List<Candidate> candidates = new ArrayList<>();
            candidates = candidateController.getAllCandidate();
            Optional<Candidate> candidateToId =
                    candidates.stream()
                            .filter(candidate1 -> candidate1.getId() == vote.getCandidate().getId())
                            .findFirst();
            if(candidateToId.isPresent()){
                vote.setCandidate(candidateToId.get());
                votes.add(vote);
                return new ResponseEntity(HttpStatus.OK);
            } else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
