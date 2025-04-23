package com.jdc.balance.repository.entity;

import java.util.List;

import com.jdc.balance.core.model.entity.AccountEntity;
import com.jdc.balance.repository.BaseRepository;

public interface AccountRepository extends BaseRepository<AccountEntity, Long> {

	List<AccountEntity> findByUserUsername(String username);
	
}
