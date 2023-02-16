import controller.*;
import ordination.DagligSkaev;
import ordination.Dosis;
import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DagligskaevTest {

    @Test
    public void TC3_Dagligskaev_DoegnDosis() {
        //Arrange
        LocalDate startDen = LocalDate.of(2022, 3, 1);
        LocalDate slutDen = LocalDate.of(2022, 3, 10);
        Patient patient = new Patient("123456-7890", "Navn", 70.0);
        DagligSkaev dagligSkaev = new DagligSkaev(startDen, slutDen, patient);
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 1.5);

        //Act
        double actualDosis = dagligSkaev.doegnDosis();
        double expectedDosis = 1.5;

        //Assert
        assertEquals(expectedDosis, actualDosis, 0.001);
    }

    @Test
    public void testOpretDagligSkaevOrdination() {

        /*
        Denne test opretter en ny Controller-instans og kalder opretDagligSkaevOrdination-metoden med nogle testdata.
         Herefter tjekker testen, om den returnerede DagligSkaev-ordination er oprettet korrekt
          ved at sammenligne dens felter med de forventede værdier.
         */


        //Arrange
        LocalDate startDen = LocalDate.of(2023, 3, 1);
        LocalDate slutDen = LocalDate.of(2023, 3, 5);
        Patient patient = new Patient("123456-7890", "Test Navn", 70.5);
        Laegemiddel laegemiddel = new Laegemiddel("Test Laegemiddel", 1.0, 2.0, 3.0, "stk");
        LocalTime[] klokkeSlet = {LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(16, 0)};
        double[] antalEnheder = {1.5, 2.0, 3.0};
        Controller controller = new Controller();

        //Act
        DagligSkaev ordination = controller.opretDagligSkaevOrdination(startDen, slutDen, patient, laegemiddel, klokkeSlet, antalEnheder);

        // Assert
        assertEquals(startDen, ordination.getStartDen());
        assertEquals(slutDen, ordination.getSlutDen());
        assertEquals(patient, ordination.getPatient());
        assertEquals(laegemiddel, ordination.getLaegemiddel());
        assertEquals(klokkeSlet.length, ordination.getDoser().size());
        for (int i = 0; i < klokkeSlet.length; i++) {
            Dosis dosis = ordination.getDoser().get(i);
            assertEquals(klokkeSlet[i], dosis.getTid());
            assertEquals(antalEnheder[i], dosis.getAntal(), 0.001);
        }



    }
}
