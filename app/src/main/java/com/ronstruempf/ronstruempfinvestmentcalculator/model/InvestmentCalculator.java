package com.ronstruempf.ronstruempfinvestmentcalculator.model;

/**
 * Investment calculator
 *
 * Created by Ron on 3/7/2016.
 */
public class InvestmentCalculator {

    public double futureValue(double P, double r, int n) {
        if (Math.round(r*10000) == 0) {
            return P*n;
        }
        return P*(Math.pow(r+1,n) - 1)/r;
    }
}
