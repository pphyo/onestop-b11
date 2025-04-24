package com.jdc.balance.service;

import java.util.function.Function;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.model.entity.CurrencyEntity;
import com.jdc.balance.core.payload.input.SettingInput;
import com.jdc.balance.core.payload.output.SettingOutput;
import com.jdc.balance.core.util.BalanceUtil;
import com.jdc.balance.repository.entity.CurrencyRepository;
import com.jdc.balance.repository.entity.SettingRepository;
import com.jdc.balance.repository.entity.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SettingService {
	
	private final SettingRepository settingRepo;
	private final CurrencyRepository currencyRepo;
	private final UserRepository userRepo;
	
	public SettingOutput save(SettingInput input) {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		Function<Long, CurrencyEntity> currencyMapper = id -> currencyRepo
																.findById(id)
																.orElseThrow(() ->
																	BalanceUtil.notFoundWithId("currency", id));
		var user = userRepo.findByUsername(username)
							.orElseThrow(() ->
								new UsernameNotFoundException(username));
		return SettingOutput.from(settingRepo.save(input.entity(currencyMapper, user)));
	}
	
	public SettingOutput update(Long id, SettingInput input) {
		return SettingOutput.from(settingRepo.findById(id).map(setting -> {
			setting.setDecimalPlace(input.decimalPlace());
			setting.setCurrencyPosition(input.currencyPosition());
			setting.setCurrency(currencyRepo.findById(input.currency()).orElseThrow(() -> BalanceUtil.notFoundWithId("currency", input.currency())));
			return setting;
		}).orElseThrow(() -> BalanceUtil.notFoundWithId("setting", id)));
	}
	
	@Transactional(readOnly = true)
	public SettingOutput findByUsername(String username) {
		return SettingOutput.from(settingRepo.findByUserUsername(username).orElse(null));
	}

	@Transactional(readOnly = true)
	public SettingOutput searchForCurrentUser() {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		return findByUsername(username);
	}

}
