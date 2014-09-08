/**
 * 
 */
package com.ohio.ofcc.homework.client;

import java.util.HashMap;
import java.util.Map;

import com.ohio.ofcc.homework.calc.IFeeCalculator;
import com.ohio.ofcc.homework.calc.impl.FeeCalculatorImpl;

/**
 * @author John_M_Yount
 * 
 * Client class to call the overloaded methods in the IFeeCalculator Interface.
 *
 */
public class FeeCalculatorClient {

	/**
	 * @param args
	 * 
	 * Main method, divided up into four separate overloaded method declarations, each one
	 * corresponding to a separate Use Case.
	 */
	public static void main(String[] args) {
		IFeeCalculator feeCalc = new FeeCalculatorImpl();
		float retVal = 0;
		Map<Integer, Integer> arbCritMap = new HashMap<Integer, Integer>();
		
		//UseCase #1: Parameters are Invoice Value and Use Case Number...
		// Passing-in $250000 as the invoice amount, 1 as Use Case #1.  
		retVal = feeCalc.calculateFee(250000, 1); 
		System.out.println("Use Case #1 ----> The final The calculated value:" +retVal);
		
		// UseCase #2: Parameters are Invoice Value and Use Case Number...
		// Passing-in $750000 as the invoice amount, 2 as Use Case #2.  
		retVal = feeCalc.calculateFee(750000, 2); 
		System.out.println("Use Case #2 ----> The final The calculated value:" +retVal);
		
		// UseCase #3: Parameters are Invoice Amount, Monetary Ranges, Percentage Ranges & Use Case Number...
		// Passing in $150000 as the bottom monetary range, 25 as the percentage rate, putting this key-value pair into the map:
		arbCritMap.put(150000, 25);
		// Passing in $550000 as the middle monetary range, 15 as the percentage rate, putting this key-value pair into the map:
		arbCritMap.put(550000, 15);
		// Passing in $750000 as the top monetary range, 10 as the percentage rate, putting this key-value pair into the map:
		arbCritMap.put(750000, 10);
		
		// Passing in $900000 as the invoice amount, 3 as the Use Case Number:
		retVal = feeCalc.calculateFee(900000, 3, arbCritMap); 
		System.out.println("Use Case #3 ----> The final The calculated value:" +retVal);
				
		// UseCase #4: Parameters are Cost Basis, Invoice Amount & Use Case Number...
		// Passing in $250000 as the New Invoice Amount, 4 as the Use case number and $600000 as the existing Cost Basis:
		retVal = feeCalc.calculateFee(200000, 4, 600000); 
		System.out.println("Use Case #4 ----> The final calculated value:" +retVal);
	}	
	
}
