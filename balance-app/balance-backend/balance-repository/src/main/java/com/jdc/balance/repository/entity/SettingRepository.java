package com.jdc.balance.repository.entity;

import java.util.Optional;

import com.jdc.balance.core.model.entity.SettingEntity;
import com.jdc.balance.repository.BaseRepository;

public interface SettingRepository extends BaseRepository<SettingEntity, Long> {

	Optional<SettingEntity> findByUserUsername(String username);
	
}
