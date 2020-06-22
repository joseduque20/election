package edu.election.controller;

import edu.election.controller.dto.Citizen;
import edu.election.controller.dto.DocumentType;
import edu.election.controller.dto.Gender;
import edu.election.controller.dto.VotingPlace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citizen")
public class CitizenController {
    private List<Citizen> citizens;

    public CitizenController() {
        citizens = new ArrayList<>();

        Citizen citizen = new Citizen();
        citizen.setId(1);
        citizen.setName("Cristian Camilo");
        citizen.setSurname("Torres Duque");
        citizen.setAge(21);
        citizen.setGender(Gender.MALE);
        citizen.setDocumentType(DocumentType.CITIZENSHIPCARD);
        citizen.setDocumentNumber("100.598.605");

        VotingPlace votingPlace = new VotingPlace();
        votingPlace.setId(2);
        votingPlace.setName("Instituto masculino");
        votingPlace.setAddress("CR 44 # 55 - 02");
        votingPlace.setMunicipality("Bello");
        votingPlace.setDepartment("Antioquia");
        votingPlace.setCountry("Colombia");
        VotingPlaceController votingPlaceController = new VotingPlaceController();
        votingPlaceController.addVotingPlace(votingPlace);

        citizen.setVotingPlace(votingPlace);
        citizen.setVotingTable("Mesa 1");

        citizens.add(citizen);
    }

    @GetMapping("/")
    public List<Citizen> getAllCitizen(){
        return citizens;
    }

    @GetMapping("/votingplace/{id}")
    public ResponseEntity getVotingPlaceById(@PathVariable int id){
        Optional <Citizen> citizenToReturn =
                citizens.stream()
                        .filter(citizen -> citizen.getId() == id)
                        .findFirst();
        if (citizenToReturn.isPresent()){
            return new ResponseEntity(citizenToReturn.get().getVotingPlace(), HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public void addCitizen(@RequestBody Citizen citizen){
        citizens.add(citizen);
    }

    @PostMapping("/2/")
    public ResponseEntity addCitizen2(@RequestBody Citizen citizen){
        VotingPlace votingPlace = new VotingPlace();
        votingPlace.setId(citizen.getVotingPlace().getId());

        VotingPlaceController votingPlaceController = new VotingPlaceController();
        //votingPlaceController.addVotingPlace(votingPlace);
        List<VotingPlace> votingPlaces = new ArrayList<>();
        votingPlaces = votingPlaceController.getAllVotingPlace();

        Optional<VotingPlace> votingPlaceToId =
                votingPlaces.stream()
                        .filter(votingPlace1 -> votingPlace1.getId() == votingPlace.getId())
                        .findFirst();
        if(votingPlaceToId.isPresent()){

            votingPlace.setName(votingPlaceToId.get().getName());
            votingPlace.setAddress(votingPlaceToId.get().getAddress());
            votingPlace.setMunicipality(votingPlaceToId.get().getMunicipality());
            votingPlace.setDepartment(votingPlaceToId.get().getDepartment());
            votingPlace.setCountry(votingPlaceToId.get().getCountry());
            citizen.setVotingPlace(votingPlace);
            citizens.add(citizen);
            return new ResponseEntity(HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCitizenById(@PathVariable int id){
        Optional <Citizen> citizenToReturn =
                citizens.stream()
                        .filter(citizen -> citizen.getId() == id)
                        .findFirst();
        if (citizenToReturn.isPresent()){
            return new ResponseEntity(citizenToReturn.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    /*@GetMapping("/{documentNumber}")
    public ResponseEntity getCitizenById(@PathVariable String documentNumber){
        Optional <Citizen> citizenToReturn =
                citizens.stream()
                .filter(citizen -> citizen.getDocumentNumber() == documentNumber)
                .findFirst();
        if (citizenToReturn.isPresent()){
            return new ResponseEntity(citizenToReturn.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }*/
}
