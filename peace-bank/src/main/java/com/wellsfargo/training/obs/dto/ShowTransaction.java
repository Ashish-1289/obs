package com.wellsfargo.training.obs.dto;

import com.wellsfargo.training.obs.model.Transact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowTransaction {
	
	private Transact t;
	
	private String transType;
}
