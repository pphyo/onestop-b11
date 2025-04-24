package com.jdc.balance.repository.entity;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;

import com.jdc.balance.core.model.entity.AccountEntity;
import com.jdc.balance.repository.BaseRepository;

public interface AccountRepository extends BaseRepository<AccountEntity, Long> {

	@EntityGraph(attributePaths = {"transactions"})
	List<AccountEntity> findByUserUsername(String username);
	
}
