package com.jdc.balance.core.payload.param;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.jdc.balance.core.model.entity.AccountEntity_;
import com.jdc.balance.core.model.entity.TransactionEntity;
import com.jdc.balance.core.model.entity.TransactionEntity_;
import com.jdc.balance.core.model.entity.UserEntity_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record TransactionParam(String month, BigDecimal amount, String keyword) {

	public Predicate where(
			CriteriaBuilder cb,
			Root<TransactionEntity> root,
			String username) {
		var predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.equal(root.get(TransactionEntity_.account)
					.get(AccountEntity_.user).get(UserEntity_.username), root));
		
		return cb.and(predicates.toArray(i -> new Predicate[i]));
	}
	
}
