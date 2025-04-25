package com.jdc.balance.core.payload.param;

import java.math.BigDecimal;

public record TransactionParam(String month, BigDecimal amount, String keyword) {

}
