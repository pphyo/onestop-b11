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
@Entity(name = BalanceConstant.EntityName.CURRENCY)
@Table(name = BalanceConstant.TABLE_PREFIX_MASTER + "currencies")
public class CurrencyEntity extends AuditMetadataEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, length = 20)
	private String code;

}
