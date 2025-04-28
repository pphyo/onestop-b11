package com.jdc.balance.core.payload.param;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.balance.core.model.entity.AccountEntity_;
import com.jdc.balance.core.model.entity.CategoryEntity_;
import com.jdc.balance.core.model.entity.TransactionEntity;
import com.jdc.balance.core.model.entity.TransactionEntity_;
import com.jdc.balance.core.model.entity.UserEntity_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record TransactionParam(String month, BigDecimal amount, String keyword) {

	public Predicate where(CriteriaBuilder cb, Root<TransactionEntity> root, String username) {
		
		var predicates = new ArrayList<Predicate>();
		
		var accountJoin = root.join(TransactionEntity_.account, JoinType.INNER);
		var categoryJoin = root.join(TransactionEntity_.category, JoinType.LEFT);
		var targetAccountJoin = root.join(TransactionEntity_.targetAccount, JoinType.LEFT);
		
		var yearMonth = YearMonth.parse(month);
		var start = yearMonth.atDay(1).atStartOfDay();
		var end = yearMonth.atEndOfMonth().plusDays(1).atStartOfDay();
		
		predicates.add(cb.equal(accountJoin.get(AccountEntity_.user).get(UserEntity_.username), username));
		predicates.add(cb.greaterThanOrEqualTo(root.get(TransactionEntity_.issuedAt), start));
		predicates.add(cb.lessThan(root.get(TransactionEntity_.issuedAt), end));
		
		if(null != amount) {
			predicates.add(cb.greaterThanOrEqualTo(root.get(TransactionEntity_.amount), amount));
		}
		
		if(StringUtils.hasText(keyword)) {
			String pattern = keyword.toLowerCase().concat("%");
			predicates.add(cb.or(
						cb.like(accountJoin.get(AccountEntity_.name), pattern),
						cb.like(categoryJoin.get(CategoryEntity_.name), pattern),
						cb.like(targetAccountJoin.get(AccountEntity_.name), pattern)
					));
		}
		
		return cb.and(predicates.toArray(i -> new Predicate[i]));
	}
	
}









