package com.jdc.balance.service;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.model.entity.CategoryEntity;
import com.jdc.balance.core.model.entity.CategoryEntity_;
import com.jdc.balance.core.model.entity.TransactionEntity_;
import com.jdc.balance.core.payload.output.PieChartDataOutput;
import com.jdc.balance.core.payload.output.TotalAmountByCategoryNameOutput;
import com.jdc.balance.core.payload.param.PieChartDataParam;
import com.jdc.balance.repository.entity.CategoryRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardService {
	
	private final CategoryRepository categoryRepo;

	public List<PieChartDataOutput> searchPieChartData(PieChartDataParam param) {
		
		List<PieChartDataOutput> result = new LinkedList<>();
		
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Function<CriteriaBuilder, CriteriaQuery<TotalAmountByCategoryNameOutput>> query = cb -> {
			var cq = cb.createQuery(TotalAmountByCategoryNameOutput.class);
			var root = cq.from(CategoryEntity.class);
			
			cq.multiselect(
					root.get(CategoryEntity_.name),
					cb.sum(root.join(CategoryEntity_.transactions).get(TransactionEntity_.amount))
				);
			
			cq.where(param.where(cb, root, username));
			cq.groupBy(root.get(CategoryEntity_.name));
			
			return cq;
		};
		
		var list = categoryRepo.search(query);
		
		for(int i = 0, l = list.size(); i < l; i ++) {
			result.add(new PieChartDataOutput(
					list.get(i).name(),
					list.get(i).totalAmount(),
					"var(--char-%s)".formatted(i + 1)
					));
		}
		
		return result;
	}
	
}
