package com.tv189.common;

import lombok.Data;

@Data
public class SearchParam {
	private Integer page = 0;

    private Integer size;

    private String wd;

    private String[] andParam = {};

    private String[] orParam = {};

    private String[] notParam = {};

    private String[] sort = {};

    private Boolean highlight=true;

    private String[] highlightFields;

    private String[] responseField;

    private String[] ignoreField;
}
