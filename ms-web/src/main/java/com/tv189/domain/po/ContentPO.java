package com.tv189.domain.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="C_CONTENT")
public class ContentPO {
	@Id
	@GeneratedValue(generator = "idgenerator")
	@GenericGenerator(name = "idgenerator", strategy="assigned")
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	private Long id;
	
	@Column(name = "CONTENTID", length = 32)
	private String contentId;
	
	@Column(name = "TITLE", length = 128)
	private String title;
	
	@Column(name = "CATEGORYID", precision = 4)
	private Integer categoryId;
	
	@Column(name = "SUBCATEGORYNAME", length = 64)
	private String subCategoryName;
	
	@Column(name = "PRODUCTID", length = 64)
	private String productId;
	
	@Column(name = "CREATETIME")
	private Date createTime;
	
	@Column(name = "UPDATETIME")
	private Date updateTime;
}
