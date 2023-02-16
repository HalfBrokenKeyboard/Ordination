import net.bytebuddy.asm.Advice;
import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PNTest {
    // -----------------------------------------------
    // Test for doegnDosis metoden


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
