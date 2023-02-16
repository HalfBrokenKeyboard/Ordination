import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PNTest {

    private PN pn;
    // -----------------------------------------------
    // Test for doegnDosis metoden
    @Test
    public void TC1_PN_DoegnDosis(){
        //Arrange
        Laegemiddel laegemiddel = new Laegemiddel("Test", 2.5,1.0,1.0, "mg");
        Patient patient = new Patient("123456-7810", "Peter",80);
        LocalDate startDen = LocalDate.parse("2023-02-10");
        LocalDate slutDen = LocalDate.parse("2023-02-20");

        double antalEnheder = 0.2;
        LocalDate dato1 = LocalDate.of(2023, 2, 11);
        LocalDate dato2 = LocalDate.of(2023, 2, 15);
        LocalDate dato3 = LocalDate.of(2023, 2, 20);

        PN pn = new PN(startDen, slutDen, patient, laegemiddel, antalEnheder);

        pn.givDosis(dato1);
        pn.givDosis(dato2);
        pn.givDosis(dato3);


        double actual = pn.doegnDosis();

        double expected = 0.06;
        assertEquals(expected, actual, 0.0001);

    }

    @Test
    public void TC2_PN_DoegnDosis(){
        //Arrange
        Laegemiddel laegemiddel = new Laegemiddel("Test", 2.5,1.0,1.0, "mg");
        Patient patient = new Patient("123456-7810", "Peter",80);
        LocalDate startDen = LocalDate.parse("2023-02-10");
        LocalDate slutDen = LocalDate.parse("2023-02-20");

        double antalEnheder = 0.5;
        LocalDate dato1 = LocalDate.of(2023, 2, 11);
        LocalDate dato2 = LocalDate.of(2023, 2, 15);
        LocalDate dato3 = LocalDate.of(2023, 2, 20);

        PN pn = new PN(startDen, slutDen, patient, laegemiddel, antalEnheder);

        pn.givDosis(dato1);
        pn.givDosis(dato2);
        pn.givDosis(dato3);


        double actual = pn.doegnDosis();

        double expected = 0.15;
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void TC3_PN_DoegnDosis(){
        //Arrange
        Laegemiddel laegemiddel = new Laegemiddel("Test", 2.5,1.0,1.0, "mg");
        Patient patient = new Patient("123456-7810", "Peter",80);
        LocalDate startDen = LocalDate.parse("2023-02-10");
        LocalDate slutDen = LocalDate.parse("2023-02-20");

        double antalEnheder = 1;
        LocalDate dato1 = LocalDate.of(2023, 2, 11);
        LocalDate dato2 = LocalDate.of(2023, 2, 15);
        LocalDate dato3 = LocalDate.of(2023, 2, 20);

        PN pn = new PN(startDen, slutDen, patient, laegemiddel, antalEnheder);

        pn.givDosis(dato1);
        pn.givDosis(dato2);
        pn.givDosis(dato3);


        double actual = pn.doegnDosis();

        double expected = 0.3;
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void TC4_PN_DoegnDosis(){
        //Arrange
        Laegemiddel laegemiddel = new Laegemiddel("Test", 2.5,1.0,1.0, "mg");
        Patient patient = new Patient("123456-7810", "Peter",80);
        LocalDate startDen = LocalDate.parse("2023-02-10");
        LocalDate slutDen = LocalDate.parse("2023-02-20");

        double antalEnheder = 1;
        LocalDate dato1 = LocalDate.of(2023, 2, 11);
        LocalDate dato2 = LocalDate.of(2023, 2, 11);


        PN pn = new PN(startDen, slutDen, patient, laegemiddel, antalEnheder);

        pn.givDosis(dato1);
        pn.givDosis(dato2);

        double actual = pn.doegnDosis();

        double expected = 2;
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void TC5_PN_DoegnDosis(){
        //Arrange
        Laegemiddel laegemiddel = new Laegemiddel("Test", 2.5,1.0,1.0, "mg");
        Patient patient = new Patient("123456-7810", "Peter",80);
        LocalDate startDen = LocalDate.parse("2023-02-10");
        LocalDate slutDen = LocalDate.parse("2023-02-20");

        double antalEnheder = 1;
        LocalDate dato1 = LocalDate.of(2023, 2, 11);
        LocalDate dato2 = LocalDate.of(2023, 2, 12);

        PN pn = new PN(startDen, slutDen, patient, laegemiddel, antalEnheder);

        pn.givDosis(dato1);
        pn.givDosis(dato2);

        double actual = pn.doegnDosis();

        double expected = 1;
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void TC6_PN_DoegnDosis(){
        //Arrange
        Laegemiddel laegemiddel = new Laegemiddel("Test", 2.5,1.0,1.0, "mg");
        Patient patient = new Patient("123456-7810", "Peter",80);
        LocalDate startDen = LocalDate.parse("2023-02-10");
        LocalDate slutDen = LocalDate.parse("2023-02-20");

        double antalEnheder = 1;
        LocalDate dato1 = LocalDate.of(2023, 2, 11);
        LocalDate dato2 = LocalDate.of(2023, 2, 11);
        LocalDate dato3 = LocalDate.of(2023, 2, 12);

        PN pn = new PN(startDen, slutDen, patient, laegemiddel, antalEnheder);

        pn.givDosis(dato1);
        pn.givDosis(dato2);
        pn.givDosis(dato3);


        double actual = pn.doegnDosis();

        double expected = 1.5;
        assertEquals(expected, actual, 0.0001);
    }

    // -----------------------------------------------
    // Test for givDosis metoden
    @Test
    public void TC1_givDosis() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn",  80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");
        LocalDate startDato = LocalDate.of(2023, 2, 10);
        LocalDate slutDato = LocalDate.of(2023, 2, 20);
        double antalEnheder = 2;

        //Act
        PN pn = new PN(startDato, slutDato, patient, laegemiddel, antalEnheder);
        LocalDate dato1 = LocalDate.of(2023, 2, 10);

        //Assert
        assertEquals(true, pn.givDosis(dato1));
    }
    @Test
    public void TC2_givDosis() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn",  80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");
        LocalDate startDato = LocalDate.of(2023, 2, 10);
        LocalDate slutDato = LocalDate.of(2023, 2, 20);
        double antalEnheder = 2;

        //Act
        PN pn = new PN(startDato, slutDato, patient, laegemiddel, antalEnheder);
        LocalDate dato1 = LocalDate.of(2023, 2, 20);



        //Assert
        assertEquals(true, pn.givDosis(dato1));
    }
    @Test
    public void TC3_givDosis() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn",  80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");
        LocalDate startDato = LocalDate.of(2023, 2, 10);
        LocalDate slutDato = LocalDate.of(2023, 2, 20);
        double antalEnheder = 2;

        //Act
        PN pn = new PN(startDato, slutDato, patient, laegemiddel, antalEnheder);
        LocalDate dato1 = LocalDate.of(2023, 2, 20);



        //Assert
        assertEquals(true, pn.givDosis(dato1));
    }

    @Test
    public void TC4_givDosis() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn",  80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");
        LocalDate startDato = LocalDate.of(2023, 2, 10);
        LocalDate slutDato = LocalDate.of(2023, 2, 20);
        double antalEnheder = 2;

        //Act
        PN pn = new PN(startDato, slutDato, patient, laegemiddel, antalEnheder);
        LocalDate dato1 = LocalDate.of(2023, 2, 9);

        //Assert
        assertEquals(false, pn.givDosis(dato1));
    }

    @Test
    public void TC5_givDosis() {
        //Arrange
        Patient patient = new Patient("123456-7890", "Fornavn Efternavn",  80);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");
        LocalDate startDato = LocalDate.of(2023, 2, 10);
        LocalDate slutDato = LocalDate.of(2023, 2, 20);
        double antalEnheder = 2;

        //Act
        PN pn = new PN(startDato, slutDato, patient, laegemiddel, antalEnheder);
        LocalDate dato1 = LocalDate.of(2023, 2, 21);

        //Assert
        assertEquals(false, pn.givDosis(dato1));
    }


}
