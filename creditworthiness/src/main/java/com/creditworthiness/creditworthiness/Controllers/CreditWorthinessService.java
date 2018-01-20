package com.creditworthiness.creditworthiness.Controllers;

public class CreditWorthinessService extends Exception {
    public static double maxCreditValuation(double income, double amount, int time, double monthlyLiabilities) {
        double creditLimit = 0;
        creditLimit = (income - monthlyLiabilities) * time;
        return creditLimit;
    }

    public static int minTime(double income, double amount, int time, double monthlyLiabilities) {
        int minTime = 0;
        if (income - monthlyLiabilities <= 0)
            return minTime;
        if (income - monthlyLiabilities != 0)
            minTime = (int) amount / ((int) income - (int) monthlyLiabilities);
        if (minTime * (income - monthlyLiabilities) < amount)
            return minTime + 1;
        return minTime;
    }

    public static boolean hasCreditworthiness(double income, double amount, int time, double monthlyLiabilities) {

        if (income - monthlyLiabilities < amount / time) {
            return false;
        }
        return true;
    }

}
