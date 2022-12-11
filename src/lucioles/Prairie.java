package lucioles;

import outils.*;

// Étapes 2 et 3 : Définition de prairies, et simulation sans interaction

public class Prairie {

	// Seuil au delà duquel une luciole émet un flash.
	public static final double SEUIL = 100.0;

	// Indices nommés pour accéder aux données d'une luciole
	public static final int ENERGIE = 0;
	public static final int DELTA = 1;


	
	
	
	
	
	public static void afficheTableau (double[] tab) {
		for (int i = 0; i< tab.length; i++) {
			System.out.print(tab[i] + " ");
		}
		System.out.println();
	}
	
	
	public static void afficheDoubleTableau (double[][] tab) {
		for (int i = 0; i< tab.length; i++) {
			for (int k = 0; k < tab[i].length; k++) {
				System.out.print(tab[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	
	//Question 15
	public static void affichePrairie (int[][] tab, double[][] population) {
		
		for (int  i = -1; i < tab.length+1; i++) {
			for (int k = -1; k <tab[0].length; k++) {

				if (k == -1) System.out.print("#");
				else {
					if ((i == -1) || (i == tab.length)) System.out.print("#");
					else {
						if (tab[i][k] == -1) System.out.print(" ");
						else {
							if (population[tab[i][k]][ENERGIE] <SEUIL) System.out.print(".");
							else System.out.print("*");
						}
						}
					}
				}

			System.out.println("#");
			}
		System.out.println();
	}
	
	
	
	
	
	
	//Question 10
	public static double[] creerLuciole() {
		double[] luciole = {RandomGen.rGen.nextDouble(0, 100), RandomGen.rGen.nextDouble(0, 1)};
		return luciole;
	}
	
	//Question 11
	public static double[] incrementeLuciole (double[] luciole) {
		luciole[ENERGIE] += luciole[DELTA];
		return luciole;
	}
	
	//Question 12
	public static double[][] creerPopulation (int nbLucioles){
		double[][] population = new double[nbLucioles][];
		for (int i = 0; i < population.length; i++) {
			population[i] = creerLuciole();
		}
		
		return population;
	}
	
	
	
	
	//Question 14
	public static int[][] prairieVide (int nbLignes, int nbColonnes) {
		int[][] prairie = new int[nbLignes][nbColonnes];
		for (int i = 0; i < prairie.length; i++) {
			for (int k = 0; k < prairie[i].length; k++) {
				prairie[i][k] = -1;
			}
		}
		return prairie;
	}
	
	
	//Question 17
	public static int[][] prairieLuciole (int nbLignes, int nbColonnes, double[][] population){
		int[][] prairie = prairieVide(nbLignes, nbColonnes);
		int ligne = 0;
		int colonne = 0;
		for (int i = 0; i < population.length; i++) {
			ligne = RandomGen.rGen.nextInt(nbLignes - 1);
			colonne = RandomGen.rGen.nextInt(nbColonnes-1);
			if (prairie[ligne][colonne] != -1) {
				while (prairie[ligne][colonne] != -1) {
					ligne = RandomGen.rGen.nextInt(nbLignes);
					colonne = RandomGen.rGen.nextInt(nbColonnes);
				}
			}
			prairie[ligne][colonne] = i;
		}
		return prairie;
	}
	
	
	
	
	
	
	
	
	
	
	//Question 19
	public static void simulationPrairie (int[][] prairie, double[][] population, int nbPas) {
		for (int i = 0; i < nbPas; i++){
			for (int k = 0; k < population.length; k++) {
				if (population[k][ENERGIE] >= SEUIL) {
					population[k][ENERGIE] = 0;
				}
				population[k][ENERGIE] = LucioleSeule.incrementeLuciole(population[k][ENERGIE], population[k][DELTA]);
			}
			affichePrairie(prairie, population);
			}		
	}
				
	
	
	
	//Question 21
	public static void simulationPrairieGIF (int[][] prairie, double[][] population, int nbPas) {
		String nomFichier = "img/luciole";
		String[] images = new String[nbPas];
		for (int i = 0; i < nbPas; i++){
			nomFichier = "img/luciole" + i + ".bmp";
			for (int k = 0; k < population.length; k++) {
				if (population[k][ENERGIE] >= SEUIL) {
					population[k][ENERGIE] = 0;
				}
				population[k][ENERGIE] = LucioleSeule.incrementeLuciole(population[k][ENERGIE], population[k][DELTA]);
			}
			
			BitMap.bmpEcritureFichier(nomFichier, prairie, population, 100);
			images[i] = nomFichier;
			
		}
		GifCreator.construitGIF("simu/monGIFanime.gif", images);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		//Question 13
		double[] luciole1 = creerLuciole();
		afficheTableau(luciole1);
		incrementeLuciole(luciole1);
		afficheTableau(luciole1);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		double[][] population =creerPopulation(10);
		afficheDoubleTableau(population);
		System.out.println();
		afficheTableau(population[9]);
		System.out.println();
		System.out.println();
		
		
		//Question 16
		int[][] prairie1 = prairieVide(10, 10);
		prairie1[1][5] = 5;
		affichePrairie(prairie1, population);
		prairie1[9][3] = 4;
		population[1][ENERGIE] = 100;
		prairie1[4][6] = 1;
		affichePrairie(prairie1, population);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		//Question 18
		double[][] population2 =creerPopulation(100);
		afficheDoubleTableau(population2);
		population2[1][ENERGIE] = 100;
		System.out.println();
		int[][] prairie2 = prairieLuciole(10, 10, population2);
		
		affichePrairie(prairie2, population2);
		
		
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
		
		//Question 20
		double[][] population3 =creerPopulation(10000);
		int[][] prairie3 = prairieLuciole(100, 100, population3);
		simulationPrairie(prairie3, population3, 500);
		
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

		
		
		//Question 22
		double[][] population4 =creerPopulation(500);
		int[][] prairie4 = prairieLuciole(316, 316, population4);
		
		simulationPrairieGIF(prairie4, population4, 100);	
		
	}

}
