import static org.junit.jupiter.api.Assertions.*;

import be.pxl.student.entity.Payment;
import be.pxl.student.util.BudgetPlannerImporter;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

class BudgetPlannerImporterTest {

    private Payment correctPayment;
    private BudgetPlannerImporter budgetPlannerImporter;

    @BeforeEach
    public void setUp(){
        budgetPlannerImporter = new BudgetPlannerImporter();
        LocalDateTime localDateTime = LocalDateTime.of(2020,2,26,2,11);
        correctPayment = new Payment(localDateTime, 1337,"EUR","Lorum ipsum");
    }

    @Test
    public void shouldReturnCorrectPaymentObject(){
        String[] input = {"Jan", "IBANBLALBABLA","IBAN2BLABLA","Wed Feb 26 02:11:00 CET 2020","1337","EUR","Lorum ipsum"};
        Assertions.assertEquals(budgetPlannerImporter.createPayment(input).toString(), correctPayment.toString());
    }

}