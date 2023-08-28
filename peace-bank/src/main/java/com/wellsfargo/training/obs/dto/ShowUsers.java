package com.wellsfargo.training.obs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShowUsers {
	
	private long id;
	private String name;
	private long number;
	private long balance;
}
