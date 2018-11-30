package com.atosenet.ga.company.db.model.ga.document;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="doc_reader")
@Data
public class DocReader {
	/** 主键ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	
	/** 文档编号*/
	@Column(name="doc_id")
	private long docid;
	
	/** 文档接收人的类型， (公司，人员类别)*/
	@Column(name="reader_type")
	private String readertype;
	
	/** 文档接收人(只存公司id可以为多个)*/
	@Column(name="reader")
	private String reader;
	
	@Column(name="readflag")
	private String readflag;
}
