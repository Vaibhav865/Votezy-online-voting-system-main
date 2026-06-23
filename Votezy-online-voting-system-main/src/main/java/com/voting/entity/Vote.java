package com.voting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vote")
public class Vote {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_seq")
	@SequenceGenerator(
	        name = "vote_seq",
	        sequenceName = "Vote_SEQ",
	        initialValue = 1000,
	        allocationSize = 1
	)
	private Long id;

	@OneToOne
	@JoinColumn(name = "voter_id", unique = true)
	@JsonIgnore
	private Voter voter;

	@ManyToOne
	@JoinColumn(name = "candidate_id")
	@JsonIgnore
	private Candidate candidate;

	@JsonProperty("voterId")
	public Long getVoterId() {
		return voter != null ? voter.getId() : null;
	}

	@JsonProperty("candidateId")
	public Long getCandidateId() {
		return candidate != null ? candidate.getId() : null;
	}

}
