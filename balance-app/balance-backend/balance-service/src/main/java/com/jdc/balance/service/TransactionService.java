package com.jdc.balance.service;

import static com.jdc.balance.core.util.BalanceUtil.notFoundWithId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.model.entity.AccountEntity;
import com.jdc.balance.core.model.entity.CategoryEntity;
import com.jdc.balance.core.model.entity.TransactionEntity;
import com.jdc.balance.core.model.entity.TransactionEntity_;
import com.jdc.balance.core.model.entity.consts.TransactionType;
import com.jdc.balance.core.payload.input.TransactionBaseInput;
import com.jdc.balance.core.payload.input.TransactionForIncomeExpenseInput;
import com.jdc.balance.core.payload.input.TransactionForTransferInput;
import com.jdc.balance.core.payload.output.TransactionBaseOutput;
import com.jdc.balance.core.payload.output.TransactionForDailyOutput;
import com.jdc.balance.core.payload.output.TransactionForIncomeExpenseOutput;
import com.jdc.balance.core.payload.output.TransactionForMonthlyOutput;
import com.jdc.balance.core.payload.output.TransactionForTransferOutput;
import com.jdc.balance.core.payload.param.TransactionParam;
import com.jdc.balance.repository.entity.AccountRepository;
import com.jdc.balance.repository.entity.CategoryRepository;
import com.jdc.balance.repository.entity.TransactionRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {
	
	private final TransactionRepository transactionRepo;
	private final AccountRepository accountRepo;
	private final CategoryRepository categoryRepo;
	private final AmountFormatService formatService;
	
	public TransactionBaseOutput create(TransactionBaseInput input) {
		if(input instanceof TransactionForTransferInput data) {
			return createTransfer(data);
		}
		return createIncomeExpense((TransactionForIncomeExpenseInput) input);
	}
	
	private TransactionBaseOutput createIncomeExpense(TransactionForIncomeExpenseInput input) {
		Function<Long, AccountEntity> accountMapper = 
				accountId -> accountRepo
								.findById(accountId)
								.orElseThrow(() -> notFoundWithId("account", accountId));
		Function<Long, CategoryEntity> categoryMapper = 
				categoryId -> categoryRepo
							.findById(categoryId)
							.orElseThrow(() -> notFoundWithId("category", categoryId));
		Function<BigDecimal, String> formatMapper = amount -> formatService.formatAmount(amount);

		var entity = input.entity(accountMapper, categoryMapper);
		return TransactionForIncomeExpenseOutput.from(transactionRepo.save(entity), formatMapper);
	}
	
	private TransactionBaseOutput createTransfer(TransactionForTransferInput input) {
		Function<Long, AccountEntity> accountMapper = 
				accountId -> accountRepo
								.findById(accountId)
								.orElseThrow(() -> notFoundWithId("account", accountId));
		Function<BigDecimal, String> formatMapper = amount -> formatService.formatAmount(amount);
		
		var entity = transactionRepo.save(input.entity(accountMapper));
		
		return TransactionForTransferOutput.from(entity, formatMapper);
	}
	
	public TransactionBaseOutput update(Long id, TransactionBaseInput input) {
		var transaction = transactionRepo.findById(id).orElseThrow(() -> notFoundWithId("transaction", id));
		
		revertTransaction(transaction);
		
		transaction.setAmount(input.amount());
		transaction.setType(input.type());
		transaction.setIssuedAt(input.issuedAt() == null ? LocalDateTime.now() : input.issuedAt());
		transaction.setNote(input.note());
		
		Function<Long, AccountEntity> accountMapper = 
				accountId -> accountRepo.findById(accountId)
								.orElseThrow(() -> notFoundWithId("account", accountId));

		if(input.type().equals(TransactionType.Transfer)
				&& input instanceof TransactionForTransferInput data) {
			
			var fromForUpdate = accountMapper.apply(data.accountFrom());
			var toForUpdate = accountMapper.apply(data.accountTo());
			transaction.transfer(fromForUpdate, toForUpdate, data.amount());
			
			transaction.setAccount(fromForUpdate);
			transaction.setTargetAccount(toForUpdate);
		} else {
			var data = (TransactionForIncomeExpenseInput) input;
			var newAccount = accountMapper.apply(data.account());
			newAccount.balanceAmount(data.type(), data.amount());
			
			transaction.setAccount(newAccount);
			transaction.setCategory(categoryRepo.findById(data.category())
						.orElseThrow(() -> notFoundWithId("category", data.category())));
		}
		
		return input.type().equals(TransactionType.Transfer) ?
				TransactionForTransferOutput.from(transaction, amount -> formatService.formatAmount(amount)) :
				TransactionForIncomeExpenseOutput.from(transaction, amount -> formatService.formatAmount(amount)) ;
	}
	
	private void revertTransaction(TransactionEntity transaction) {
		if(transaction.getType() == TransactionType.Transfer) {
			transaction.revertTransfer(transaction.getAccount(), transaction.getTargetAccount(), transaction.getAmount());
			transaction.setTargetAccount(null);
		} else {
			transaction.getAccount().revertBalanceAmount(transaction.getType(), transaction.getAmount());
			transaction.setCategory(null);
		}
	}

	public TransactionBaseOutput updateIncomeExpense(Long id, TransactionForIncomeExpenseInput input) {
		var transaction = transactionRepo.findById(id).orElseThrow(() -> notFoundWithId("transaction", id));
		
		// revert balance
		var account = transaction.getAccount();
		account.revertBalanceAmount(transaction.getType(), transaction.getAmount());
		
		// set new fields for transaction
		transaction.setAmount(input.amount());
		transaction.setType(input.type());
		transaction.setIssuedAt(input.issuedAt() == null ? LocalDateTime.now() : input.issuedAt());
		transaction.setNote(input.note());
		
		// find account and category
		var newAccount = accountRepo
							.findById(input.account())
							.orElseThrow(() -> notFoundWithId("account", input.account()));
		
		var newCategory = categoryRepo
							.findById(input.category())
							.orElseThrow(() -> notFoundWithId("category", input.category()));
		
		// balance
		account.balanceAmount(input.type(), input.amount());
		
		// set in transaction
		transaction.setAccount(newAccount);
		transaction.setCategory(newCategory);
		
		return TransactionForIncomeExpenseOutput.from(transaction, amount -> formatService.formatAmount(amount));
	}

	public TransactionBaseOutput updateTransfer(Long id, TransactionForTransferInput input) {
		Function<Long, AccountEntity> accountMapper = 
								accountId -> accountRepo.findById(accountId)
												.orElseThrow(() -> notFoundWithId("account", accountId));
		
		var transaction = transactionRepo.findById(id).orElseThrow(() -> notFoundWithId("transaction", id));
		
		var from = transaction.getAccount();
		var to = transaction.getTargetAccount();
		
		transaction.revertTransfer(from, to, transaction.getAmount());
		
		transaction.setAmount(input.amount());
		transaction.setType(input.type());
		transaction.setIssuedAt(input.issuedAt() == null ? LocalDateTime.now() : input.issuedAt());
		transaction.setNote(input.note());
		
		var fromForUpdate = accountMapper.apply(input.accountFrom());
		var toForUpdate = accountMapper.apply(input.accountTo());
		
		transaction.transfer(fromForUpdate, toForUpdate, input.amount());
		
		return TransactionForTransferOutput.from(transaction, amount -> formatService.formatAmount(amount));
	}
	
	@Transactional(readOnly = true)
	public TransactionBaseOutput searchById(Long id) {
		var transaction = transactionRepo.findById(id).orElseThrow(() -> notFoundWithId("transaction", id));
		return transaction.getCategory() == null ?
				TransactionForTransferOutput.from(transaction, amount -> formatService.formatAmount(amount)) :
				TransactionForIncomeExpenseOutput.from(transaction, amount -> formatService.formatAmount(amount));
	}
	
	@Transactional(readOnly = true)
	public TransactionForMonthlyOutput search(TransactionParam param) {
		Function<BigDecimal, String> formatMapper = amount -> formatService.formatAmount(amount);
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Function<CriteriaBuilder, CriteriaQuery<TransactionEntity>> query = cb -> {
			var cq = cb.createQuery(TransactionEntity.class);
			var root = cq.from(TransactionEntity.class);
			
			cq.select(root);
			cq.where(param.where(cb, root, username));
			cq.orderBy(cb.desc(root.get(TransactionEntity_.issuedAt)));
			
			return cq;
		};
		
		List<TransactionEntity> list = transactionRepo.search(query);
		
		Map<LocalDate, List<TransactionBaseOutput>> groupByDate = list.stream()
												.collect(Collectors.groupingBy(
															entity -> entity.getIssuedAt().toLocalDate(),
															Collectors.mapping(
																	entity -> entity.getCategory() == null ?
																		TransactionForTransferOutput.from(entity, formatMapper) :
																		TransactionForIncomeExpenseOutput.from(entity, formatMapper),
																		Collectors.toList()
																)
															));
		
		List<TransactionForDailyOutput> dailyTransactions = groupByDate.entrySet()
												.stream()
												.map(entry -> TransactionForDailyOutput.from(entry.getKey(), entry.getValue()))
												.sorted((a, b) -> a.date().compareTo(b.date()))
												.toList();
		
		return TransactionForMonthlyOutput.from(YearMonth.parse(param.month()), dailyTransactions);
	}

}
