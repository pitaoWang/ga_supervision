package com.atosenet.ga.company.db.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.atosenet.ga.company.db.core.common.IdEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "sys_operation")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entityCache")
public class Operation extends IdEntity {
	private static final long serialVersionUID = 1L;

	/** 操作名称 */
	@Column(name = "op_name")
	public String opName;

	/** 操作名称 */
	@Column(name = "op_identity")
	public String opIdentity;

}
