package com.jdc.balance.core.payload.param;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.jdc.balance.core.model.entity.AccountEntity;
import com.jdc.balance.core.model.entity.AccountEntity_;
import com.jdc.balance.core.model.entity.UserEntity_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record AccountParam(String name, BigDecimal amount) {

	public Predicate where(CriteriaBuilder cb, Root<AccountEntity> root, String username) {
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(cb.equal(cb.lower(root.get(AccountEntity_.user).get(UserEntity_.username)), username));

		if(StringUtils.hasLength(name)) {
			var namePred = cb.like(cb.lower(root.get(AccountEntity_.name)), name.toLowerCase().concat("%"));
			predicates.add(namePred);
		}
		
		if(null != amount && amount.compareTo(new BigDecimal(0)) > 0) {
			var amountPred = cb.greaterThanOrEqualTo(root.get(AccountEntity_.amount), amount);
			predicates.add(amountPred);
		}
		
		return cb.and(predicates.toArray(value -> new Predicate[value]));
	}

}
