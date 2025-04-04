package com.jdc.balance.core.payload.param;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.balance.core.model.entity.CurrencyEntity;
import com.jdc.balance.core.model.entity.CurrencyEntity_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record CurrencyParam(
		String name, String code
	) {

	public Predicate where(CriteriaBuilder cb, Root<CurrencyEntity> root) {
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(name)) {
			var namePredicate = cb.like(cb.lower(root.get(CurrencyEntity_.NAME)), name.toLowerCase().concat("%"));
			predicates.add(namePredicate);
		}
		
		if(StringUtils.hasLength(code)) {
			var codePredicate = cb.like(cb.lower(root.get(CurrencyEntity_.CODE)), code.toLowerCase().concat("%"));
			predicates.add(codePredicate);
		}
		
		return cb.and(predicates.toArray(value -> new Predicate[0]));
	}
	
}
