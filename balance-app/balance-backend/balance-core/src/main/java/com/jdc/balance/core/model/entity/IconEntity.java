package com.jdc.balance.core.model.entity;

import java.io.Serializable;

import com.jdc.balance.core.model.entity.audit.AuditMetadataEntity;
import com.jdc.balance.core.util.BalanceConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = BalanceConstant.EntityName.ICON)
@Table(name = BalanceConstant.TABLE_PREFIX_MASTER + "icons")
public class IconEntity extends AuditMetadataEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String path;

}
