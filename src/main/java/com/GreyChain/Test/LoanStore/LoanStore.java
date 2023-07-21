package com.GreyChain.Test.LoanStore;



import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


    public class LoanStore {
        private final List<Loan> loans;
        private final Logger logger;

        public LoanStore() {
            this.loans = new ArrayList<>();
            this.logger = Logger.getLogger(LoanStore.class.getName());
        }

        public void addLoan(Loan loan) {
            if (loan.getPaymentDate().compareTo(loan.getDueDate()) > 0) {
                throw new IllegalArgumentException("Payment date cannot be greater than the due date.");
            }
            loans.add(loan);
        }

        public Map<String, Double> aggregateByLender() {
            Map<String, Double> lenderTotal = new HashMap<>();
            for (Loan loan : loans) {
                String lenderId = loan.getLenderId();
                double currentTotal = lenderTotal.getOrDefault(lenderId, 0.0);
                lenderTotal.put(lenderId, currentTotal + loan.getRemainingAmount());
            }
            return lenderTotal;
        }

        public Map<Double, Double> aggregateByInterest() {
            Map<Double, Double> interestTotal = new HashMap<>();
            for (Loan loan : loans) {
                double interestRate = loan.getInterestPerDay();
                double currentTotal = interestTotal.getOrDefault(interestRate, 0.0);
                interestTotal.put(interestRate, currentTotal + loan.getRemainingAmount());
            }
            return interestTotal;
        }

        public Map<String, Double> aggregateByCustomerId() {
            Map<String, Double> customerTotal = new HashMap<>();
            for (Loan loan : loans) {
                String customerId = loan.getCustomerId();
                double currentTotal = customerTotal.getOrDefault(customerId, 0.0);
                customerTotal.put(customerId, currentTotal + loan.getRemainingAmount());
            }
            return customerTotal;
        }

        // Method to check for overdue loans and log an alert
        public void checkForOverdueLoans() {
            for (Loan loan : loans) {
                if (loan.getDueDate().compareTo(loan.getPaymentDate()) < 0) {
                    logger.log(Level.WARNING, "Loan {0} is overdue!", loan.getLoanId());
                }
            }
        }

        public Collection<Loan> getLoans() {
            return loans;
        }
    }
