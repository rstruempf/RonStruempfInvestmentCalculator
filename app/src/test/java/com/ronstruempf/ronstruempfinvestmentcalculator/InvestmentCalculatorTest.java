package com.ronstruempf.ronstruempfinvestmentcalculator;

import com.ronstruempf.ronstruempfinvestmentcalculator.model.InvestmentCalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class InvestmentCalculatorTest {

    private InvestmentCalculator calculator = new InvestmentCalculator();

    @Test
    public void whenAllZeroFVIsZero() throws Exception {
        assertEquals(0.0, calculator.futureValue(0,0,0), 0.00001);
    }

    @Test
    public void whenPayment1000N10R3pFVIs11463_88() throws Exception {
        assertEquals(11463.88, calculator.futureValue(1000,0.03,10), 0.001);
    }

    @Test
    public void whenPaymentZeroFVZero() throws Exception {
        assertEquals(0.0, calculator.futureValue(0,0.03,10), 0.0001);
    }

    @Test
    public void whenRateZeroFVRxN() throws Exception {
        assertEquals(10000, calculator.futureValue(1000,0,10), 0.0001);
    }

    @Test
    public void whenN0FV0() throws Exception {
        assertEquals(0, calculator.futureValue(1000,0.03,0), 0.0001);
    }

}