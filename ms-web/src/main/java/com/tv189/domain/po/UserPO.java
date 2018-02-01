package com.tv189.domain.po;

import java.io.Serializable;



import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 复合索引，加复合索引后通过复合索引字段查询将大大提高速度。
 * @CompoundIndexes({
    @CompoundIndex(name = "phone_age_idx", def = "{'phone': 1, 'age': -1}")
}  )
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user") //标注在实体类上，类似于hibernate的entity注解，标明由mongo来维护该表。
public class UserPO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id //主键，不可重复，自带索引。如果自己不设置@Id主键，mongo会自动生成一个唯一主键，并且插入时效率远高于自己设置主键。
	private String userId;
	
	@NotNull @Indexed(unique = true)//声明该字段需要加索引，这里添加的是唯一索引
	private String userName;
	
	@NotNull
	private String nickName;
	
	@NotNull @Indexed
	private String phone;
	
	@NotNull
	private Integer age;
	
	private String email;
	
}
