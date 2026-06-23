package com.voting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.email.service.VoterCastVoteMailService;
import com.voting.entity.Candidate;
import com.voting.entity.Vote;
import com.voting.entity.Voter;
import com.voting.exception.ResourceNotFoundException;
import com.voting.exception.VoteNotAllowedException;
import com.voting.repository.CandidateRepository;
import com.voting.repository.VoteRepository;
import com.voting.repository.VoterRepository;

import jakarta.transaction.Transactional;

@Service
public class VotingService {

	@Autowired
	private VoteRepository voteRepository;
	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private VoterRepository voterRepository;
	@Autowired
	private VoterCastVoteMailService castVoteMailService;

	@Transactional
	public Vote castVote(Long voterId, Long candidateId) {
		if (!voterRepository.existsById(voterId)) {
			throw new ResourceNotFoundException("Voter not found with id:: " + voterId);
		}

		if (!candidateRepository.existsById(candidateId)) {
			throw new ResourceNotFoundException("Candidate not found with id:: " + candidateId);
		}

		Voter voter = voterRepository.findById(voterId).get();
		if (voter.getHasVoted()) {
			throw new VoteNotAllowedException("voter id:: " + voterId + " has allready cast vote!");
		}

		Candidate candidate = candidateRepository.findById(candidateId).get();
		Vote vote = new Vote();
		vote.setVoter(voter);
		vote.setCandidate(candidate);
		voteRepository.save(vote);
		castVoteMailService.sendVoteConfirmationMail(voter.getEmail(), voter.getName());

		candidate.setVoteCount(candidate.getVoteCount() + 1);
		candidateRepository.save(candidate);

		voter.setVote(vote);
		voter.setHasVoted(true);
		voterRepository.save(voter);
		return vote;
	}

	public List<Vote> getAllVotes() {
		return voteRepository.findAll();
	}

}
