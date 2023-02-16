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

}


