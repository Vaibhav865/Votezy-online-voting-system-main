package com.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long> {

	public boolean existsByEmail(String email);
}
