package com.jdc.balance.service.entity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.payload.input.IconInput;
import com.jdc.balance.core.payload.output.IconOutput;
import com.jdc.balance.core.util.BalanceUtil;
import com.jdc.balance.repository.entity.IconRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IconService {
	
	private IconRepository iconRepo;
	
	public IconOutput save(IconInput input) {
		return IconOutput.from(iconRepo.save(input.entity()));
	}
	
	public IconOutput update(Long id, IconInput input) {
		return IconOutput.from(iconRepo.findById(id).map(e -> {
			e.setPath(input.path());
			return e;
		}).orElseThrow(() -> BalanceUtil.notFoundWithId("icon", id)));
	}
	
	@Transactional(readOnly = true)
	public IconOutput searchById(Long id) {
		return IconOutput.from(
				iconRepo.findById(id)
					.orElseThrow(() -> BalanceUtil.notFoundWithId("icon", id)));
	}

}
