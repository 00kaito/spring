package com.creditworthiness.creditworthiness.Models;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreditEvaluationForm {
    Double income = 0.0;
    Double amount = 0.0;
    Integer time = 1;
    Double liabilities = 0.0;

    public CreditEvaluationForm() {

    }

    public CreditEvaluationForm(double income, double amount, int time, double liabilities) {
        this.income = income;
        this.amount = amount;
        this.time = time;
        this.liabilities = liabilities;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTime() {
        if (time == null) {
            this.time=1;

        }
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Double getLiabilities() {
        if (liabilities == null) {
            this.liabilities=0.0;

        }
        return liabilities;
    }

    public void setLiabilities(Double liabilities) {
        this.liabilities = liabilities;
    }
}
