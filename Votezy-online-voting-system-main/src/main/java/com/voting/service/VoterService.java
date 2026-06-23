package com.voting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.email.service.RegisterMailService;
import com.voting.entity.Candidate;
import com.voting.entity.Vote;
import com.voting.entity.Voter;
import com.voting.exception.DuplicateResourceException;
import com.voting.exception.ResourceNotFoundException;
import com.voting.repository.CandidateRepository;
import com.voting.repository.VoterRepository;

import jakarta.transaction.Transactional;

@Service
public class VoterService {

	@Autowired
	private VoterRepository voterRepository;
	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private RegisterMailService registerMailService;
	

	public Voter registerVoter(Voter voter) {
		if (voterRepository.existsByEmail(voter.getEmail())) {
			throw new DuplicateResourceException("voter with email id:: " + voter.getEmail() + " already exists");
		}
		 Voter save = voterRepository.save(voter);
		 registerMailService.sendRegistrationMail(save.getEmail(), save.getName(), " ");
		 return save;
	}

	public List<Voter> getAllVoter() {
		return voterRepository.findAll();
	}

	public Voter getVoterById(Long id) {
		Voter voter = voterRepository.findById(id).orElse(null);
		if (voter == null) {
			throw new ResourceNotFoundException("voter with id:: " + id + " not found");
		}
		return voter;
	}

	public Voter updateVoter(Long id, Voter updatedVoter) {
		Voter voter = voterRepository.findById(id).orElse(null);
		if (voter == null) {
			throw new ResourceNotFoundException("voter with id:: " + id + " not found");
		}
		if (updatedVoter.getName() != null) {
			voter.setName(updatedVoter.getName());
		}
		if (updatedVoter.getEmail() != null) {
			voter.setEmail(updatedVoter.getEmail());
		}
		return voterRepository.save(voter);
	}

	@Transactional
	public void deleteVoter(Long id) {
		Voter voter = voterRepository.findById(id).orElse(null);
		if (voter == null) {
			throw new ResourceNotFoundException("Cannot delete voter with id:: " + id);
		}

		Vote vote = voter.getVote();
		if (vote != null) {
			Candidate candidate = vote.getCandidate();
			candidate.setVoteCount(candidate.getVoteCount() - 1);
			candidateRepository.save(candidate);
		}
		voterRepository.delete(voter);
	}
	
}
