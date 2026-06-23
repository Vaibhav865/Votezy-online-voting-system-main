package com.voting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting.entity.ElectionResult;

public interface ElectionResultRepository extends JpaRepository<ElectionResult, Long> {
	
	Optional<ElectionResult> findByElectionName(String electionName);
}
