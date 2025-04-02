package com.jdc.balance.service.entity;

import static com.jdc.balance.core.util.BalanceUtil.notFoundWithId;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.model.entity.CategoryEntity;
import com.jdc.balance.core.payload.input.CategoryInput;
import com.jdc.balance.core.payload.output.CategoryOutput;
import com.jdc.balance.core.payload.param.CategoryParam;
import com.jdc.balance.repository.entity.CategoryRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

	private CategoryRepository categoryRepo;

	public CategoryOutput save(CategoryInput input) {
		return CategoryOutput.from(categoryRepo.save(input.entity()));
	}

	public CategoryOutput update(Long id, CategoryInput input) {
		var category = categoryRepo.findById(id).map(c -> {
			c.setName(input.name());
			c.setIncome(input.income());
			c.setIcon(input.icon());
			return c;
		}).orElseThrow(() -> notFoundWithId("category", id));
		return CategoryOutput.from(category);
	}

	public boolean delete(Long id) {
		categoryRepo.deleteById(id);
		return categoryRepo.findById(id).isEmpty();
	}

	@Transactional(readOnly = true)
	public CategoryOutput searchById(Long id) {
		return CategoryOutput.from(
					categoryRepo
						.findById(id)
						.orElseThrow(() -> notFoundWithId("category", id))
				);
	}

	@Transactional(readOnly = true)
	public List<CategoryOutput> search(CategoryParam param) {
		Function<CriteriaBuilder, CriteriaQuery<CategoryEntity>> query = cb -> {
			var cq = cb.createQuery(CategoryEntity.class);
			var root = cq.from(CategoryEntity.class);

			cq.select(root);
			cq.where(param.where(cb, root));
			cq.orderBy(cb.desc(root.get("id")));

			return cq;
		};

		return categoryRepo.search(query).stream().map(CategoryOutput::from).toList();
	}

}
