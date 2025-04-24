package com.jdc.balance.core.payload.param;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.jdc.balance.core.model.entity.IconEntity;
import com.jdc.balance.core.model.entity.IconEntity_;
import com.jdc.balance.core.model.entity.consts.CategoryIconFilter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record IconParam(String name, CategoryIconFilter filter, Boolean account) {

	public Predicate where(CriteriaBuilder cb, Root<IconEntity> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(StringUtils.hasText(name)) {
			predicates.add(cb.like(cb.lower(root.get(IconEntity_.name)), name.toLowerCase().concat("%")));
		}
		
		if(null != account) {
			predicates.add(cb.equal(root.get(IconEntity_.account), account));
		}
		
		if(null != filter) {
			if(!filter.equals(CategoryIconFilter.Both)) {
				predicates.add(cb.or(
						cb.equal(root.get(IconEntity_.filter), CategoryIconFilter.Both),
						cb.equal(root.get(IconEntity_.filter), filter)
					));
			} else {
				predicates.add(cb.equal(root.get(IconEntity_.filter), filter));
			}
		}
		
		return cb.and(predicates.toArray(i -> new Predicate[i]));
	}
	
}
