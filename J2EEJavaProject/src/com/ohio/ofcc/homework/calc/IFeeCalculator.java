/**
 * 
 */
package com.ohio.ofcc.homework.calc;

import java.util.Map;

/**
 * @author John_M_Yount
 *
 */
public interface IFeeCalculator 
{
	/*	IFeeCalculator Interface: 4 methods, each corresponding to a separate Use Case, #1 - #4.
	 * 
	 */
	// Overloaded method declarations:
	public float calculateFee(float invoiceAmt, int useCase); // Use Cases #1 & #2
	public float calculateFee(float invoiceAmt, int useCase, Map<Integer, Integer> arbCritMap); // Use Case #3
	public float calculateFee(float invoiceAmt, int useCase, float costBasis);  // Use Case #4
	public float calculateFee(float invoiceAmt, int useCase, Map<Integer, Integer> arbCritMap, float costBasis); // Use Case #4
}
