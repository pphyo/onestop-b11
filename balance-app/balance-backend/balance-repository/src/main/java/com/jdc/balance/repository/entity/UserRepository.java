package com.jdc.balance.repository.entity;

import java.util.Optional;

import com.jdc.balance.core.model.entity.UserEntity;
import com.jdc.balance.repository.BaseRepository;

public interface UserRepository extends BaseRepository<UserEntity, Long> {

	Optional<UserEntity> findOneByUsername(String username);
	
}
