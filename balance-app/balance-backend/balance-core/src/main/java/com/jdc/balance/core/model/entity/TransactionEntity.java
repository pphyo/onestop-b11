package com.jdc.balance.core.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.balance.core.model.entity.consts.TransactionType;
import com.jdc.balance.core.util.BalanceConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = BalanceConstant.EntityName.TRANSACTION)
@Table(name = BalanceConstant.TABLE_PREFIX_TX + "transactions")
public class TransactionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "issued_at", nullable = false)
	private LocalDateTime issuedAt;

	@Column(nullable = false)
	private TransactionType type;

	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal amount;

	@Column(columnDefinition = "TEXT")
	private String note;

	@ManyToOne(optional = false)
	private AccountEntity account;

	@ManyToOne(optional = false)
	private CategoryEntity category;

}
