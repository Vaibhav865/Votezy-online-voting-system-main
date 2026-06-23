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

import com.voting.entity.Candidate;
import com.voting.service.CandidateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@PostMapping("/add")
	public ResponseEntity<Candidate> addCandidate(@RequestBody @Valid Candidate candidate) {
		Candidate savedCandidate = candidateService.addCandidate(candidate);
		return new ResponseEntity<Candidate>(savedCandidate, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Candidate>> getAllCandidate() {
		List<Candidate> candidateList = this.candidateService.getAllCandidate();
		return new ResponseEntity<List<Candidate>>(candidateList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
		Candidate candidate = this.candidateService.getCandidateById(id);
		return new ResponseEntity<Candidate>(candidate, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Candidate> updateCandidate(@PathVariable @Valid Long id, @RequestBody Candidate candidate) {
		Candidate updatedCandidate = candidateService.UpdateCandidate(id, candidate);
		return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCandidate(@PathVariable Long id){
		candidateService.deleteCandidate(id);
		return new ResponseEntity<>("Candidate delete with id ::"+id+" deleted successfully",HttpStatus.OK); 
	}

}
