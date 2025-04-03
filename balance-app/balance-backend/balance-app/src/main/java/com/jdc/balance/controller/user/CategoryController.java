package com.jdc.balance.controller.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.core.payload.BalancePayload;
import com.jdc.balance.core.payload.input.CategoryInput;
import com.jdc.balance.core.payload.output.CategoryOutput;
import com.jdc.balance.core.payload.param.CategoryParam;
import com.jdc.balance.service.entity.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user/categories")
@RequiredArgsConstructor
public class CategoryController {
	
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryOutput> createCategory(
			@Validated @RequestBody CategoryInput input, 
			BindingResult result
		) {
		return new ResponseEntity<CategoryOutput>(categoryService.save(input), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<CategoryOutput> updateCategory(
			@PathVariable Long id, 
			@Validated @RequestBody CategoryInput input, 
			BindingResult result) {
		return new ResponseEntity<CategoryOutput>(categoryService.update(id, input), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteCategory(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(categoryService.delete(id), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public BalancePayload<CategoryOutput> searchCategoryById(@PathVariable Long id) {
		return BalancePayload.success(categoryService.searchById(id));
	}
	
	@GetMapping
	public BalancePayload<List<CategoryOutput>> searchCategory(CategoryParam param) {
		return BalancePayload.success(categoryService.search(param));
	}

}
