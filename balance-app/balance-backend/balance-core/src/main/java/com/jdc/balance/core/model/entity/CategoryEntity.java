package com.jdc.balance.core.model.entity;

import java.io.Serializable;
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
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = BalanceConstant.EntityName.CATEGORY)
@Table(name = BalanceConstant.TABLE_PREFIX_MASTER + "categories")
public class CategoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Boolean income;
	
	@ManyToOne(optional = false)
	private IconEntity icon;
	
	@ManyToOne(optional = false)
	private UserEntity user;
	
	@OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<TransactionEntity> transactions;
}
