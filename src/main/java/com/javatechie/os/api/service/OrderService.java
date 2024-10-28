package com.javatechie.os.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javatechie.os.api.common.Payment;
import com.javatechie.os.api.common.TransactionRequest;
import com.javatechie.os.api.common.TransactionResponse;
import com.javatechie.os.api.entity.Order;
import com.javatechie.os.api.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public TransactionResponse saveOrder(TransactionRequest transactinorder) {
		String response = "";
		Order order = transactinorder.getOrder();
		Payment payment = transactinorder.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		Payment paymentResponse = this.restTemplate.postForObject("http://localhost:9191/payment/doPayment", payment, Payment.class);
		response = paymentResponse.getPaymentStatus().equalsIgnoreCase("success") ? "payment processing success and order placed ": "there is a failure in payment api order added to cart";
//		return repository.saveAndFlush(order);
		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
	}
}
