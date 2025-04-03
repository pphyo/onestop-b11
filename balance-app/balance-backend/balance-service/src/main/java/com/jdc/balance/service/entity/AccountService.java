package com.jdc.balance.service.entity;

import static com.jdc.balance.core.util.BalanceUtil.notFoundWithId;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.payload.input.AccountInput;
import com.jdc.balance.core.payload.output.AccountOutput;
import com.jdc.balance.repository.entity.AccountRepository;
import com.jdc.balance.repository.entity.IconRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
	
	private final AccountRepository accountRepo;
	private final IconRepository iconRepo;
	
	public AccountOutput save(AccountInput input) {
		return AccountOutput.from(
				accountRepo.save(
						input.entity(id -> id == null ? null : iconRepo.findById(id).orElseThrow(() -> notFoundWithId("icon", id)))));
	}
	
	public AccountOutput update(Long id, AccountInput input) {
		var entity = accountRepo.findById(id).map(account -> {
			account.setName(input.name());
			account.setInitialAmount(input.amount());
			account.setIcon(input.iconId() == null ? null :
					iconRepo.findById(input.iconId())
						.orElseThrow(() -> notFoundWithId("icon", input.iconId())));
			return account;
		}).orElseThrow(() -> notFoundWithId("account", id));
		
		return AccountOutput.from(entity);
	}
	
	public boolean delete(Long id) {
		accountRepo.deleteById(id);
		return accountRepo.findById(id).isEmpty();
	}
	
	@Transactional(readOnly = true)
	public AccountOutput searchById(Long id) {
		return AccountOutput.from(
				accountRepo.findById(id)
					.orElseThrow(() -> notFoundWithId("account", id)));
	}

}
