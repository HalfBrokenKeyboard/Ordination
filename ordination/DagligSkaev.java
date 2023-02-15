package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {
    //TODO
    private ArrayList<Dosis> doser = new ArrayList<>();


    public DagligSkaev(LocalDate startDate, LocalDate slutDate, Patient patient) {
        super(startDate, slutDate, patient);
        this.doser = new ArrayList<>();
    }


    public ArrayList<Dosis> getDoser() {
        return new ArrayList<>(doser);
    }

    public void setDoser(ArrayList<Dosis> doser) {
        this.doser = doser;
    }


    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid, antal);
        doser.add(dosis);
    }

    @Override
    public double samletDosis() {
        double totalDosis = 0.0;
        for (Dosis dosis : doser) {
            totalDosis += dosis.getAntal();
        }
        return totalDosis;
    }

    @Override
    public double doegnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }
}







