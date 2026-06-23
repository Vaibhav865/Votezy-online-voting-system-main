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

import com.voting.dto.ElectionResultRequestDTO;
import com.voting.dto.ElectionResultResponseDTO;
import com.voting.entity.ElectionResult;

import com.voting.service.ElectionResultService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/election-result")
@CrossOrigin
public class ElectionResultController {

	@Autowired
	private ElectionResultService electionResultService;

	@PostMapping("/declare")
	public ResponseEntity<ElectionResultResponseDTO> declaredElectionResult(
			@RequestBody @Valid ElectionResultRequestDTO electionResultRequestDTO) {

		ElectionResult result = electionResultService.declareElectionResult(electionResultRequestDTO.getElectionName());
		ElectionResultResponseDTO responseDTO = new ElectionResultResponseDTO();
		responseDTO.setElectionname(result.getElectionName());
		responseDTO.setTotalVotes(result.getTotalVotes());
		responseDTO.setWinnerId(result.getWinnerId());
		responseDTO.setWinnerVote(result.getWinner().getVoteCount());

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@GetMapping("/result")
	public ResponseEntity<List<ElectionResult>> getAllResult() {
		List<ElectionResult> results = electionResultService.getAllResult();
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

}
