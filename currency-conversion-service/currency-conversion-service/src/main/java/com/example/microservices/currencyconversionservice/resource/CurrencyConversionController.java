package com.example.microservices.currencyconversionservice.resource;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.currencyconversionservice.config.CurrencyExchangeProxy;
import com.example.microservices.currencyconversionservice.domain.CurrencyConversion;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion caulculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,currencyConversion.getConversionMultiple(), quantity.multiply(currencyConversion.getConversionMultiple()),currencyConversion.getEnvironment());
	}
	
	@GetMapping("/up")
	public String up() {
		return "up";
	}
	
}
