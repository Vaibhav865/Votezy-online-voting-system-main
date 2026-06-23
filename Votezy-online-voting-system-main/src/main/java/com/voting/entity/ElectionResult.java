package com.voting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "election_Result")
public class ElectionResult {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_seq")
	@SequenceGenerator(
	        name = "vote_seq",
	        sequenceName = "ElectionResult_SEQ",
	        initialValue = 1000,
	        allocationSize = 1
	)
	private Long id;

	
	@NotBlank(message = "election name is required")
	@Column(name = "electionname",length = 30)
	private String electionName;

	private int totalVotes;

	@OneToOne
	@JoinColumn(name = "winner_id")
	@JsonIgnore
	private Candidate winner;

	@JsonProperty("winnerId")
	public Long getWinnerId() {
		return winner != null ? winner.getId() : null;
	}

}
