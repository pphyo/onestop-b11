package com.jdc.balance.core.model.entity;

import java.math.BigDecimal;
import java.util.List;

import com.jdc.balance.core.exception.BalanceBusinessException;
import com.jdc.balance.core.model.entity.audit.AuditTimeMetadata;
import com.jdc.balance.core.model.entity.consts.TransactionType;
import com.jdc.balance.core.util.BalanceConstant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = BalanceConstant.EntityName.ACCOUNT)
@Table(name = BalanceConstant.TABLE_PREFIX_TX + "accounts")
public class AccountEntity extends AuditTimeMetadata {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String name;

	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal amount;

	@OneToOne(optional = false)
	private IconEntity icon;

	@ManyToOne(optional = false)
	private UserEntity user;

	@OneToMany(mappedBy = "account", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	private List<TransactionEntity> transactions;

	@OneToMany(mappedBy = "targetAccount", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<TransactionEntity> incomingTransactions;

	public void balanceAmount(TransactionType type, BigDecimal amount) {
		if(type.equals(TransactionType.Income)) {
			this.amount = this.amount.add(amount);
		} else {
			if(this.amount.compareTo(amount) < 0) {
				throw new BalanceBusinessException("Not enough amount.");
			}
			
			this.amount = this.amount.subtract(amount);
		}
	}

	public void revertBalanceAmount(TransactionType type, BigDecimal amount) {
		this.amount = type.equals(TransactionType.Income) ? this.amount.subtract(amount) : this.amount.add(amount);	
	}
}
