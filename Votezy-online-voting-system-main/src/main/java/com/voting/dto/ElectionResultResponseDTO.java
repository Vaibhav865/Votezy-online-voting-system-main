package com.voting.dto;

import lombok.Data;

@Data
public class ElectionResultResponseDTO {

	private String electionname;
	
	private int totalVotes;
	
	private long winnerId;
	
	private int winnerVote;
}
