package controller;

import ordination.*;
import storage.Storage;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Controller {
	private Storage storage;
	private static Controller controller;

	public Controller() {
		storage = new Storage();
	}

	public static Controller getController() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public static Controller getTestController() {
		return new Controller();
	}

	/**
	 * Hvis startDato er efter slutDato kastes en IllegalArgumentException og
	 * ordinationen oprettes ikke
	 * Pre: startDen, slutDen, patient og laegemiddel er ikke null
	 * Pre: antal >= 0
	 * @return opretter og returnerer en PN ordination.
	 */
	public PN opretPNOrdination(LocalDate startDen, LocalDate slutDen,
								Patient patient, Laegemiddel laegemiddel, double antal) {
		PN pn = new PN(startDen, slutDen, patient, laegemiddel, antal);
		pn.setLaegemiddel(laegemiddel);
		if (!checkStartFoerSlut(startDen, slutDen)) {
			throw new IllegalArgumentException("Slut dato er før star dato");
		}

		if (antal <= 0) {
			throw new IllegalArgumentException("Antal enheder er mindre end eller lige med 0");
		}

		if (pn.getAntalGangeGivet() < 0) {
			throw new IllegalArgumentException("Dosis ikke givet");
		}
		return pn;
	}

	/**
	 * Opretter og returnerer en DagligFast ordination. Hvis startDato er efter
	 * slutDato kastes en IllegalArgumentException og ordinationen oprettes ikke
	 * Pre: startDen, slutDen, patient og laegemiddel er ikke null
	 * Pre: margenAntal, middagAntal, aftanAntal, natAntal >= 0
	 */
	public DagligFast opretDagligFastOrdination(LocalDate startDen,
			LocalDate slutDen, Patient patient, Laegemiddel laegemiddel,
			double morgenAntal, double middagsAntal, double aftenAntal,
			double natAntal) {

		DagligFast ordination = new DagligFast(startDen, slutDen, patient, morgenAntal, middagsAntal, aftenAntal, natAntal);
		ordination.setLaegemiddel(laegemiddel);

		if(startDen.isAfter(slutDen)){
			throw new IllegalArgumentException("Slut dato er før start dato");
		}
		if (morgenAntal <= 0  && middagsAntal <= 0 && aftenAntal <= 0 && natAntal <= 0) {
			throw new IllegalArgumentException("Skal angive mindst en dosis");
		}

		return ordination;
	}

	/**
	 * Opretter og returnerer en DagligSkæv ordination. Hvis startDato er efter
	 * slutDato kastes en IllegalArgumentException og ordinationen oprettes ikke.
	 * Hvis antallet af elementer i klokkeSlet og antalEnheder er forskellige kastes også en IllegalArgumentException.
	 *
	 * Pre: startDen, slutDen, patient og laegemiddel er ikke null
	 * Pre: alle tal i antalEnheder > 0
	 */

	/*
	Denne metode opretter og retunerer en ny "DagligSkaev ordination".
	 Metoden kontrollerer først om, startdatoen er efter slutdatoen, og kaster en Exception, hvis det er tilfældet.
	Herefter kontrollerer den, om alle angivne antal enheder er positive, og kaster en Exception, hvis det ikke er tilfældet.
	Metoden opretter derefter en ny dagligskaev ordination og tildeler den angivne patient og lægemiddel. Den opretter
	herefter en dosis for hver angivet tid og antalenheder ved hjælp af opretDosis() metoden i dagligSkaev klassen. Endelig
	returerer metoden den oprettet ordination.
	 */
	public DagligSkaev opretDagligSkaevOrdination(LocalDate startDen,
			LocalDate slutDen, Patient patient, Laegemiddel laegemiddel,
			LocalTime[] klokkeSlet, double[] antalEnheder) {

			if(startDen.isAfter(slutDen)){
				throw new IllegalArgumentException("Startdato må ikke være efter slutdato");
			}
			for(double antal : antalEnheder){
				if(antal <= 0){
					throw new IllegalArgumentException("Antal enheder skal være positivt");
				}
			}
			DagligSkaev ordination = new DagligSkaev(startDen, slutDen, patient);
			ordination.setLaegemiddel(laegemiddel);
			ordination.setPatient(patient);
			for (int i = 0; i < klokkeSlet.length ; i++) {
				LocalTime tid = klokkeSlet[i];
				double antal = antalEnheder[i];
				ordination.opretDosis(tid,antal);
		}
			return ordination;

	}

	/**
	 * En dato for hvornår ordinationen anvendes tilføjes ordinationen. Hvis
	 * datoen ikke er indenfor ordinationens gyldighedsperiode kastes en
	 * IllegalArgumentException
	 * Pre: ordination og dato er ikke null
	 */
	public void ordinationPNAnvendt(PN ordination, LocalDate dato) {
		if (!ordination.givDosis(dato)) {
			throw new IllegalArgumentException("Fejl");
		}
	}



	/**
	 * Den anbefalede dosis for den pågældende patient (der skal tages hensyn
	 * til patientens vægt). Det er en forskellig enheds faktor der skal
	 * anvendes, og den er afhængig af patientens vægt.
	 * Pre: patient og lægemiddel er ikke null
	 */
	public double anbefaletDosisPrDoegn(Patient patient, Laegemiddel laegemiddel) {
		double vægt = patient.getVaegt();
		double doseFaktor;

		if(vægt < 25){
			doseFaktor = laegemiddel.getEnhedPrKgPrDoegnLet();
		} else if (vægt <= 120){
			doseFaktor = laegemiddel.getEnhedPrKgPrDoegnNormal();
		} else {
			doseFaktor = laegemiddel.getEnhedPrKgPrDoegnTung();
		}
		return vægt * doseFaktor;
	}

	/**
	 * For et givent vægtinterval og et givent lægemiddel, hentes antallet af
	 * ordinationer.
	 * Pre: laegemiddel er ikke null
	 */
	public int antalOrdinationerPrVægtPrLægemiddel(double vægtStart,
			double vægtSlut, Laegemiddel laegemiddel) {
		int count = 0;
		for(Patient patient : getAllPatienter()){
			if(patient.getVaegt() >= vægtStart && patient.getVaegt() <= vægtSlut){
				for(Ordination ordination : patient.getOrdinationer()){
					if(ordination.getLaegemiddel().equals(laegemiddel)){
						count++;
					}
				}
			}
		}
		return count;
	}

	public List<Patient> getAllPatienter() {
		return storage.getAllPatienter();
	}

	public List<Laegemiddel> getAllLaegemidler() {
		return storage.getAllLaegemidler();
	}
	//yo test slet

	/**
	 * Metode der kan bruges til at checke at en startDato ligger før en
	 * slutDato.
	 *
	 * @return true hvis startDato er før slutDato, false ellers.
	 */
	private boolean checkStartFoerSlut(LocalDate startDato, LocalDate slutDato) {
		boolean result = true;
		if (slutDato.compareTo(startDato) < 0) {
			result = false;
		}
		return result;
	}

	public Patient opretPatient(String cpr, String navn, double vaegt) {
		Patient p = new Patient(cpr, navn, vaegt);
		storage.addPatient(p);
		return p;
	}

	public Laegemiddel opretLaegemiddel(String navn,
			double enhedPrKgPrDoegnLet, double enhedPrKgPrDoegnNormal,
			double enhedPrKgPrDoegnTung, String enhed) {
		Laegemiddel lm = new Laegemiddel(navn, enhedPrKgPrDoegnLet,
				enhedPrKgPrDoegnNormal, enhedPrKgPrDoegnTung, enhed);
		storage.addLaegemiddel(lm);
		return lm;
	}

	public void createSomeObjects() {
		this.opretPatient("121256-0512", "Jane Jensen", 63.4);
		this.opretPatient("070985-1153", "Finn Madsen", 83.2);
		this.opretPatient("050972-1233", "Hans Jørgensen", 89.4);
		this.opretPatient("011064-1522", "Ulla Nielsen", 59.9);
		this.opretPatient("090149-2529", "Ib Hansen", 87.7);

		this.opretLaegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
		this.opretLaegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
		this.opretLaegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
		this.opretLaegemiddel("Methotrexat", 0.01, 0.015, 0.02, "Styk");

		this.opretPNOrdination(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 12),
				storage.getAllPatienter().get(0), storage.getAllLaegemidler()
						.get(1),
				123);

		this.opretPNOrdination(LocalDate.of(2021, 2, 12), LocalDate.of(2021, 2, 14),
				storage.getAllPatienter().get(0), storage.getAllLaegemidler()
						.get(0),
				3);

		this.opretPNOrdination(LocalDate.of(2021, 1, 20), LocalDate.of(2021, 1, 25),
				storage.getAllPatienter().get(3), storage.getAllLaegemidler()
						.get(2),
				5);

		this.opretPNOrdination(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 12),
				storage.getAllPatienter().get(0), storage.getAllLaegemidler()
						.get(1),
				123);

		this.opretDagligFastOrdination(LocalDate.of(2021, 1, 10),
				LocalDate.of(2021, 1, 12), storage.getAllPatienter().get(1),
				storage.getAllLaegemidler().get(1), 2, 0, 1, 0);

		LocalTime[] kl = { LocalTime.of(12, 0), LocalTime.of(12, 40),
				LocalTime.of(16, 0), LocalTime.of(18, 45) };
		double[] an = { 0.5, 1, 2.5, 3 };

		this.opretDagligSkaevOrdination(LocalDate.of(2021, 1, 23),
				LocalDate.of(2021, 1, 24), storage.getAllPatienter().get(1),
				storage.getAllLaegemidler().get(2), kl, an);
	}

}
