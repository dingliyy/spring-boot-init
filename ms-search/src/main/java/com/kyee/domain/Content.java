package com.kyee.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注解 @Document 添加后，默认情况下这个实体中所有的属性都会被建立索引、并且分词。
 * 也可以通过 @Field 注解来进行详细指定。
 * 
 * 应用启动后，Spring-data-elasticSearch会自动帮我们建立索引库和创建实体的mapping信息，默认情况下，
 * 在创建mapping信息的时候，只会创建添加了@Field注解的mapping信息。其他没有添加@Filed注解的字段在保存索引的时候自动确定。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "program", //类似数据库的库名
          type = "path", //类型数据库表名，类似solr的Collection
          shards = 1, //索引分片，es可以把一个完整的索引分成多个分片，这样的好处是可以把一个大的索引拆分成多个，分布到不同的节点上。构成分布式搜索。分片的数量只能在索引创建前指定，并且索引创建后不能更改。
          replicas = 0) //代表索引副本，es可以设置多个索引的副本，副本的作用一是提高系统的容错性，当个某个节点某个分片损坏或丢失时可以从副本中恢复。二是提高es的查询效率，es会自动对搜索请求进行负载均衡。
public class Content {
	@Id //主键
	private Long id;
	
	//@Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
	private String title;
	
	@Field(type = FieldType.Nested)
	private List<Video> videos;
}
