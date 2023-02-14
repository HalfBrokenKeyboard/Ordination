package ordination;

import java.lang.reflect.Array;
import java.time.LocalDate;

public class DagligFast extends Ordination{
    // TODO
    Dosis[] doser = new Dosis[4];
    public DagligFast(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);

    }

    @Override
    public double samletDosis() {
        return 0;
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
