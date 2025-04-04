package com.jdc.balance.core.payload.param;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.balance.core.model.entity.CategoryEntity;
import com.jdc.balance.core.model.entity.CategoryEntity_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record CategoryParam(
			String name,
			Boolean income
		) {

	public Predicate where(CriteriaBuilder cb, Root<CategoryEntity> root) {
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(name)) {
			var namePred = cb.like(cb.lower(root.get(CategoryEntity_.NAME)), name.toLowerCase().concat("%"));
			predicates.add(namePred);
		}
		
		if(null != income) {
			var incomePred = cb.equal(root.get(CategoryEntity_.INCOME), income);
			predicates.add(incomePred);
		}
		
		return cb.and(predicates.toArray(i -> new Predicate[i]));
	}

}
