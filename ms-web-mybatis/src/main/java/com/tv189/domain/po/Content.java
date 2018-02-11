package com.tv189.domain.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {
    private Long id;

    private Integer categoryid;

    private String contentid;

    private Date createtime;

    private String productid;

    private String subcategoryname;

    private String title;

    private Date updatetime;
}