package com.tv189.domain.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDTO {
	@NotNull
	private Integer pageNumber;
	@NotNull
	private Integer pageSize;
	private String keyword;
}
