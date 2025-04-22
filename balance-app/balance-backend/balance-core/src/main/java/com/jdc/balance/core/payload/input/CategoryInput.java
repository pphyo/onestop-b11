package com.jdc.balance.core.payload.input;

import java.util.function.Function;

import com.jdc.balance.core.model.entity.CategoryEntity;
import com.jdc.balance.core.model.entity.IconEntity;
import com.jdc.balance.core.model.entity.UserEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryInput(
			@NotBlank(message = "Category name required!")
			String name,
			@NotNull(message = "Category income required!")
			Boolean income,
			@NotNull(message = "Category icon required!")
			Long iconId
		) {

	public CategoryEntity entity(Function<Long, IconEntity> iconMapper, UserEntity user) {
		var entity = new CategoryEntity();
		entity.setName(name);
		entity.setIncome(income);
		entity.setIcon(iconMapper.apply(iconId));
		entity.setUser(user);
		return entity;
	}

}
