package com.jdc.balance.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.core.payload.BalancePayload;
import com.jdc.balance.core.payload.input.SettingInput;
import com.jdc.balance.core.payload.output.SettingOutput;
import com.jdc.balance.service.SettingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user/settings")
@RequiredArgsConstructor
public class SettingController {
	
	private final SettingService settingService;
	
	@PostMapping
	public ResponseEntity<SettingOutput> createSetting(
						@Validated @RequestBody
						SettingInput input,
						BindingResult result) {
		return new ResponseEntity<SettingOutput>(settingService.save(input), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<SettingOutput> updateSetting(
						@PathVariable
						Long id,
						@Validated @RequestBody
						SettingInput input,
						BindingResult result) {
		return new ResponseEntity<SettingOutput>(settingService.update(id, input), HttpStatus.OK);
	}
	
	@GetMapping("current")
	public BalancePayload<SettingOutput> searchCurrentSetting() {
		return BalancePayload.success(settingService.searchForCurrentUser());
	}

}
