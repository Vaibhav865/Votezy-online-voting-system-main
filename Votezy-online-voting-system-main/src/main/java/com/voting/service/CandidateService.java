package com.voting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.email.service.CandidateProfileUpdateMailService;
import com.voting.email.service.RegisterMailService;
import com.voting.entity.Candidate;
import com.voting.entity.Vote;
import com.voting.exception.DuplicateResourceException;
import com.voting.exception.ResourceNotFoundException;
import com.voting.repository.CandidateRepository;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private RegisterMailService registerMailService;

	@Autowired
	private CandidateProfileUpdateMailService candidateProfileUpdateMailService;

	CandidateService(RegisterMailService registerMailService) {
		this.registerMailService = registerMailService;
	}

	public Candidate addCandidate(Candidate candidate) {
		if (candidateRepository.existsByEmail(candidate.getEmail())) {
			throw new DuplicateResourceException("Candidate with email id:: " + candidate.getEmail() + " already exists");
		}
		Candidate registerCandidate = candidateRepository.save(candidate);
		registerMailService.sendRegistrationMail(registerCandidate.getEmail(), registerCandidate.getName(),
				registerCandidate.getParty());
		return registerCandidate;
	}

	public List<Candidate> getAllCandidate() {
		return candidateRepository.findAll();
	}

	public Candidate getCandidateById(Long id) {
		Candidate candidate = candidateRepository.findById(id).orElse(null);
		if (candidate == null) {
			throw new ResourceNotFoundException("candidate with id:: " + id + " not found");
		}
		return candidate;
	}

	public Candidate UpdateCandidate(Long id, Candidate updatedCandidate) {
		Candidate candidate = getCandidateById(id);
		if (updatedCandidate.getName() != null) {
			candidate.setName(updatedCandidate.getName());
		}

		if (updatedCandidate.getParty() != null) {
			candidate.setParty(updatedCandidate.getParty());
		}
		
		if(updatedCandidate.getEmail() != null) {
			candidate.setEmail(updatedCandidate.getEmail());
		}

		Candidate update = candidateRepository.save(candidate);
		candidateProfileUpdateMailService.sendProfileUpdateMail(update.getEmail(), update.getName(),
				candidate.getEmail(), update.getParty());
		return update;
	}

	public void deleteCandidate(Long id) {
		Candidate candidate = getCandidateById(id);
		List<Vote> votes = candidate.getVote();
		for (Vote v : votes) {
			v.setCandidate(null);
		}
		candidate.getVote().clear();
		candidateRepository.delete(candidate);
	}

}
