package ordination;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DagligFast extends Ordination{
    // TODO
    double morgenAntal;
    double middagsAntal;
    double aftenAntal;
    double natAntal;

    Dosis[] doser;
    public DagligFast(LocalDate startDen, LocalDate slutDen, Patient patient,
                      double morgenAntal, double middagsAntal, double aftenAntal, double natAntal) {
        super(startDen, slutDen, patient);

        doser = new Dosis[4];
        if (morgenAntal > 0) {
            doser[0]= new Dosis(LocalTime.of(8,0,0), morgenAntal);
        }
        if (middagsAntal > 0) {
            doser[1]= new Dosis(LocalTime.of(12,0,0), middagsAntal);
        }
        if (aftenAntal > 0) {
            doser[2]= new Dosis(LocalTime.of(17,0,0), aftenAntal);
        }
        if (natAntal > 0) {
            doser[3]= new Dosis(LocalTime.of(22,0,0), natAntal);
        }

    }

    @Override
    public double samletDosis() {
        double days =  antalDage();
        return doegnDosis() * days;
    }

    @Override
    public double doegnDosis() {
        return morgenAntal + middagsAntal + aftenAntal + natAntal;
    }

    @Override
    public String getType() {
        return "Daglig fast";
    }

    public Dosis[] getDoser() {
        return doser;
    }
}
