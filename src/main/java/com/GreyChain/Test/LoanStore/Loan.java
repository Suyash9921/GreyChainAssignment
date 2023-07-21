package com.GreyChain.Test.LoanStore;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Getter
public class Loan {
    private String loanId;

    private String customerId;
    private String lenderId;

    private double amount;
    private double remainingAmount;
    private String paymentDate;
    private double interestPerDay;
    private String dueDate;
    private double penaltyPerDay;
    private boolean cancel;

    protected Loan(LoanBuilder lb){
        this.loanId = lb.loanId;
        this.customerId = lb.customerId;
        this.lenderId = lb.lenderId;
        this.amount = lb.amount;
        this.remainingAmount = lb.remainingAmount;
        this.paymentDate = lb.paymentDate;
        this.interestPerDay = lb.interestPerDay;
        this.dueDate = lb.dueDate;
        this.penaltyPerDay = lb.penaltyPerDay;
        this.cancel = lb.cancel;
    }

    public static LoanBuilder Builder()
    {
        return new LoanBuilder();
    }


    @NoArgsConstructor
    public static class LoanBuilder
    {

        @NotBlank(message = "Loan ID cannot be blank")
        private String loanId;

        @NotBlank(message = "Customer ID cannot be blank")
        private String customerId;
        @NotBlank(message = "Lender ID cannot be blank")
        private String lenderId;

        private double amount;
        private double remainingAmount;
        private String paymentDate;
        private double interestPerDay;
        private String dueDate;
        private double penaltyPerDay;
        private boolean cancel;

        public String getLoanId() {
            return loanId;
        }

        public LoanBuilder setLoanId(String loanId) {
            this.loanId = loanId;
            return this;
        }

        public String getCustomerId() {
            return customerId;
        }

        public LoanBuilder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public String getLenderId() {
            return lenderId;
        }

        public double getAmount() {
            return amount;
        }

        public LoanBuilder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public double getRemainingAmount() {
            return remainingAmount;
        }

        public LoanBuilder setRemainingAmount(double remainingAmount) {
            this.remainingAmount = remainingAmount;
            return this;
        }

        public String getPaymentDate() {
            return paymentDate;
        }

        public LoanBuilder setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public double getInterestPerDay() {
            return interestPerDay;
        }

        public LoanBuilder setInterestPerDay(double interestPerDay) {
            this.interestPerDay = interestPerDay;
            return this;
        }

        public String getDueDate() {
            return dueDate;
        }

        public LoanBuilder setDueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public double getPenaltyPerDay() {
            return penaltyPerDay;
        }

        public LoanBuilder setPenaltyPerDay(double penaltyPerDay) {
            this.penaltyPerDay = penaltyPerDay;
            return this;
        }

        public boolean isCancel() {
            return cancel;
        }

        public void setCancel(boolean cancel) {
            this.cancel = cancel;
        }

        public Loan build()
        {
            if (this.remainingAmount > this.amount) {
                throw new IllegalArgumentException("Remaining Amount cannot be greater than the Loan Amount.");
            }
            if (this.paymentDate.compareTo(this.dueDate) > 0) {
                throw new IllegalArgumentException("Payment date cannot be greater than the due date.");
            }
            return new Loan(this);
        }


        public LoanBuilder setLenderId(String lenderId) {
            this.lenderId = lenderId;
            return this;
        }
    }

}

