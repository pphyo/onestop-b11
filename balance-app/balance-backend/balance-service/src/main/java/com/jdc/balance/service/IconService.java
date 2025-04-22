package com.jdc.balance.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.model.entity.IconEntity;
import com.jdc.balance.core.model.entity.IconEntity_;
import com.jdc.balance.core.payload.input.IconInput;
import com.jdc.balance.core.payload.output.IconOutput;
import com.jdc.balance.core.payload.param.IconParam;
import com.jdc.balance.core.util.BalanceUtil;
import com.jdc.balance.repository.entity.IconRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IconService {
	
	private final IconRepository iconRepo;
	
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
	public List<IconOutput> search(IconParam params) {
		
		Function<CriteriaBuilder, CriteriaQuery<IconEntity>> query = cb -> {
			var cq = cb.createQuery(IconEntity.class);
			var root = cq.from(IconEntity.class);
			
			cq.select(root);
			cq.where(params.where(cb, root));
			cq.orderBy(cb.asc(root.get(IconEntity_.CREATED_AT)));
			
			return cq;
		};
		
		return iconRepo.search(query).stream().map(IconOutput::from).toList();
	}
	
	@Transactional(readOnly = true)
	public IconOutput searchById(Long id) {
		return IconOutput.from(
				iconRepo.findById(id)
					.orElseThrow(() -> BalanceUtil.notFoundWithId("icon", id)));
	}

}
