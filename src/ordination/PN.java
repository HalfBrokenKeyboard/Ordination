package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

public class PN extends Ordination{

    private ArrayList<LocalDate> dosisGivetDatoer = new ArrayList<>();
    private Laegemiddel laegemiddel;
    private double antalEnheder;


    public PN(LocalDate startDen, LocalDate slutDen, Patient patient, Laegemiddel laegemiddel,double antalEnheder) {
        super(startDen, slutDen, patient);
        this.laegemiddel = laegemiddel;
        this.antalEnheder = antalEnheder;

    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        if (givesDen.isAfter(getStartDen()) && givesDen.isBefore(getSlutDen()) || givesDen.isEqual(getStartDen()) || givesDen.isEqual(getSlutDen())) {
            dosisGivetDatoer.add(givesDen);

            return true;
        }

        return false;
    }


    public double doegnDosis() {
        if (dosisGivetDatoer.size() == 0) {
            return 0.0;
        } else {
            return (dosisGivetDatoer.size()*antalEnheder)/(1 + (DAYS.between(dosisGivetDatoer.get(0), dosisGivetDatoer.get(dosisGivetDatoer.size()-1))));
        }
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
        return "PN";
    }


}
