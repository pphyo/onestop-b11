package com.jdc.balance.core.payload.output;

import java.math.BigDecimal;

public record PieChartDataOutput(
			String name,
			BigDecimal data,
			String fill
		) {

}
