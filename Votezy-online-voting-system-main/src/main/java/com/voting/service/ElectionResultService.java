package com.voting.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.entity.Candidate;
import com.voting.entity.ElectionResult;
import com.voting.exception.ResourceNotFoundException;
import com.voting.repository.CandidateRepository;
import com.voting.repository.ElectionResultRepository;
import com.voting.repository.VoteRepository;

@Service
public class ElectionResultService {

	@Autowired
	private ElectionResultRepository electionResultRepository;
	@Autowired
	private VoteRepository voteRepository;
	@Autowired
	private CandidateRepository candidateRepository;

	public ElectionResult declareElectionResult(String electionName) {
		Optional<ElectionResult> existingResult = this.electionResultRepository.findByElectionName(electionName);
		if (existingResult.isPresent()) {
			return existingResult.get();
		}

		// check voting is done or not
		if (voteRepository.count() == 0) {
			throw new IllegalArgumentException("cannot declare the result as no votes have been casted");
		}

		List<Candidate> listOfCandidates = candidateRepository.findAllByOrderByVoteCountDesc();
		if (listOfCandidates.isEmpty()) {
			throw new ResourceNotFoundException("no candidates available");
		}

		Candidate winner = listOfCandidates.get(0);

		int totalVote = 0;
		for (Candidate candidate : listOfCandidates) {
			totalVote += candidate.getVoteCount();
		}

		ElectionResult result = new ElectionResult();
		result.setElectionName(electionName);
		result.setWinner(winner);
		result.setTotalVotes(totalVote);

		return electionResultRepository.save(result);
	}

	public List<ElectionResult> getAllResult() {
		return electionResultRepository.findAll();
	}

}
