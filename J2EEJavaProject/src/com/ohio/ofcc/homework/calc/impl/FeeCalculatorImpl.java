/**
 * 
 */
package com.ohio.ofcc.homework.calc.impl;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.ohio.ofcc.homework.calc.IFeeCalculator;

/**
 * @author John_M_Yount
 *
 */
public class FeeCalculatorImpl implements IFeeCalculator {

	/* (non-Javadoc)
	 * @see com.ohio.ofcc.homework.calc.IFeeCalculator#calculateFee(int, int)
	 */
	float lowInvoiceValue = 100000;
	float midInvoiceValue = 200000;
	float highInvoiceValue = 500000;
	
	float twentyPercentValue = (20/100);
	float tenPercentValue = (10/100);
	float fivePercentValue = (5/100);
	
	@Override
	public float calculateFee(float invoiceAmt, int useCase, Map<Integer, Integer> arbCritMap, float costBasis) 
	{
		
		float retVal = 0;
		
		switch (useCase)
		{
			case 1: retVal = calculateFeeCase1(invoiceAmt);break;
			case 2: retVal = calculateFeeCase2(invoiceAmt);break;
			case 3: {
				if(null ==  arbCritMap){
					retVal = calculateFeeCase2(invoiceAmt);					
				}else{
					retVal = calculateFeeCase2(invoiceAmt,arbCritMap);
				}				
			}
			break;
			case 4: retVal = calculateFeeCase4(invoiceAmt, costBasis);break;
			
		}
		
		
		return retVal;
	}
	
	@Override
	public float calculateFee(float invoiceAmt, int useCase){
		return calculateFee(invoiceAmt, useCase, null); 
	}
	
	@Override
	public float calculateFee(float invoiceAmt, int useCase, float costBasis){
		return calculateFee(invoiceAmt, useCase, null, costBasis); 
	}
	
	@Override
	public float calculateFee(float invoiceAmt, int useCase, Map<Integer, Integer> arbCritMap){
		return calculateFee(invoiceAmt, useCase, arbCritMap, 0); 
	}
	
	public float calculateFeeCase1(float invoiceAmt)
	{
		float retVal = 0;
		
		if(invoiceAmt <= lowInvoiceValue)
		{
			retVal = (invoiceAmt * twentyPercentValue);
		} else {
			retVal = (((invoiceAmt - lowInvoiceValue) * tenPercentValue));
			System.out.println(retVal);
			retVal = (midInvoiceValue +((invoiceAmt - lowInvoiceValue) * tenPercentValue));
			System.out.println(retVal);
		}		
		
		return retVal;
		
	}
	
	public float calculateFeeCase2(float invoiceAmt)
	{
		float retVal = 0;
		
		if(invoiceAmt <= highInvoiceValue){
			retVal = calculateFeeCase1(invoiceAmt);			
		} else {
			retVal = calculateFeeCase1(highInvoiceValue);
			retVal = retVal + ((invoiceAmt - highInvoiceValue) * fivePercentValue);
		}
		
		return retVal;
		
	}
	
	/**
	 * This method caluculates arbitrary ranges.
	 * @param invoiceAmt
	 * @param rangeCriteria (key- invoiceCeiling and value- invoiceRate)
	 * @return
	 */
	public float calculateFeeCase2(float invoiceAmt, Map<Integer, Integer> rangeCriteria)
	{
		float retVal = 0;
		int prevCeiling = 0;
		Set<Integer> invoiceCeilingSet = rangeCriteria.keySet();
		TreeSet<Integer> sortedInvoiceCeiling = new TreeSet<Integer>(invoiceCeilingSet);
		for(Integer invoiceCeiling:sortedInvoiceCeiling){
			System.out.println("The invoiceCeiling "+invoiceCeiling);
			System.out.println("The value from map - "+ rangeCriteria.get(invoiceCeiling));
			if(invoiceAmt<=invoiceCeiling){				
				System.out.println("In if criteria");
				int invoiceCel = invoiceCeiling.intValue() - prevCeiling;
				int feeRate = rangeCriteria.get(invoiceCeiling).intValue();
				System.out.println("In if criteria: invoiceCel "+invoiceCel);
				System.out.println("In if criteria: FeeRate "+feeRate);
				float calcVal = invoiceCel * feeRate/100;
				System.out.println("In if criteria: Calculated value: "+calcVal);
				retVal = retVal + calcVal;
				System.out.println("In if criteria: return value: "+retVal);
				retVal = retVal + ((invoiceAmt-prevCeiling) * (rangeCriteria.get(invoiceCeiling).intValue())/100);				
			}else{
				System.out.println("In else criteria");
				int invoiceCel = invoiceCeiling.intValue();
				int feeRate = rangeCriteria.get(invoiceCeiling).intValue();
				System.out.println("In else criteria: invoiceCel "+invoiceCel);
				System.out.println("In else criteria: FeeRate "+feeRate);
				float calcVal = invoiceCel * feeRate/100;
				System.out.println("In else criteria: Calculated value: "+calcVal);
				retVal = retVal + calcVal;
				System.out.println("In else criteria: return value: "+retVal);
			}
			prevCeiling = invoiceCeiling;
			System.out.println("Final return value -----> "+retVal);
		}
		return retVal;
		
	}
	
	
	public float calculateFeeCase4(float invoiceAmt, float costBasis)
	{
		float retVal = 0;
		float amtTotal = invoiceAmt+costBasis;
		if(amtTotal < 100000){
			//whole amount 20 %
			retVal = invoiceAmt * 20/100;
		}else if(amtTotal < 500000){			
			if(costBasis<100000){
				//part 1 amount 20 %
				retVal = (invoiceAmt*20/100);
				//part 2 amount 10 %
				retVal = retVal + ((amtTotal - 100000)*10/100);
			}else{
				//whole amt 10%
				retVal = invoiceAmt * 10/100;
			}
		}else {
			//amt total is greater that 500000
			if(costBasis<=500000){
				if(costBasis<=100000){
					//part 1 amount 20%
					retVal = (invoiceAmt*20/100);
					//part 2 amount 10%
					retVal = retVal + ((amtTotal - 100000)*10/100);
					//part 3 amount 5%
					retVal = retVal + ((amtTotal - 500000)*5/100);
				}else{
					//costBasis is between 100000 and 500000					
					//part 1 amount 10%
					retVal = (invoiceAmt*10/100);
					//part 1 amount 5%
					retVal = retVal + (amtTotal - 500000)*5/100;
				}
			}else{
				//whole amt as 5%
				retVal = invoiceAmt * 10/100;
			}
			
			
		}
		return retVal;
		
	}
	
	

}
