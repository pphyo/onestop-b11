package com.jdc.balance.core.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.balance.core.exception.BalanceBusinessException;
import com.jdc.balance.core.model.entity.audit.AuditTimeMetadata;
import com.jdc.balance.core.model.entity.consts.TransactionType;
import com.jdc.balance.core.util.BalanceConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = BalanceConstant.EntityName.TRANSACTION)
@Table(name = BalanceConstant.TABLE_PREFIX_TX + "transactions")
public class TransactionEntity extends AuditTimeMetadata {

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

	@ManyToOne
	@JoinColumn(name = "target_account_id")
	private AccountEntity targetAccount;

	@ManyToOne
	private CategoryEntity category;

	public void transfer(AccountEntity from, AccountEntity to, BigDecimal amount) {
		if(from.getAmount().compareTo(amount) < 0) {
			throw new BalanceBusinessException("Not enough amount.");
		}
		from.setAmount(from.getAmount().subtract(amount));
		to.setAmount(to.getAmount().add(amount));
	}
	
	public void revertTransfer(AccountEntity from, AccountEntity to, BigDecimal amount) {
		from.setAmount(from.getAmount().add(amount));
		to.setAmount(to.getAmount().subtract(amount));
	}

}
