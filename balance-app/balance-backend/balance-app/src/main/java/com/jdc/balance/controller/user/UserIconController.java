package com.jdc.balance.controller.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.core.payload.BalancePayload;
import com.jdc.balance.core.payload.output.IconOutput;
import com.jdc.balance.core.payload.param.IconParam;
import com.jdc.balance.service.IconService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user/icons")
@RequiredArgsConstructor
public class UserIconController {
	
	private final IconService iconService;
	
	@GetMapping
	public BalancePayload<List<IconOutput>> searchIcon(IconParam params) {
		return BalancePayload.success(iconService.search(params));
	}
	
	@GetMapping("{id}")
	public BalancePayload<IconOutput> searchIconById(@PathVariable Long id) {
		return BalancePayload.success(iconService.searchById(id));
	}

}
