package com.jdc.balance.core.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.jdc.balance.core.util.BalanceConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity(name = BalanceConstant.EntityName.BUDGET)
@Table(name = BalanceConstant.TABLE_PREFIX_TX + "budgets", uniqueConstraints = {
		@UniqueConstraint(columnNames = "month"), @UniqueConstraint(columnNames = "category_id") })
public class BudgetEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "limit_amount", nullable = false, precision = 10, scale = 2)
	private BigDecimal limitAmount;
	
	@Column(nullable = false)
	private LocalDate month = LocalDate.now();
	
	@ManyToOne(optional = false)
	private CategoryEntity category;

}
