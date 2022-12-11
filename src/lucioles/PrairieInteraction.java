package lucioles;

import outils.*;

// Étape 4: Simulation d'une prairie avec interaction entre les lucioles

public class PrairieInteraction {

	// Seuil au delà duquel une luciole émet un flash.
	public static final double SEUIL = 100.0;

	// Indices nommés pour accéder aux données d'une luciole
	public static final int ENERGIE = 0;
	public static final int DELTA = 1;

	// Définition de l'apport d'énergie par flash, et du rayon de voisinage
	public static final double APPORT = 15.0;
	public static final int RAYON = 2;

	
	
	
	
	public static void afficheVoisin (int[][] tab) {
		for (int i = 0; i <tab.length; i++ ) {
			for (int k= 0; k < tab[i].length; k++) {
				System.out.print(tab[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public static void affichePopulationEnergie(double[][] population) {
		for (int i = 0; i < population.length; i++) {
			System.out.print(population[i][ENERGIE] + " ");
		}
		System.out.println();
	}
	
	
	
	
	
	
	// renvois un tableau avec les positions de toutes les lucioles d'une population
	public static int[][] positionLucioles (int[][] prairie, double[][] population){
		int[][] positions = new int[population.length][2];
		for (int i = 0; i < prairie.length; i++) {
			for (int k = 0; k < prairie[i].length; k++) {
				if (prairie[i][k] != -1){
					positions[prairie[i][k]][0] = i;
					positions[prairie[i][k]][1] = k;
				}
			}
		}
		return positions;
	}
	
	
	
	
	
	
	
	
	
	
	
	// renvois un tableau avec la liste des voisins d'une luciole d'une population dans une prairie
	public static int[] voisinsLuciole (int[][] prairie, double[][] population, int luciole){
		int[] voisins;
		int[][] positions = positionLucioles(prairie, population);
		int nbVoisins = 0;
		for (int i =0; i < population.length; i++) {
			if (i != luciole) {
				if ((positions[i][0] >= positions[luciole][0]-2) && (positions[i][0] <= positions[luciole][0]+2) && (positions[i][1] >= positions[luciole][1]-2) && (positions[i][1] <= positions[luciole][1]+2)) {
					nbVoisins += 1;
				}
			}
		}
		voisins = new int[nbVoisins];
		int nbVoi = 0;
		for (int i =0; i < population.length; i++) {
			if (i != luciole) {
				if ((positions[i][0] >= positions[luciole][0]-2) && (positions[i][0] <= positions[luciole][0]+2) && (positions[i][1] >= positions[luciole][1]-2) && (positions[i][1] <= positions[luciole][1]+2)) {
					if (nbVoi < nbVoisins) {
						voisins[nbVoi] = i;
						nbVoi += 1;
					}
				}
			}
		}
			
		
		
		return voisins;
	}
	
	
	
	
	
	
	
	//Question 23
	// renvois un tableau avec les voisins de toutes les lucioles d'une population dans une prairie
	public static int[][] voisinage (int[][] prairie, double[][] population){
		int[][] positions= new int[population.length][];
		for (int i = 0; i < population.length; i++) {
			positions[i] = voisinsLuciole(prairie, population, i);
		}
		return positions;
	}
	
	
	
	//Question 24
	public static void incrementeLuciole (double[][] population, int[][] voisinage, int luciole) {
		for (int i = 0; i < voisinage[luciole].length; i++) {
			if (population[voisinage[luciole][i]][ENERGIE] >= SEUIL) {
				population[luciole][ENERGIE] += APPORT;
			}
		}
	}
	
	
	//Question 25
	public static void simulationPrairieGIFInteraction (int[][] prairie, double[][] population, int nbPas) {
		String nomFichier = "img/lucioleInteraction";
		String[] images = new String[nbPas];
		double[][] nextPopulation = population;
		int[][] voisins = voisinage(prairie, population);
		for (int i = 0; i < nbPas; i++){
			nomFichier = "img/lucioleInteraction" + i + ".bmp";
			for (int k = 0; k < population.length; k++) {
				if (population[k][ENERGIE] >= SEUIL) {
					for (int  j = 0; j < voisins[k].length; j++) {
						nextPopulation[voisins[k][j]][ENERGIE] += 15;
					}
					population[k][ENERGIE] = 0;
				}
				population[k][ENERGIE] += nextPopulation[k][ENERGIE];
				population[k][ENERGIE] = LucioleSeule.incrementeLuciole(population[k][ENERGIE], population[k][DELTA]);
			}
			
			BitMap.bmpEcritureFichier(nomFichier, prairie, population, 100);
			images[i] = nomFichier;
			
		}
		GifCreator.construitGIF("simu/monGIFanimeInteraction.gif", images);
	}
	

	
	
	
	

	
	
	
	public static void main(String[] args) {
		double[][] population = Prairie.creerPopulation(10);
		int[][] prairie = Prairie.prairieLuciole(10, 10, population);
		Prairie.affichePrairie(prairie, population);
		afficheVoisin(voisinage(prairie, population));
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		double[][] population1 = Prairie.creerPopulation(3);
		population1[1][ENERGIE] = 100;
		int[][] prairie1 = Prairie.prairieLuciole(2, 2, population1);
		Prairie.affichePrairie(prairie1, population1);
		affichePopulationEnergie(population1);
		incrementeLuciole(population1, voisinage(prairie1, population1), 2);
		affichePopulationEnergie(population1);
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		double[][] population4 = Prairie.creerPopulation(500);
		int[][] prairie4 = Prairie.prairieLuciole(30, 30, population4);
		Prairie.affichePrairie(prairie4, population4);
		simulationPrairieGIFInteraction(prairie4, population4, 100);
		
		
		double[][] population2 = Prairie.creerPopulation(100);
		int[][] prairie2 = Prairie.prairieLuciole(25, 25, population2);
		simulationPrairieGIFInteraction(prairie2, population2, 100);
		//Question 26 : Les lucioles forment des clusters qui interagissent à intervalles réguliers.
		//Plus un cluster est grand et compact plus il clignotera fréquemment.
		//Tout comme les clusters proches peuvent s’influencer (plus ou moins) les un les autres,
		//centaines lucioles ne faisant parties d’aucun groupe clignotent de manière indépendantes.
	}

}
