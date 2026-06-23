package com.voting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponseDTO {

	private String message;

	private boolean success;

	private Long voterId;

	private Long candidateId;

}
