import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PNTest {

    private PN pn;
    // -----------------------------------------------
    // Test for doegnDosis metoden
    @Test
    public void TC1_PN_DoegnDosis(){
        //Arrange
        LocalDate startDen = LocalDate.of(2023, 2, 11);
        LocalDate mellemDen = LocalDate.of(2023, 2, 15);
        LocalDate slutDen = LocalDate.of(2023, 2, 20);

        ArrayList<LocalDate> datoer = new ArrayList<>();

        datoer.add(startDen);
        datoer.add(mellemDen);
        datoer.add(slutDen);

        Laegemiddel laegemiddel = new Laegemiddel("Test", 2.5,1.0,1.0, "mg");
        Patient patient = new Patient("123456-7810", "Peter",80);
        PN pn = new PN(startDen, slutDen, patient, laegemiddel, 0.20);
        pn.givDosis(LocalDate.of(2023, 2, 11));
        pn.givDosis(LocalDate.of(2023, 2, 15));

        double actual = pn.doegnDosis();

        double expected = 0.6;
        assertEquals(expected, actual, expected);

    }

    @Test
    public void TC2_PN_DoegnDosis(){
        LocalDate startDen = LocalDate.of(2023, 2, 11);
        LocalDate slutDen = LocalDate.of(2023, 2, 15);
        Laegemiddel laegemiddel = new Laegemiddel("Test", 2.5,1.0,1.0, "mg");
        Patient patient = new Patient("123456-7810", "Peter",80);
        PN pn = new PN(startDen, slutDen, patient, laegemiddel, 0.50);
        pn.givDosis(LocalDate.of(2023, 2, 11));
        pn.givDosis(LocalDate.of(2023, 2, 15));
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
