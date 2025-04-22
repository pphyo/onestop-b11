package com.jdc.balance.core.payload.output;

import com.jdc.balance.core.model.entity.IconEntity;

public record IconOutput(Long id, String name, String path) {

	public static IconOutput from(IconEntity entity) {
		return entity == null ? null : new IconOutput(entity.getId(), entity.getName(), entity.getPath());
	}

}
