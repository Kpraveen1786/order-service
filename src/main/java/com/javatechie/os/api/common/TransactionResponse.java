package com.javatechie.os.api.common;

import com.javatechie.os.api.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

	private Order order;
	private double amout;
	private String transactionId;
	private String message;
}