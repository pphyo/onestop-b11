package com.jdc.balance.core.payload.output;

import com.jdc.balance.core.model.entity.CategoryEntity;

public record CategoryOutput(
			Long id,
			String name,
			Boolean icome,
			String icon
		) {

	public static CategoryOutput from(CategoryEntity entity) {
		return new CategoryOutput(entity.getId(), entity.getName(), 
				entity.getIncome(), entity.getIcon());
	}

}
