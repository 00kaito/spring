package com.creditworthiness.creditworthiness.Controllers;

import org.junit.Test;

import static org.junit.Assert.*;


public class CreditWorthinessServiceTest {
    private static final double DELTA = 1e-15;

    @Test
    public void maxCreditValuation() throws Exception {
        CreditWorthinessService test = new CreditWorthinessService();
        assertEquals(5000.0, test.maxCreditValuation(1000, 5000,5, 0), DELTA);
        assertEquals(30000.0, test.maxCreditValuation(2500, 15000,12, 0), DELTA);
        assertEquals(9600.0, test.maxCreditValuation(2500, 15000,12, 1700), DELTA);
    }

    @Test
    public void minTime() throws Exception {
    }

    @Test
    public void hasCreditworthiness() throws Exception {
    }

}