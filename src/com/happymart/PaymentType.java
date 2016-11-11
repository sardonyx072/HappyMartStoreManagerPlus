package com.happymart;

import java.io.Serializable;

public enum PaymentType implements Serializable {
	Cash,
	Credit,
	Debit,
	Check;
}
