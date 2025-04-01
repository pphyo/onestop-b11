package com.jdc.balance.core.util;

import com.jdc.balance.core.exception.BalanceBaseException;
import com.jdc.balance.core.exception.BalanceBusinessException;

public class BalanceUtil {
	
	public static <ID> BalanceBaseException notFoundWithId(String domain, ID id) {
		return new BalanceBusinessException(
			"No %s found with id %s."
				.formatted(domain, id.toString())
		);
	}

}
