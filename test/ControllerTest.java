import controller.Controller;
import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ControllerTest {

    private LocalDate startDen;
    private LocalDate slutDen;
    private Patient patient;
    private Laegemiddel laegemiddel;


    //OPRET PN ORDINATION
    @Test
    void TC1_OpretPNOrdination1() {

        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");
        Controller c = Controller.getController();

        double antalEnheder = 1;
        LocalDate startDen = LocalDate.parse("2023-02-20");
        LocalDate slutDen = LocalDate.parse("2023-02-25");

        //Act
        PN faktiskPN = c.opretPNOrdination(startDen,slutDen,patient,laegemiddel,antalEnheder);

        //Assert
        PN forventetPN = new PN(startDen,slutDen,patient,laegemiddel,antalEnheder);
        assertEquals(forventetPN.getStartDen(), faktiskPN.getStartDen());
        assertEquals(forventetPN.getSlutDen(), faktiskPN.getSlutDen());
        assertEquals(forventetPN.getLaegemiddel(), faktiskPN.getLaegemiddel());
        assertEquals(forventetPN.getPatient(), faktiskPN.getPatient());
        assertEquals(forventetPN.getAntalEnheder(), faktiskPN.getAntalEnheder());

    }

    @Test
    void TC2_OpretPNOrdination2() {

        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");
        Controller c = Controller.getController();

        double antalEnheder = -2;
        LocalDate startDen = LocalDate.parse("2023-02-20");
        LocalDate slutDen = LocalDate.parse("2023-02-25");

        //Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            c.opretPNOrdination(startDen,slutDen,patient,laegemiddel,antalEnheder);

        });
        //Assert
        assertEquals("Antal enheder er mindre end eller lige med 0", exception.getMessage());

    }
    @Test
    void TC3_OpretPNOrdination3() {

        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");
        Controller c = Controller.getController();

        double antalEnheder = 1;
        LocalDate startDen = LocalDate.parse("2023-02-26");
        LocalDate slutDen = LocalDate.parse("2023-02-25");

        //Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            c.opretPNOrdination(startDen,slutDen,patient,laegemiddel,antalEnheder);

        });
        //Assert
        assertEquals("Slut dato er før star dato", exception.getMessage());

    }
    @Test
    void TC4_OpretPNOrdination4() {

        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");
        Controller c = Controller.getController();

        double antalEnheder = 5;
        LocalDate startDen = LocalDate.parse("2023-02-20");
        LocalDate slutDen = LocalDate.parse("2023-02-28");

        //Act
        PN faktiskPN = c.opretPNOrdination(startDen,slutDen,patient,laegemiddel,antalEnheder);

        //Assert
        PN forventetPN = new PN(startDen,slutDen,patient,laegemiddel,antalEnheder);
        assertEquals(forventetPN.getStartDen(), faktiskPN.getStartDen());
        assertEquals(forventetPN.getSlutDen(), faktiskPN.getSlutDen());
        assertEquals(forventetPN.getLaegemiddel(), faktiskPN.getLaegemiddel());
        assertEquals(forventetPN.getPatient(), faktiskPN.getPatient());
        assertEquals(forventetPN.getAntalEnheder(), faktiskPN.getAntalEnheder());

    }


    //ORDINATION PN ANVENDT
    @Test
    void TC1_OrdinationPNAnvendt() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10, 0.20, 0.30, "Styk");
        Controller c = Controller.getController();
        double antalEnheder = 1;
        LocalDate startDen = LocalDate.parse("2023-02-10");
        LocalDate slutDen = LocalDate.parse("2023-02-20");
        PN ordination = new PN(startDen, slutDen,patient,laegemiddel,antalEnheder);

        LocalDate dato = LocalDate.parse("2023-02-10");

        //Act // Assert
        assertDoesNotThrow(() -> c.ordinationPNAnvendt(ordination, dato));
    }

    @Test
    void TC2_OrdinationPNAnvendt() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10, 0.20, 0.30, "Styk");
        Controller c = Controller.getController();
        double antalEnheder = 1;
        LocalDate startDen = LocalDate.parse("2023-02-10");
        LocalDate slutDen = LocalDate.parse("2023-02-20");
        PN ordination = new PN(startDen, slutDen,patient,laegemiddel,antalEnheder);

        LocalDate dato = LocalDate.parse("2023-02-09");

        //Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            c.ordinationPNAnvendt(ordination, dato);

        });
        //Assert
        assertEquals("Fejl", exception.getMessage());

    }

    @Test
    void TC3_OrdinationPNAnvendt() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10, 0.20, 0.30, "Styk");
        Controller c = Controller.getController();
        double antalEnheder = 1;
        LocalDate startDen = LocalDate.parse("2023-02-10");
        LocalDate slutDen = LocalDate.parse("2023-02-20");
        PN ordination = new PN(startDen, slutDen,patient,laegemiddel,antalEnheder);

        LocalDate dato = LocalDate.parse("2023-02-15");

        //Act // Assert
        assertDoesNotThrow(() -> c.ordinationPNAnvendt(ordination, dato));
    }

    @Test
    void TC4_OrdinationPNAnvendt() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10, 0.20, 0.30, "Styk");
        Controller c = Controller.getController();
        double antalEnheder = 1;
        LocalDate startDen = LocalDate.parse("2023-02-10");
        LocalDate slutDen = LocalDate.parse("2023-02-20");
        PN ordination = new PN(startDen, slutDen,patient,laegemiddel,antalEnheder);

        LocalDate dato = LocalDate.parse("2023-02-20");

        //Act // Assert
        assertDoesNotThrow(() -> c.ordinationPNAnvendt(ordination, dato));
    }

    @Test
    void TC5_OrdinationPNAnvendt() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10, 0.20, 0.30, "Styk");
        Controller c = Controller.getController();
        double antalEnheder = 1;
        LocalDate startDen = LocalDate.parse("2023-02-10");
        LocalDate slutDen = LocalDate.parse("2023-02-20");
        PN ordination = new PN(startDen, slutDen,patient,laegemiddel,antalEnheder);

        LocalDate dato = LocalDate.parse("2023-02-21");

        //Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            c.ordinationPNAnvendt(ordination, dato);

        });
        //Assert
        assertEquals("Fejl", exception.getMessage());
    }

    // Daglig Skæv Controller Test

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
            assertEquals(antalEnheder[i], dosis.getAntal());
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


    // Daglig Fast Controller Test

    @Test
    public void TC1_opretDagligFastOrdination() {
        startDen = LocalDate.of(2023, 2, 10);
        slutDen = LocalDate.of(2023, 2, 25);
        patient = new Patient("123456-7890",  "Fornavn Efternavn", 80);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");

        double morgenAntal = 1.0;
        double middagsAntal = 0.0;
        double aftenAntal = 1.0;
        double natAntal = 0.0;

        Controller c = Controller.getTestController();

        //Act
        DagligFast ordination = c.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, morgenAntal, middagsAntal, aftenAntal, natAntal);

        // Assert
        assertEquals(startDen, ordination.getStartDen());
        assertEquals(slutDen, ordination.getSlutDen());
        assertEquals(laegemiddel, ordination.getLaegemiddel());
        assertEquals(morgenAntal, ordination.getMorgenAntal());
        assertEquals(middagsAntal, ordination.getMiddagsAntal());
        assertEquals(aftenAntal, ordination.getAftenAntal());
        assertEquals(natAntal, ordination.getNatAntal());
    }
    @Test
    public void TC2_opretDagligFastOrdination() {
        startDen = LocalDate.of(2023, 2, 10);
        slutDen = LocalDate.of(2023, 2, 11);
        patient = new Patient("123456-7890",  "Fornavn Efternavn", 80);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");

        double morgenAntal = 0.0;
        double middagsAntal = 1.0;
        double aftenAntal = 0.0;
        double natAntal = 1.0;

        Controller c = Controller.getTestController();

        //Act
        DagligFast ordination = c.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, morgenAntal, middagsAntal, aftenAntal, natAntal);

        // Assert
        assertEquals(startDen, ordination.getStartDen());
        assertEquals(slutDen, ordination.getSlutDen());
        assertEquals(laegemiddel, ordination.getLaegemiddel());
        assertEquals(morgenAntal, ordination.getMorgenAntal());
        assertEquals(middagsAntal, ordination.getMiddagsAntal());
        assertEquals(aftenAntal, ordination.getAftenAntal());
        assertEquals(natAntal, ordination.getNatAntal());
    }

    @Test
    public void TC3_opretDagligFastOrdination() {
        startDen = LocalDate.of(2023, 2, 10);
        slutDen = LocalDate.of(2023, 2, 20);
        patient = new Patient("123456-7890",  "Fornavn Efternavn", 80);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");

        double morgenAntal = 0.0;
        double middagsAntal = 0.0;
        double aftenAntal = 0.0;
        double natAntal = 0.0;

        Controller c = Controller.getTestController();

        //Act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            c.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, morgenAntal, middagsAntal, aftenAntal, natAntal);
        });
        assertEquals("Skal angive mindst en dosis", exception.getMessage());
    }

    @Test
    public void TC4_opretDagligFastOrdination() {
        startDen = LocalDate.of(2023, 2, 10);
        slutDen = LocalDate.of(2023, 2, 8);
        patient = new Patient("123456-7890",  "Fornavn Efternavn", 80);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");

        double morgenAntal = 1;
        double middagsAntal = 1;
        double aftenAntal = 0.0;
        double natAntal = 0.0;

        Controller c = Controller.getTestController();

        //Act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            c.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, morgenAntal, middagsAntal, aftenAntal, natAntal);
        });
        assertEquals("Slut dato er før start dato", exception.getMessage());
    }


    @Test
    public void TC5_opretDagligFastOrdination() {
        startDen = LocalDate.of(2023, 2, 10);
        slutDen = LocalDate.of(2023, 2, 18);
        patient = new Patient("123456-7890",  "Fornavn Efternavn", 80);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");

        double morgenAntal = -1;
        double middagsAntal = 0.0;
        double aftenAntal = 0.0;
        double natAntal = 0.0;

        Controller c = Controller.getTestController();

        //Act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            c.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, morgenAntal, middagsAntal, aftenAntal, natAntal);
        });
        assertEquals("Skal angive mindst en dosis", exception.getMessage());
    }
}
