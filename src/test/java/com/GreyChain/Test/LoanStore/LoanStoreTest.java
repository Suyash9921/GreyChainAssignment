package com.GreyChain.Test.LoanStore;

// LoanStoreTest.java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class LoanStoreTest {
    private LoanStore loanStore;

    @BeforeEach
    public void setUp() {
        loanStore = new LoanStore();
    }

    @Test
    public void testAddLoan() {
        Loan loan = Loan.Builder().setLoanId("L1").setCustomerId("C1").setLenderId("LEN1").setLenderId("LEN1")
                .setAmount(10000).setRemainingAmount(10000).setDueDate("05/07/2023").setPaymentDate("05/06/2023").setInterestPerDay(0.01)
                .build();
        loanStore.addLoan(loan);
        assertEquals(1, loanStore.getLoans().size());
    }

    @Test
    public void testAddLoanWithInvalidPaymentDate() {
        Loan loan = Loan.Builder().setLoanId("L1").setCustomerId("C1").setLenderId("LEN1").setLenderId("LEN1")
                .setAmount(10000).setRemainingAmount(10000).setDueDate("05/06/2023").setPaymentDate("05/07/2023").setInterestPerDay(0.01)
                .build();
        assertThrows(IllegalArgumentException.class, () -> loanStore.addLoan(loan));
    }

    @Test
    public void testAggregateByLender() {
        Map<String, Double> lenderTotal = loanStore.aggregateByLender();

        assertNotNull(lenderTotal);
        assertEquals(1, lenderTotal.size());
        assertEquals(15000.0, lenderTotal.get("LEN1"));
    }

    @Test
    public void testAggregateByInterest() {
        Map<Double, Double> interestTotal = loanStore.aggregateByInterest();

        assertNotNull(interestTotal);
        assertEquals(2, interestTotal.size());
        assertEquals(10000.0, interestTotal.get(1)); // Expected total amount for interest rate 1
        assertEquals(5000.0, interestTotal.get(2)); // Expected total amount for interest rate 2
    }

    @Test
    public void testAggregateByCustomerId() {
        Map<String, Double> customerTotal = loanStore.aggregateByCustomerId();

        assertNotNull(customerTotal);
        assertEquals(3, customerTotal.size());
        assertEquals(10000.0, customerTotal.get("C1")); // Expected total amount for customer C1
        assertEquals(5000.0, customerTotal.get("C2")); // Expected total amount for customer C2
        assertEquals(5000.0, customerTotal.get("C3")); // Expected total amount for customer C3
    }
}
