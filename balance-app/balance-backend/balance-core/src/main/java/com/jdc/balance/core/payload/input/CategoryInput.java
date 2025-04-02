package com.jdc.balance.core.payload.input;

import com.jdc.balance.core.model.entity.CategoryEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryInput(
			@NotBlank(message = "Category name required!")
			String name,
			@NotNull(message = "Category income required!")
			Boolean income,
			String icon
		) {

	public CategoryEntity entity() {
		var entity = new CategoryEntity();
		entity.setName(name);
		entity.setIncome(income);
		entity.setIcon(icon);
		return entity;
	}

}
