package com.jdc.balance.service;

import static com.jdc.balance.core.util.BalanceUtil.notFoundWithId;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.model.entity.AccountEntity;
import com.jdc.balance.core.model.entity.AccountEntity_;
import com.jdc.balance.core.model.entity.IconEntity;
import com.jdc.balance.core.model.entity.TransactionEntity;
import com.jdc.balance.core.model.entity.consts.TransactionType;
import com.jdc.balance.core.payload.input.AccountInput;
import com.jdc.balance.core.payload.output.AccountOutput;
import com.jdc.balance.core.payload.output.AccountOverallOutput;
import com.jdc.balance.core.payload.param.AccountParam;
import com.jdc.balance.repository.entity.AccountRepository;
import com.jdc.balance.repository.entity.IconRepository;
import com.jdc.balance.repository.entity.UserRepository;

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
	private final UserRepository userRepo;
	
	public AccountOutput save(AccountInput input) {
		Function<Long, IconEntity> iconMapper = id -> id == null ? null : iconRepo.findById(id)
														.orElseThrow(() -> notFoundWithId("icon", id));
		
		Function<BigDecimal, String> formatMapper = amount -> formatService.formatAmount(amount);
		
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		
		var savedEntity = accountRepo.save(input.entity(iconMapper, user));
		
		return AccountOutput.from(savedEntity, formatMapper);
	}
	
	public AccountOutput update(Long id, AccountInput input) {
		var entity = accountRepo.findById(id).map(account -> {
			account.setName(input.name());
			account.setAmount(input.amount());
			account.setIcon(input.icon() == null ? null :
					iconRepo.findById(input.icon())
						.orElseThrow(() -> notFoundWithId("icon", input.icon())));
			return account;
		}).orElseThrow(() -> notFoundWithId("account", id));
		
		return AccountOutput.from(entity, amount -> formatService.formatAmount(amount));
	}
	
	public boolean delete(Long id) {
		accountRepo.deleteById(id);
		return accountRepo.findById(id).isEmpty();
	}
	
	@Transactional(readOnly = true)
	public AccountOverallOutput searchOverall() {
		Function<BigDecimal, String> formatMapper = amount -> formatService.formatAmount(amount);
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var accounts = accountRepo.findByUserUsername(username);
		
		var netAsset = accounts.stream()
							.map(AccountEntity::getAmount)
							.filter(amount -> amount != null)
							.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		var income = accounts.stream()
						.flatMap(acc -> acc.getTransactions()
										.stream()
										.filter(tx -> tx.getType().equals(TransactionType.Income))
										.map(TransactionEntity::getAmount)
										.filter(amount -> amount != null))
						.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		var expense = accounts.stream()
						.flatMap(acc -> acc.getTransactions()
										.stream()
										.filter(tx -> tx.getType().equals(TransactionType.Expense))
										.map(TransactionEntity::getAmount)
										.filter(amount -> amount != null))
						.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return AccountOverallOutput.from(formatMapper, income, expense, netAsset);
	}
	
	@Transactional(readOnly = true)
	public List<AccountOutput> search(AccountParam param) {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Function<CriteriaBuilder, CriteriaQuery<AccountEntity>> query = cb -> {
			var cq = cb.createQuery(AccountEntity.class);
			var root = cq.from(AccountEntity.class);
			
			cq.select(root);
			cq.where(param.where(cb, root, username));
			cq.orderBy(cb.asc(root.get(AccountEntity_.id)));
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