package com.jdc.balance.service.entity;

import static com.jdc.balance.core.util.BalanceUtil.notFoundWithId;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.balance.core.model.entity.CurrencyEntity;
import com.jdc.balance.core.payload.input.CurrencyInput;
import com.jdc.balance.core.payload.output.CurrencyOutput;
import com.jdc.balance.repository.entity.CurrencyRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyService {
	
	private final CurrencyRepository currencyRepo;

	public CurrencyOutput create(CurrencyInput input) {
		return CurrencyOutput.from(currencyRepo.save(input.entity()));
	}

	public CurrencyOutput update(Long id, CurrencyInput input) {
		return CurrencyOutput.from(
					currencyRepo.findById(id)
						.map(entity -> {
							entity.setName(input.name());
							entity.setCode(input.code());
							return entity;
						})
						.orElseThrow(() -> notFoundWithId("currency", id))
				);
	}
	
	public Boolean delete(Long id) {
		currencyRepo.delete(currencyRepo.findById(id).orElseThrow(() -> notFoundWithId("currency", id)));
		return currencyRepo.findById(id).isEmpty();
	}

	public List<CurrencyOutput> search(String name, String code) {
		
		Function<CriteriaBuilder, CriteriaQuery<CurrencyEntity>> query = cb -> {
			CriteriaQuery<CurrencyEntity> cq = cb.createQuery(CurrencyEntity.class); // create query object
			
			Root<CurrencyEntity> root = cq.from(CurrencyEntity.class); // get root object
			
			cq.select(root); // select c.id, c.name, c.code from CURRENCY;
			
			cq.where(getCurrencyPredicates(cb, root, name, code)); // select * from currency where ...
			cq.orderBy(cb.desc(root.get("id")));
			
			return cq;
		};
		
		return currencyRepo.search(query).stream()
					.map(CurrencyOutput::from).toList();
	}

	private Predicate getCurrencyPredicates(CriteriaBuilder cb, Root<CurrencyEntity> root, String name, String code) {
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(name)) {
			var namePredicate = cb.like(cb.lower(root.get("name")), name.toLowerCase().concat("%"));
			predicates.add(namePredicate);
		}
		
		if(StringUtils.hasLength(code)) {
			var codePredicate = cb.like(cb.lower(root.get("code")), code.toLowerCase().concat("%"));
			predicates.add(codePredicate);
		}
		
		return cb.and(predicates.toArray(value -> new Predicate[0]));
	}
	
}