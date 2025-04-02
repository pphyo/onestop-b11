package com.jdc.balance.core.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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

@Data
@Entity(name = BalanceConstant.EntityName.ACCOUNT)
@Table(name = BalanceConstant.TABLE_PREFIX_TX + "accounts")
public class AccountEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String name;

	@Column(name = "initial_amount", nullable = false, precision = 15, scale = 2)
	private BigDecimal initialAmount;

	@OneToOne
	private IconEntity icon;

	@ManyToOne
	private UserEntity user;

	@OneToMany(mappedBy = "account", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	private List<TransactionEntity> transactions;

}
