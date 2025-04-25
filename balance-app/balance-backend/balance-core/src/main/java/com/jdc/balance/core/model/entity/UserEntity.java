package com.jdc.balance.core.model.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jdc.balance.core.model.entity.audit.AuditorMetadata;
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
@Entity(name = BalanceConstant.EntityName.USER)
@Table(name = BalanceConstant.TABLE_PREFIX_MASTER + "users")
public class UserEntity extends AuditorMetadata implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String name;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private Boolean admin;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(admin) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

}
