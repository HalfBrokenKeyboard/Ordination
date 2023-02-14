package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

public class PN extends Ordination{

    private double antalEnheder;
    private ArrayList<LocalDate> dosisGivetDatoer = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, Patient patient) {
        super(startDen, slutDen, patient);
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        if (givesDen.isAfter(getStartDen()) || givesDen.isBefore(getSlutDen())) {
            dosisGivetDatoer.add(givesDen);
            return true;
        }

        return false;
    }

    public double doegnDosis() {
        return (dosisGivetDatoer.size()*antalEnheder)/(DAYS.between(dosisGivetDatoer.get(0), dosisGivetDatoer.get(dosisGivetDatoer.size()-1)));
    }

    public double samletDosis() {
        return dosisGivetDatoer.size()*antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return dosisGivetDatoer.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    public String getType() {
        return null;
    }

}
