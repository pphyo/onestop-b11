package com.jdc.balance.core.payload.param;

import java.util.ArrayList;
import java.util.List;

import com.jdc.balance.core.model.entity.IconEntity;
import com.jdc.balance.core.model.entity.IconEntity_;
import com.jdc.balance.core.model.entity.consts.IconFilterType;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record IconParam(Boolean account, IconFilterType filter) {

	public Predicate where(CriteriaBuilder cb, Root<IconEntity> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(null != account) {
			var accountPred = cb.equal(root.get(IconEntity_.account), account);
			predicates.add(accountPred);
		}
		
		if(null != filter) {
			var incomePred = cb.equal(root.get(IconEntity_.filter), filter);
			predicates.add(incomePred);
		}
		
		return cb.and(predicates.toArray(i -> new Predicate[i]));
	}
	
}
