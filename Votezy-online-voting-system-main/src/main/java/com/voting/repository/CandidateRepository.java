package com.voting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	List<Candidate> findAllByOrderByVoteCountDesc();
	public boolean existsByEmail(String email);
}
