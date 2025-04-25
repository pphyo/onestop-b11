package com.jdc.balance.core.model.entity;

import com.jdc.balance.core.model.entity.audit.AuditorMetadata;
import com.jdc.balance.core.model.entity.consts.CategoryIconFilter;
import com.jdc.balance.core.util.BalanceConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class IconEntity extends AuditorMetadata {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String path;
	
	private Boolean account;
	
	@Enumerated(EnumType.STRING)
	private CategoryIconFilter filter;

}
