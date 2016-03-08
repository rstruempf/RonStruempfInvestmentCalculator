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
    public void whenPaymentZeroFVIsZero() throws Exception {
        assertEquals(calculator.futureValue(0,0,0), 0.0, 0.0001);
    }
}