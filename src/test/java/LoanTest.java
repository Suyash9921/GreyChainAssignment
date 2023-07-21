// LoanTest.java
import com.GreyChain.Test.LoanStore.Loan;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {
    @Test
    public void testLoanCreation() {
        Loan loan = Loan.Builder().setLoanId("L1").setCustomerId("C1").setLenderId("LEN1").setLenderId("LEN1")
                .setAmount(10000).setRemainingAmount(10000).setDueDate("05/07/2023").setPaymentDate("05/06/2023").setInterestPerDay(0.01)
                .build();
        assertEquals("L1", loan.getLoanId());
        assertEquals("C1", loan.getCustomerId());
        assertEquals("LEN1", loan.getLenderId());
        assertEquals(10000, loan.getAmount());
        assertEquals(10000, loan.getRemainingAmount());
        assertEquals("05/06/2023", loan.getPaymentDate());
        assertEquals(1, loan.getInterestPerDay());
        assertEquals("05/07/2023", loan.getDueDate());
        assertEquals(0.01, loan.getPenaltyPerDay());
    }

    // Add more test cases to cover other scenarios and validations
}
