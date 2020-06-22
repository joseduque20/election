package edu.election.controller;

import edu.election.controller.dto.VotingPlace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/votingPlace")
public class VotingPlaceController {
    private List<VotingPlace> votingPlaces;

    public VotingPlaceController() {
        votingPlaces = new ArrayList<>();

        VotingPlace votingPlace = new VotingPlace();
        votingPlace.setId(1);
        votingPlace.setName("Instituto femenino");
        votingPlace.setAddress("CR 49 # 48 - 10");
        votingPlace.setMunicipality("Bello");
        votingPlace.setDepartment("Antioquia");
        votingPlace.setCountry("Colombia");
        votingPlaces.add(votingPlace);
    }

    @GetMapping("/")
    public List<VotingPlace> getAllVotingPlace(){
        return votingPlaces;
    }

    @PostMapping("/")
    public void addVotingPlace (@RequestBody VotingPlace votingPlace){
        votingPlaces.add(votingPlace);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeVotingPlace(@PathVariable int id){
        Optional<VotingPlace> votingPlaceToRemove =
                votingPlaces.stream()
                .filter(votingPlace -> votingPlace.getId() == id)
                .findFirst();
        if(votingPlaceToRemove.isPresent()){
            votingPlaces.remove(votingPlaceToRemove.get());
            return new ResponseEntity(HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }


}
