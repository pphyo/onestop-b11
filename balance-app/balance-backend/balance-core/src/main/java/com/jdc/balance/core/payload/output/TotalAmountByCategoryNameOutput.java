package com.jdc.balance.core.payload.output;

import java.math.BigDecimal;

public record TotalAmountByCategoryNameOutput(String name, BigDecimal totalAmount) {

}
