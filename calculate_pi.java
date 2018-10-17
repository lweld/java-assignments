/*
 * This program calculates the value of pi using various formulas, primitive 
 * types, the BigDecimal class and the Ramanujan summation algorithm.
 * An efficiency and accuracy report is given with each calculation.
 * 
 * Author: Liam Weld
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class calculate_pi {
	// Used to count iterations
    public static double count;
    private static Scanner screenInput = new Scanner(System.in);

    // Uses Leibniz formula with floats
    public static float piCalculatorLeibniz() {
    	float sum = 0.0f;
    	float prevSum;
    	int n = 0;
    	
    	do {
    		prevSum = sum;
    		sum += (float) ((Math.pow(-1, n) / (2 * n + 1) * 4.0));
    		n++;
    		count++;
    	} while (sum != prevSum);
    	return sum;
    }
    
    // Taylor series with floats. As a parameter, it accepts a value of X to use in calculating arctan. 
    public static float taylorSeriesFloat(float x) {
    	float sum = 0.0f;
    	float prevSum;
    	int n = 0;
    	
    	do {
    		prevSum = sum;
    		sum += (float) (((Math.pow(-1, n) / ((2 * n) + 1))) * (Math.pow(x, (2 * n + 1))));
    		n++;
    		count++;
    	} while (sum != prevSum);
    	return sum;
    }
    
    // Uses Taylor series to calculate the arctan formula with floats
    public static float arcTanPiCalculatorFloat() {
    	float termOne = taylorSeriesFloat(0.1f);
    	float termTwo = taylorSeriesFloat(1.0f / 515.0f);
    	float termThree = taylorSeriesFloat(1.0f / 239.0f);
    	float finalValue = ((8 * termOne) - (4 * termTwo) - termThree) * 4;
    	return finalValue;
    }
    
    // Taylor series with double. As a parameter, it accepts a value of X to use in calculating arctan.
    public static double taylorSeriesDouble(double x) {
    	double sum = 0.0;
    	double prevSum;
    	int n = 0;
    	
    	do {
    		prevSum = sum;
    		sum += (Math.pow(-1, n) / (2 * n + 1)) * (Math.pow(x, (2 * n + 1)));
    		n++;
    		count++;
    	} while (sum != prevSum);
    	return sum;
    }
    
    // Uses Taylor series to calculate the arctan formula with double
    public static double arcTanPiCalculatorDouble() {
    	double termOne = taylorSeriesDouble(0.1);
    	double termTwo = taylorSeriesDouble(1.0 / 515.0);
    	double termThree = taylorSeriesDouble(1.0 / 239.0);
    	double finalValue = ((8 * termOne) - (4 * termTwo) - termThree) * 4;
    	return finalValue;
    }
    
    // Uses Taylor series with double. Uses Ramanujan summation to increase accuracy.
    // As a parameter, it accepts a value of X to use in calculating arctan.
    public static double taylorSeriesDoubleModified(double x) {
    	int n = 0;
        double negSum = 0.0;
        double posSum = 0.0;
        double prevNegSum;
        double prevPosSum;
        int sign = 1;
        double twoNPlusOne;
        double term;
        double sum = 0.0;

        do {
            prevNegSum = negSum;
            prevPosSum = posSum;
            twoNPlusOne =  2 * n + 1;
            term = Math.pow(x, twoNPlusOne) / twoNPlusOne;
            if (sign > 0) {
                posSum += term;
            } else {
                negSum += term;
            }
            sign = -sign;
            n++;
            count++;
        } while (!(negSum == prevNegSum && posSum == prevPosSum));
        sum = posSum - negSum;
    	return sum;
    }
    
    // Uses Taylor series to calculate the arctan formula with double using Kahan summation
    public static double arcTanPiCalculatorDoubleModified() {
    	double termOne = taylorSeriesDoubleModified(0.1);
    	double termTwo = taylorSeriesDoubleModified(1.0 / 515.0);
    	double termThree = taylorSeriesDoubleModified(1.0 / 239.0);
    	double finalValue = ((8 * termOne) - (4 * termTwo) - termThree) * 4;
    	return finalValue;
    }
    
    // Uses BBP formula with double
    public static double piCalculatorBBP() {
    	double sum = 0.0;
    	double prevSum;
    	double n = 0.0;
    	
    	do {
    		prevSum = sum;
    		sum += (1 / Math.pow(16, n)) * ((4 / (8 * n + 1)) - (2 / (8 * n + 4)) - (1 / (8 * n + 5)) - (1 / (8 * n + 6)));
    		n++;
    		count++;
    	} while (sum != prevSum);
    	return sum;
    }
    
    // Uses BBP formula with BigDecimal. As a parameter, it accepts a number of significant digits to 
    // round to as specified by the user.
    public static BigDecimal piCalculatorBBPBig(int numDigitsDesired) {
    	BigDecimal one, two, four, five, six, eight, sixteen, sum, prevSum, k, eightMultiplyIterator;
    	BigDecimal termOneDenom, termOne, termTwoDenom, termTwo, termThreeDenom, termThree; 
    	BigDecimal termFourDenom, termFour, termFiveDenom, termFive, termsInBrackets, finalTerm;
    	
    	one = BigDecimal.ONE;
    	two = new BigDecimal("2");
    	four = new BigDecimal("4");
    	five = new BigDecimal("5");
    	six = new BigDecimal("6");
    	eight = new BigDecimal("8");
    	sixteen = new BigDecimal("16");
    	sum = BigDecimal.ZERO;
    	prevSum = BigDecimal.ZERO;
    	k = BigDecimal.ZERO;
    	int i = 0;
    	int terminateCondition;
    	
    	do {
    		prevSum = sum;
    		
    		eightMultiplyIterator = eight.multiply(k);
    		termOneDenom = sixteen.pow(i);
    		termOne = one.divide(termOneDenom, numDigitsDesired, RoundingMode.HALF_EVEN);
    		termTwoDenom = eightMultiplyIterator.add(one);
    		termTwo = four.divide(termTwoDenom, numDigitsDesired, RoundingMode.HALF_EVEN);
    		termThreeDenom = eightMultiplyIterator.add(four);
    		termThree = two.divide(termThreeDenom, numDigitsDesired, RoundingMode.HALF_EVEN);
    		termFourDenom = eightMultiplyIterator.add(five);
    		termFour = one.divide(termFourDenom, numDigitsDesired, RoundingMode.HALF_EVEN);
    		termFiveDenom = eightMultiplyIterator.add(six);
    		termFive = one.divide(termFiveDenom, numDigitsDesired, RoundingMode.HALF_EVEN);
    		
    		termsInBrackets = termTwo.subtract(termThree).subtract(termFour).subtract(termFive);
    		finalTerm = termOne.multiply(termsInBrackets);
    		sum = sum.add(finalTerm);
    		k = k.add(one);
    		i++;
    		count++;
    		terminateCondition = sum.compareTo(prevSum);
    	} while (terminateCondition != 0);
    	return sum;
    }
    
    // Aids in displaying BigDecimal numbers to the screen using 100 digits per line
    public static void displayResult(BigDecimal aNum) {
    	var asString = aNum.toString();
    	for(int i = 0; i < asString.length(); i++) {
    		System.out.print(asString.charAt(i));
    		if (i > 0 && (i + 1) % 100 == 0)
    			System.out.println();
    	}
    	System.out.println();
    }
    
    // Simplifies reporting the execution time and the number of iterations
    public static void timeIterationsReport(long start) {
        long finishTime = System.nanoTime();
        long diff = finishTime - start;
        if (diff <= 1e3)
            System.out.print("Took " + diff + " nanosec., ");
        else if (diff <= 1e6)
            System.out.print("Took " + Math.round(diff / 10.0) / 100.0 + " microsec. ");
        else if (diff <= 1e9)
        	System.out.print("Took " + Math.round(diff / 1e4) / 100.0 + " millisec. ");
        else
        	System.out.print("Took " + Math.round(diff / 1e7) / 100.0 + " sec. ");
        System.out.println("and required " + count + " iterations.");
        count = 0.0;
    }
    
    // Used to calculate and display the accuracy of a 16 digit result using the value of pi stored in the Math class
    public static void accuracyReport(double estimate) {
    	var error = 100 * (estimate - Math.PI) / Math.PI;
    	System.out.printf("Error is %.2e percent.\n\n", error);
    }

    // Accepts an int as input from the user if it is within a certain range
    public static int getInt(int low, String prompt, int high) {
        int numFromUser = 0;
        String dummy;
        boolean numericEntryOK;
        do {
            System.out.print(prompt);
            numericEntryOK = false;
            try {
                numFromUser = screenInput.nextInt();
                numericEntryOK = true;
            } catch (InputMismatchException e) {
                dummy = screenInput.nextLine();
                System.out.println(dummy + " is not an integer!");
                numFromUser = low;
            }
            // Indicate to the user why they're being prompted again.
            if (numFromUser < low || numFromUser > high) {
                System.out.println("The number is outside the legal limits.");
            }
        } while (!numericEntryOK || numFromUser < low || numFromUser > high);
        return numFromUser;
    }

    // main method
    public static void main(String[] args) {
        long startTime;
        double estimate;
        int numDigitsDesired;

        System.out.printf("Math.PI is:\n%.16f\n\n", Math.PI);
        
        // Calls Leibniz formula with float
        startTime = System.nanoTime();
        estimate = piCalculatorLeibniz();
        System.out.printf("%f - using Leibniz formula with float.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);
        
        // Calls arctan formula with float
        startTime = System.nanoTime();
        estimate = arcTanPiCalculatorFloat();
        System.out.printf("%f - using arcTan formula with float.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);
        
        // Calls arctan formula with double
        startTime = System.nanoTime();
        estimate = arcTanPiCalculatorDouble();
        System.out.printf("%f - using arcTan formula with double.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);
        
        // Calls arctan formula with double, using the Ramanujan summation
        startTime = System.nanoTime();
        estimate = arcTanPiCalculatorDoubleModified();
        System.out.printf("%f - using Modified arcTan formula with double.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);
        
        // Calls BBP formula with double
        startTime = System.nanoTime();
        estimate = piCalculatorBBP();
        System.out.printf("%f - using BBP formula with double.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);
        
        // Calls BBP formula with BigDecimal
        startTime = System.nanoTime();
        System.out.println("Using BBP formula with BigDecimals for 16 digits:");
        var estimateBigD = piCalculatorBBPBig(16);
        System.out.println(estimateBigD);
        timeIterationsReport(startTime);
        accuracyReport(estimateBigD.doubleValue());

        startTime = System.nanoTime();
        System.out.println("Using BBP formula with BigDecimals for 100 digits:");
        System.out.println(piCalculatorBBPBig(100));
        timeIterationsReport(startTime);
        System.out.println();
        
        numDigitsDesired = getInt(1000, "How many digits would you like the number to be calculated to (ie, precision)? ", 10000);
        
        startTime = System.nanoTime();
        System.out.println("Using BBP formula with BigDecimals for " + numDigitsDesired + " digits:");
        displayResult(piCalculatorBBPBig(numDigitsDesired));
        timeIterationsReport(startTime);
        
        screenInput.close();
    }
}