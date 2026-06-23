package com.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting.entity.Voter;
import com.voting.service.VoterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
	
	@Autowired
	private VoterService service;

	@PostMapping("/register")
	public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter){
		Voter savedVoter = service.registerVoter(voter); 
		return new ResponseEntity<>(savedVoter,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Voter> getVoterById(@PathVariable Long id){
		Voter voter = service.getVoterById(id); 
		return new ResponseEntity<>(voter,HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Voter>> getAllVoters(){
		List<Voter> allVoter = service.getAllVoter();
		return new ResponseEntity<>(allVoter,HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Voter> updateVoter(@PathVariable @Valid Long id, @RequestBody Voter voter){
		Voter updatedVoter = service.updateVoter(id, voter);
		return new ResponseEntity<>(updatedVoter,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteVoter(@PathVariable Long id){
		service.deleteVoter(id);
		return new ResponseEntity<>("Voter with id:: "+id+" deleted",HttpStatus.OK);
	}
	
}
