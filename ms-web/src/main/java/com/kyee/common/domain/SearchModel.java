package com.kyee.common.domain;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchModel {
	@NotNull
	private Integer pageNumber;
	@NotNull
	private Integer pageSize;
	private String keyword;
}
