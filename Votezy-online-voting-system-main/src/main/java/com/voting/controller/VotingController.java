package com.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting.dto.VoteRequestDTO;
import com.voting.dto.VoteResponseDTO;
import com.voting.entity.Vote;
import com.voting.service.VotingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VotingController {

	@Autowired
	private VotingService votingService;

	@PostMapping("/cast")
	public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequest) {
		Vote vote = votingService.castVote(voteRequest.getVoterId(), voteRequest.getCandidateId());
		VoteResponseDTO voteResponseDTO = new VoteResponseDTO("Vote casted sucessfully", true, vote.getVoterId(),
				vote.getCandidateId());
		return new ResponseEntity<>(voteResponseDTO, HttpStatus.CREATED);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Vote>> getAllVotes() {
		List<Vote> allVotes = votingService.getAllVotes();
		return new ResponseEntity<>(allVotes, HttpStatus.OK);
	}

}
