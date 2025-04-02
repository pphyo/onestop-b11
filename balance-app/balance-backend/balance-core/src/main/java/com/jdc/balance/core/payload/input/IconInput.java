package com.jdc.balance.core.payload.input;

import com.jdc.balance.core.model.entity.IconEntity;

import jakarta.validation.constraints.NotBlank;

public record IconInput(
			@NotBlank(message = "Please provide icon path!")
			String path
		) {

	public IconEntity entity() {
		var entity = new IconEntity();
		entity.setPath(path);
		return entity;
	}

}
