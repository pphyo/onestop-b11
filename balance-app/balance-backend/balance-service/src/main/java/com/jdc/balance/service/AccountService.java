package com.jdc.balance.service;

import static com.jdc.balance.core.util.BalanceUtil.notFoundWithId;

import java.util.List;
import java.util.function.Function;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.model.entity.AccountEntity;
import com.jdc.balance.core.model.entity.AccountEntity_;
import com.jdc.balance.core.payload.input.AccountInput;
import com.jdc.balance.core.payload.output.AccountOutput;
import com.jdc.balance.core.payload.param.AccountParam;
import com.jdc.balance.repository.entity.AccountRepository;
import com.jdc.balance.repository.entity.IconRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
	
	private final AccountRepository accountRepo;
	private final IconRepository iconRepo;
	private final AmountFormatService formatService;
	
	public AccountOutput save(AccountInput input) {
		return AccountOutput.from(
				accountRepo.save(
						input.entity(id -> id == null ? null : iconRepo.findById(id).orElseThrow(() -> notFoundWithId("icon", id)))), amount -> formatService.formatAmount(amount));
	}
	
	public AccountOutput update(Long id, AccountInput input) {
		var entity = accountRepo.findById(id).map(account -> {
			account.setName(input.name());
			account.setAmount(input.amount());
			account.setIcon(input.iconId() == null ? null :
					iconRepo.findById(input.iconId())
						.orElseThrow(() -> notFoundWithId("icon", input.iconId())));
			return account;
		}).orElseThrow(() -> notFoundWithId("account", id));
		
		return AccountOutput.from(entity, amount -> formatService.formatAmount(amount));
	}
	
	public boolean delete(Long id) {
		accountRepo.deleteById(id);
		return accountRepo.findById(id).isEmpty();
	}
	
	@Transactional(readOnly = true)
	public List<AccountOutput> search(AccountParam param) {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Function<CriteriaBuilder, CriteriaQuery<AccountEntity>> query = cb -> {
			var cq = cb.createQuery(AccountEntity.class);
			var root = cq.from(AccountEntity.class);
			
			cq.select(root);
			cq.where(param.where(cb, root, username));
			cq.orderBy(cb.asc(root.get(AccountEntity_.ID)));
			return cq;
		};
		return accountRepo.search(query).stream().map(acc -> AccountOutput.from(acc, amount -> formatService.formatAmount(amount))).toList();
	}
	
	@Transactional(readOnly = true)
	public AccountOutput searchById(Long id) {
		return AccountOutput.from(
				accountRepo.findById(id)
					.orElseThrow(() -> notFoundWithId("account", id)), amount -> formatService.formatAmount(amount));
	}

}
