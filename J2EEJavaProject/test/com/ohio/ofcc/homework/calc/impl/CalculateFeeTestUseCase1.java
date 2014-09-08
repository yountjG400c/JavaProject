package com.ohio.ofcc.homework.calc.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import com.ohio.ofcc.homework.calc.impl.FeeCalculatorImpl;

import org.junit.Test;

public class CalculateFeeTestUseCase1 {

	@Test
	public void testCalculateFeeCase1() {
		//UseCase #1 test: Parameters are Invoice Value and Use Case Number...
		// Passing-in $450000 as the invoice amount, 1 as Use Case #1.  
		FeeCalculatorImpl feeCalculatorImpl = new FeeCalculatorImpl();
		feeCalculatorImpl.calculateFee(450000, 1);
	}

	@Test
	public void testCalculateFeeCase2Float() {
		// UseCase #2 test: Parameters are Invoice Value and Use Case Number...
		// Passing-in $800000 as the invoice amount, 2 as Use Case #2.  
		FeeCalculatorImpl feeCalculatorImpl = new FeeCalculatorImpl();
		feeCalculatorImpl.calculateFee(800000, 2);
	}

	@Test
	public void testCalculateFeeCase2FloatMapOfIntegerInteger() {
		Map<Integer, Integer> arbCritMap = new HashMap<Integer, Integer>();
		
		// UseCase #3 test: Parameters are Invoice Amount, Monetary Ranges, Percentage Ranges & Use Case Number...
		// Passing in $175000 as the bottom monetary range, 30 as the percentage rate, putting this key-value pair into the map:
		arbCritMap.put(175000, 30);
		// Passing in $600000 as the middle monetary range, 25 as the percentage rate, putting this key-value pair into the map:
		arbCritMap.put(600000, 25);
		// Passing in $800000 as the top monetary range, 15 as the percentage rate, putting this key-value pair into the map:
		arbCritMap.put(800000, 15);
		
		FeeCalculatorImpl feeCalculatorImpl = new FeeCalculatorImpl();
		feeCalculatorImpl.calculateFee(950000, 3, arbCritMap);
	}

	@Test
	public void testCalculateFeeCase4() {
		FeeCalculatorImpl feeCalculatorImpl = new FeeCalculatorImpl();
		// UseCase #4 test: Parameters are Cost Basis, Invoice Amount & Use Case Number...
		// Passing in $300000 as the New Invoice Amount, 4 as the Use case number and $700000 as the existing Cost Basis:
		feeCalculatorImpl.calculateFee(300000, 4, 700000);
	}

}
