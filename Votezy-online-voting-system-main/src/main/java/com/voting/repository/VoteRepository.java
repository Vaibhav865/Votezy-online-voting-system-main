package com.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
