import javafx.scene.paint.Paint;
import ordination.DagligFast;
import ordination.Laegemiddel;
import ordination.Patient;
import ordination.DagligFast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DagligFastTest {
    double morgenAntal;
    double middagsAntal;
    double aftenAntal;
    double natAntal;

    Patient patient;
    Laegemiddel laegemiddel;
    LocalDate startdato;
    @BeforeEach
    public void setup(){
        patient = new Patient("123456-7890", "Fornavn Efternavn", 80);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.10,  0.20, 0.30, "Styk");
        startdato = LocalDate.of(2023, 02, 10);
    }
    // -----------------------------------------------
    // Test for doegnDosis metoden
    @Test
    @Order(1)
    public void TC1_Morgen_og_Aften() {
        //Arrange
        morgenAntal = 1;
        middagsAntal = 0;
        aftenAntal = 1;
        natAntal = 0;

        LocalDate slutDato = LocalDate.of(2023, 02, 11);
        DagligFast dagligFast = new DagligFast(startdato, slutDato, patient, morgenAntal, middagsAntal, aftenAntal, natAntal);

        //Act
        double actualDosis = dagligFast.doegnDosis();
        double expectedDosis = 2;

        //Assert
        assertEquals(expectedDosis, actualDosis);
    }

    @Test
    @Order(2)
    public void TC2_Alle_tidspunkter() {
        //Arrange
        morgenAntal = 2;
        middagsAntal = 2;
        aftenAntal = 2;
        natAntal = 2;

        LocalDate slutDato = LocalDate.of(2023, 02, 11);
        DagligFast dagligFast = new DagligFast(startdato, slutDato, patient, morgenAntal, middagsAntal, aftenAntal, natAntal);

        //Act
        double actualDosis = dagligFast.doegnDosis();
        double expectedDosis = 8;

        //Assert
        assertEquals(expectedDosis, actualDosis);
    }

    @Test
    @Order(3)
    public void TC3_Ingen_doser() {
        //Arrange
        morgenAntal = 0;
        middagsAntal = 0;
        aftenAntal = 0;
        natAntal = 0;

        LocalDate slutDato = LocalDate.of(2023, 02, 11);
        DagligFast dagligFast = new DagligFast(startdato, slutDato, patient, morgenAntal, middagsAntal, aftenAntal, natAntal);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dagligFast.doegnDosis();
        });
        assertEquals(exception.getMessage(), "Ingen doser angivet");
    }

    @Test
    @Order(4)
    public void TC4_Minus_i_doser() {
        //Arrange
        morgenAntal = -1;
        middagsAntal = 0;
        aftenAntal = 0;
        natAntal = 0;

        LocalDate slutDato = LocalDate.of(2023, 02, 11);
        DagligFast dagligFast = new DagligFast(startdato, slutDato, patient, morgenAntal, middagsAntal, aftenAntal, natAntal);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dagligFast.doegnDosis();
        });
        assertEquals(exception.getMessage(), "Ingen doser angivet");
    }

    @Test
    @Order(5)
    public void TC5_Middag_og_Nat() {
        //Arrange
        morgenAntal = 0;
        middagsAntal = 1;
        aftenAntal = 0;
        natAntal = 1;

        LocalDate slutDato = LocalDate.of(2023, 02, 11);
        DagligFast dagligFast = new DagligFast(startdato, slutDato, patient, morgenAntal, middagsAntal, aftenAntal, natAntal);

        //Act
        double actualDosis = dagligFast.doegnDosis();
        double expectedDosis = 2;

        //Assert
        assertEquals(expectedDosis, actualDosis);
    }

}
