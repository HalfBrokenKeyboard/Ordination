package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    //DagligSkaev er en subklasse af ordination og har derfor adgang til alle felter og metoder i ordination


    private Patient patient;

    //DagligSkaev indeholder også et arraylist kaldet doser, som indeholder alle doserne for en enkelt dag
    private ArrayList<Dosis> doser;




    /*
    Dette er konstruktøren for klassen DagligSkaev, som tager en startDato, slutDato og en patient,
    som parametre, og kalder superklassen konstruktør. (Super)
     */
    public DagligSkaev(LocalDate startDate, LocalDate slutDate, Patient patient) {
        super(startDate, slutDate, patient);
        this.patient = patient;
        this.doser = new ArrayList<>();
    }

    //get metode for arrayet doser
    public ArrayList<Dosis> getDoser() {
        return new ArrayList<>(doser);
    }


    public void setDoser(ArrayList<Dosis> doser) {
        this.doser = doser;
    }


    /*
    Denne metode tager 2 parametre, tidspunktet for doseringen, og antallet af doser
    og herefter opretter en ny dosis instans og tilføjer den til "doser" arrayetlist
     */
    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid, antal);
        doser.add(dosis);
    }

    /*
    Denne metode beregner den samlede dosis af medicin i perioden mellem startDato og slutDato
    Dette gøres ved at kalde metoden DoegnDosis() og gange resultatet med antallet af dage i perioden
     */
    @Override
    public double samletDosis() {
        double totalDosis = doegnDosis();
        double numberOfDays = ChronoUnit.DAYS.between(getStartDen(), getSlutDen()) + 1;
        return totalDosis * numberOfDays;
    }

    /*
    Denne metode, doegnDosis(), beregner den samlede mængde af medicin, der skal tages på en enkelt dag, baseret på dosis og antal doser pr. dag.

    Metoden har tre betingelser, der skal opfyldes, før beregningen kan udføres:
    Der skal være en startdato for ordinationen
    Der skal være en slutdato for ordinationen
    Der skal være mindst én dosis, der skal tages
    Hvis en af betingelserne ikke er opfyldt, vil metoden kaste en IllegalArgumentException med en passende besked.

    Hvis alle betingelserne er opfyldt, vil metoden beregne den samlede mængde af medicin, der skal tages på en enkelt dag,
     ved at summere alle doser. Totaldosis returneres derefter som en double.
     */
    @Override
    public double doegnDosis() {

        if(getStartDen() == null) {
            throw new IllegalArgumentException("Ingen start dato");

        }

        if(getSlutDen() == null){
            throw new IllegalArgumentException("Ingen slut dato");
        }

        if(getDoser().size() <= 0){
            throw new IllegalArgumentException("Doser er lig med 0");
        }
            double totalDosis = 0.0;
            for (Dosis dosis : doser) {
                totalDosis += dosis.getAntal();
            }
            return totalDosis;
        }


    //Metoden returer en streng, der beskriver typen af ordination
    @Override
    public String getType() {
        return "DagligSkaev";
    }



}







