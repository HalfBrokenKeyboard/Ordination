import controller.Controller;
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

public class ControllerTest {

    private LocalDate startDen;
    private LocalDate slutDen;
    private Patient patient;
    private Laegemiddel laegemiddel;



    @Test
    public void TC1_testOpretDagligSkaevOrdinationOpret() {

        //Arrange
        startDen = LocalDate.of(2023, 2, 20);
        slutDen = LocalDate.of(2023, 2, 25);
        patient = new Patient("123456-7890", "Test Navn", 70.5);
        laegemiddel = new Laegemiddel("Test Laegemiddel", 1.0, 2.0, 3.0, "stk");
        LocalTime[] klokkeSlet = {LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(16, 0)};
        double[] antalEnheder = {1.0, 2.0,3.0};
        Controller c = Controller.getTestController();

        //Act
        DagligSkaev ordination = c.opretDagligSkaevOrdination(startDen, slutDen, patient, laegemiddel, klokkeSlet, antalEnheder);

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


    @Test
    public void TC2_testOpretDagligSkaevOrdinationDato(){
        //Arrange
        startDen = LocalDate.of(2023, 2, 25);
        slutDen = LocalDate.of(2023, 2, 20);
        patient = new Patient("123456-7890", "Test Navn", 70.5);
        laegemiddel = new Laegemiddel("Test Laegemiddel", 1.0, 2.0, 3.0, "stk");
        LocalTime[] klokkeSlet = {LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(16, 0)};
        double[] antalEnheder = {1.0, 2.0,3.0};
        Controller c = Controller.getTestController();

        //Act & assert

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
             c.opretDagligSkaevOrdination(startDen, slutDen, patient, laegemiddel, klokkeSlet, antalEnheder);
        });
        assertEquals("Startdato må ikke være efter slutdato", exception.getMessage());

    }


    @Test
    public void TC3_testOpretDagligSkaevOrdinationEnheder(){

        //Arrange
        startDen = LocalDate.of(2023, 2, 20);
        slutDen = LocalDate.of(2023, 2, 25);
        patient = new Patient("123456-7890", "Test Navn", 70.5);
        laegemiddel = new Laegemiddel("Test Laegemiddel", 1.0, 2.0, -2, "stk");
        LocalTime[] klokkeSlet = {LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(16, 0)};
        double[] antalEnheder = {1.0, 2.0,-2};
        Controller c = Controller.getTestController();

        //Act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            c.opretDagligSkaevOrdination(startDen, slutDen, patient, laegemiddel, klokkeSlet, antalEnheder);
        });
        assertEquals("Antal enheder skal være positive", exception.getMessage());

    }


}
