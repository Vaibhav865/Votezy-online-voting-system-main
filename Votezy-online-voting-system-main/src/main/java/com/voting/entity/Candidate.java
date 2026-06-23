package com.voting.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "candidate")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_seq")
	@SequenceGenerator(
	        name = "vote_seq",
	        sequenceName = "Candidate_SEQ",
	        initialValue = 1000,
	        allocationSize = 1
	)
	private Long id;

	@NotBlank(message = "name is required")
	@Column(length = 30)
	private String name;
	
	@NotBlank(message = "email is required")
	@Email(message = "invalid email format")
	@Column(length = 40)
	private String email;

	@Min(value = 18, message = "Age must be at least 18")
	@NotNull(message = "Age is required")
	private Integer age;
	
	@NotBlank(message = "party is required")
	@Column(length = 30)
	private String party;

	private int voteCount = 0;

	@OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL )
	@JsonIgnore
	private List<Vote> vote;

}
