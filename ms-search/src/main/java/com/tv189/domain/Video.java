package com.tv189.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {
	private Integer videoQuarlityId;
	private String path;
	private String hlsPath;
}
