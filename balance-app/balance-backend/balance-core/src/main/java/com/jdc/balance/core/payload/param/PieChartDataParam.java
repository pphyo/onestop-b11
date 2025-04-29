package com.jdc.balance.core.payload.param;

import java.time.YearMonth;
import java.util.ArrayList;

import com.jdc.balance.core.model.entity.CategoryEntity;
import com.jdc.balance.core.model.entity.CategoryEntity_;
import com.jdc.balance.core.model.entity.TransactionEntity_;
import com.jdc.balance.core.model.entity.UserEntity_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record PieChartDataParam(
		YearMonth month,
		Boolean income
	) {

	public Predicate where(CriteriaBuilder cb, Root<CategoryEntity> root, String username) {
		var predicates = new ArrayList<Predicate>();
		
		var userJoin = root.join(CategoryEntity_.user, JoinType.INNER);
		var transactionJoin = root.join(CategoryEntity_.transactions, JoinType.INNER);
		
		var start = month.atDay(1).atStartOfDay();
		var end = month.atEndOfMonth().plusDays(1).atStartOfDay();
		
		predicates.add(cb.equal(userJoin.get(UserEntity_.username), username));
		predicates.add(cb.greaterThanOrEqualTo(
				transactionJoin.get(TransactionEntity_.issuedAt), start));
		predicates.add(cb.lessThan(transactionJoin.get(TransactionEntity_.issuedAt), end));
		predicates.add(cb.equal(root.get(CategoryEntity_.income), income));
		
		return cb.and(predicates.toArray(size -> new Predicate[size]));
	}

}
