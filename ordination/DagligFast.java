package ordination;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DagligFast extends Ordination{
    // TODO
    int morgenAntal;
    int middagsAntal;
    int aftenAntal;
    int natAntal;

    Dosis[] doser;
    public DagligFast(LocalDate startDen, LocalDate slutDen, Patient patient,
                      int morgenAntal, int middagsAntal, int aftenAntal, int natAntal) {
        super(startDen, slutDen, patient);

        doser = new Dosis[4];
        if (morgenAntal > 0) {
            doser[0]= new Dosis(LocalTime.of(8,0,0), morgenAntal);
        }
        if (middagsAntal > 0) {
            doser[1]= new Dosis(LocalTime.of(12,0,0), morgenAntal);
        }
        if (aftenAntal > 0) {
            doser[2]= new Dosis(LocalTime.of(17,0,0), morgenAntal);
        }
        if (natAntal > 0) {
            doser[3]= new Dosis(LocalTime.of(22,0,0), morgenAntal);
        }

    }

    @Override
    public double samletDosis() {
        double totalDosis = doegnDosis();
        double days = ChronoUnit.DAYS.between(getStartDen(), getSlutDen()) + 1;
        return totalDosis * days;
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
