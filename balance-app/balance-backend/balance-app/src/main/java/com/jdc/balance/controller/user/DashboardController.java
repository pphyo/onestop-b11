package com.jdc.balance.controller.user;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.core.payload.BalancePayload;
import com.jdc.balance.core.payload.output.PieChartDataOutput;
import com.jdc.balance.core.payload.param.PieChartDataParam;
import com.jdc.balance.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/dashboards")
@RequiredArgsConstructor
public class DashboardController {
	
	private final DashboardService dashboardService;
	
	public BalancePayload<List<PieChartDataOutput>> searchPieChartDataByCategoryType(PieChartDataParam param) {
		return BalancePayload.success(dashboardService.searchPieChartData(param));
	}

}
