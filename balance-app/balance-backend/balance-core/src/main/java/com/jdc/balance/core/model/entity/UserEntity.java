package com.jdc.balance.core.model.entity;

import java.io.Serializable;

import com.jdc.balance.core.util.BalanceConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = BalanceConstant.EntityName.USER)
@Table(name = BalanceConstant.TABLE_PREFIX_MASTER + "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

}
