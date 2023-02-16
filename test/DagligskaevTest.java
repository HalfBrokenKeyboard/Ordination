import controller.*;
import ordination.DagligSkaev;
import ordination.Dosis;
import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DagligskaevTest {
    private LocalDate startDen;
    private LocalDate slutDen;
    private Patient patient;
    private DagligSkaev dagligSkaev;
@BeforeEach
public void setup() {
    startDen = LocalDate.of(2022, 3, 1);
    slutDen = LocalDate.of(2022, 3, 10);
    patient = new Patient("123456-7890", "Navn", 70.0);
    dagligSkaev = new DagligSkaev(startDen, slutDen, patient);
}


    // -----------------------------------------------
    // Test for doegnDosis metoden
    @Test
    public void TC1_Dagligskaev_DoegnDosis(){
        
        //Arrange
        DagligSkaev dagligSkaev = new DagligSkaev(null, slutDen, patient) ;
        //Act & assert


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dagligSkaev.doegnDosis();
        });
        assertEquals(exception.getMessage(), "Ingen start dato");
        
    }

    @Test
    public void TC2_Dagligskaev_DoegnDosis(){
        //Arrange
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 15);
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 10);

        //Act
        double actualDosis = dagligSkaev.doegnDosis();
        double expectedDosis = 25;

        //Assert
        assertEquals(expectedDosis, actualDosis, 0.001);
    }

    @Test
    public void TC3_Dagligskaev_DoegnDosis(){

        //Arrange
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 1.5);
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 0.5);

        //Act
        double actualDosis = dagligSkaev.doegnDosis();
        double expectedDosis = 2;

        //Assert
        assertEquals(expectedDosis, actualDosis, 0.001);
    }

    @Test
    public void TC4_Dagligskaev_DoegnDosis() {
        //Arrange
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 0.2);
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 0.3);
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 0.1);

        //Act
        double actualDosis = dagligSkaev.doegnDosis();
        double expectedDosis = 0.6;

        //Assert
        assertEquals(expectedDosis, actualDosis, 0.001);
    }

    @Test
    public void TC5_DagligSkaev_DoegnDosis(){
        //Arrange
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 2.5);
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 2.5);
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 2.5);
        dagligSkaev.opretDosis(LocalTime.of(8, 0), 2.5);

        //Act
        double actualDosis = dagligSkaev.doegnDosis();
        double expectedDosis = 10.0;

        //Assert
        assertEquals(expectedDosis, actualDosis, 0.001);
    }

    @Test
    public void TC6_Dagligskaev_DoegnDosis(){

        //Arrange
        DagligSkaev dagligSkaev = new DagligSkaev(startDen, null, patient) ;
        //Act & assert


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dagligSkaev.doegnDosis();
        });
        assertEquals(exception.getMessage(), "Ingen slut dato");

    }

    @Test
    public void TC7_DagligSkaev_DoegnDosis(){
    DagligSkaev da = new DagligSkaev(startDen,slutDen,patient);
    dagligSkaev.opretDosis(LocalTime.of(8,0), -1);

    Exception exception = assertThrows(IllegalArgumentException.class, () ->{
        dagligSkaev.doegnDosis();
    });
    assertEquals(exception.getMessage(), "Doser er lig med null");
    }
}


