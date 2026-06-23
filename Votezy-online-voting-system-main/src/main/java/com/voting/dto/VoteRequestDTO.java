package com.voting.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequestDTO {

	@NotNull(message = "voter id is required")
	private Long voterId;
	
	@NotNull(message = "candidate id is required")
	private Long candidateId;
}
