package lucioles;

// Étape 1 : Simulation d'une seule luciole

public class LucioleSeule {

	// Seuil au delà duquel une luciole émet un flash.
	public static final double SEUIL = 100.0;
	
	
	public static char symboliseLuciole (double niveauEnergie) {
		if (niveauEnergie >= SEUIL)
			return '*';
		else
			return '.';
	}

	// Question 3
	public static void afficheLuciole (double niveauEnergie, boolean verbeux) {
		System.out.print(symboliseLuciole(niveauEnergie));
		if (verbeux)
			System.out.print(" " + niveauEnergie);

		System.out.println();
	}

	//Question 6
	public static double incrementeLuciole (double niveauEnergie, double deltaEnergie) {
		return niveauEnergie + deltaEnergie;
	}

	//Question 8
	public static void simuleLucioleNbPas (int nbPas) {

		double lucioleEnergie = RandomGen.rGen.nextDouble(0, 100);
		double lucioleDeltaEnergie = RandomGen.rGen.nextDouble(0, 1);

		for (int i = 0; i < nbPas; i++) {

			lucioleEnergie = incrementeLuciole(lucioleEnergie, lucioleDeltaEnergie);
			afficheLuciole(lucioleEnergie, true);

			if (lucioleEnergie >= SEUIL)
				lucioleEnergie = 0;
		}

	}

	//Question 9
	public static void simuleLucioleNbFlashs (int nbFlashs) {

		double lucioleEnergie = RandomGen.rGen.nextDouble(0, 100);
		double lucioleDeltaEnergie = RandomGen.rGen.nextDouble(0, 1);
		int nombreFlashs = 0;

		while (nombreFlashs < nbFlashs) {
			lucioleEnergie = incrementeLuciole(lucioleEnergie, lucioleDeltaEnergie);

			afficheLuciole(lucioleEnergie, nbFlashs < 3);

			if (lucioleEnergie >= SEUIL) {
				lucioleEnergie = 0;
				nombreFlashs += 1;
			}
		}
	}

	public static void main(String[] args) {
		// Question 1
		double lucioleEnergie = RandomGen.rGen.nextDouble(0, 100);
		System.out.println(lucioleEnergie);
		System.out.println(symboliseLuciole(lucioleEnergie));
		//Question 4
		afficheLuciole(lucioleEnergie, true);
		lucioleEnergie += lucioleEnergie + RandomGen.rGen.nextDouble();
		afficheLuciole(lucioleEnergie, true);
		lucioleEnergie += lucioleEnergie + RandomGen.rGen.nextDouble();
		afficheLuciole(lucioleEnergie, true);
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		//Question 5
		double lucioleDeltaEnergie = RandomGen.rGen.nextDouble(0, 1);
		
		//Questioin 7
		afficheLuciole(incrementeLuciole(lucioleEnergie, lucioleDeltaEnergie), true);
		lucioleEnergie = incrementeLuciole(lucioleEnergie, lucioleDeltaEnergie);
		afficheLuciole(incrementeLuciole(lucioleEnergie, lucioleDeltaEnergie), true);
		lucioleEnergie = incrementeLuciole(lucioleEnergie, lucioleDeltaEnergie);
		afficheLuciole(incrementeLuciole(lucioleEnergie, lucioleDeltaEnergie), true);
		lucioleEnergie = incrementeLuciole(lucioleEnergie, lucioleDeltaEnergie);
		afficheLuciole(incrementeLuciole(lucioleEnergie, lucioleDeltaEnergie), true);
		lucioleEnergie = incrementeLuciole(lucioleEnergie, lucioleDeltaEnergie);
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		
		simuleLucioleNbPas(300);
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		simuleLucioleNbFlashs(700);
		
	}

}
