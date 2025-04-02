package com.jdc.balance.core.payload.input;

import java.util.function.Function;

import com.jdc.balance.core.model.entity.CategoryEntity;
import com.jdc.balance.core.model.entity.IconEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryInput(
			@NotBlank(message = "Category name required!")
			String name,
			@NotNull(message = "Category income required!")
			Boolean income,
			Long iconId
		) {

	public CategoryEntity entity(Function<Long, IconEntity> iconMapper) {
		var entity = new CategoryEntity();
		entity.setName(name);
		entity.setIncome(income);
		entity.setIcon(iconMapper.apply(iconId));
		return entity;
	}

}
