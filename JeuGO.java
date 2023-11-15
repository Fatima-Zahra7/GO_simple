import java.util.Scanner;

public class JeuGO {	
	private static String[]jouer={"Joueur 1","Joueur 2"};
	
	private static int[]score =new int[2];

	/*
	 * do While for pour faire travailler les programmes
	 * La boucle la plus interne sera le tableau puis la saisie des coordonnees suivit de l'alternation des joueurs 
	 */
	public static void coordination (){
		int laCase=0;
		int joueur=1;
		int [][]tab = new int [9][9];
		
			do {
				for (int i=0;i<jouer.length;i++) {
					System.out.print(jouer[i]+"  ");
				}
				System.out.println();
				
				for (int x=0;x<score.length;x++) {
					System.out.print(score[x]+"\t\t");
				}
				System.out.println("\n");
				afficherPlateauGoban(tab);
				saisieCoordonnee(tab,joueur);
				recalculVoisins(tab, joueur);
				joueur=alternanceJoueur(joueur);
			}
			while(gameOver(tab)==false);
		
		
		System.out.println(afficherPlateauGoban(tab));
		victoire();
	}
	
	public static void recalculVoisins(int[][]tab,int joueur) {
		
		int ennemi=alternanceJoueur(joueur);
		
		for(int x=0;x<tab.length;x++) {
			for(int y=0;y<tab[x].length;y++) {
				
				if(x==0 && y==0){
					voisin2HautGauche(tab,joueur,ennemi);
				}
				else if(x==0 && y==tab[tab.length-1].length-1) {
					voisin2HautDroite(tab,joueur,ennemi);
				}
				else if((x==tab.length-1 && y==0)){
					voisin2BasGauche(tab,joueur,ennemi);
				}
				else if(x==tab.length-1 && y==tab[tab.length-1].length-1){
					voisin2BasDroite(tab,joueur,ennemi);
				}
				else if(x==0 ){
					voisin3gauche(tab,joueur,x,y,ennemi);
				}
				else if(x==tab.length-1) {
					voisin3doite(tab, joueur, x, y,ennemi);
				}
				else if( y==0 ) {
					voisin3Haut(tab, joueur, x, y,ennemi);
				}
				else if(y==tab[tab.length-1].length-1) {
					voisin3Bas(tab, joueur, x, y,ennemi);
				}
				else {
					voisins4(tab,joueur,x,y,ennemi);
				}
				
			}
		}
	}
	
	//Plateau de jeu le Goban
	public static int[][] afficherPlateauGoban(int[][]tab) {
		char [] abscisse = {'A','B','C','D','E','F','G','H','I'};
		int [] ordonnee = { 0, 1, 2, 3, 4, 5, 6, 7, 8};
		char lettre=0;
		
		for(int x=0; x<abscisse.length; x++) {
			System.out.print("\t"+abscisse[x]+"  ");
		}
		System.out.println("\n");
		
		for(int y=0; y<ordonnee.length; y++) {
			System.out.print(ordonnee[y]+" : ");
			for(int colonne=0; colonne<tab[y].length; colonne++) {
				
				switch (tab[colonne][y]) {

		        case 0: lettre = '+';
		        break;
		        case 1: lettre = 'N';
		        break;
		        case 2: lettre = 'B';
		        break;
		        case 3: lettre = '+';
		        break;
		        case 4: lettre = '+';
		        break;
    		    }
				
				System.out.print("\t"+lettre+" ");
			}
			
			System.out.println("\n");
		}
		return tab;
	}
	// permet de savoir si la case est libre de saisie ou pas, il n'y a que deux valeurs possibles T et F cas joueur 1 ou 2
	public static boolean emplacementValide (int laCase, int joueur) {
		if(laCase==0) {
			return true;
		}
		if(joueur==1&&laCase==3) {
			return true;
		}
		if(joueur==2&&laCase==4) {
			return true;
		}
		return false;
	}
	
	/*Convertir les abscisses en ciffre pour ensuite envoyer le resultat dans la saisie coordonn�e et place les pions o� veut le joueur
	 * la fonction prend la valeur en ASCII de la lettre voulue moins la valeur ASCII de la lettre A 
	 */
	public static int charToInt(char valeur) {
		int val=(int)valeur - (int)'A';
		return val;
	}
	/*On va faire l'appel de charToInt qui va permettre de placer le pion a l'abscisse voulue
	 * On va saisir les coordonn�es du pion et grace � l'appel de la fonction EmplacementOuPas qui permet de savoir si la place est libre
	 */
	public static int[][] saisieCoordonnee (int[][] tab,int joueur) {
		Scanner saisie = new Scanner (System.in);
		//tab[9][9]
		char lettre;
		int y=0;
		int x=0;
	
		do {
			do {
				System.out.println("\nEntrez l'abscisse o� vous souhaitez placer votre pion");
				lettre=saisie.next().charAt(0);
				x=charToInt(lettre);
			}
			while(x<0 && x>8);
			System.out.println("Entrez l'ordonn�e o� vous souhaitez placer votre pion");
			y=saisie.nextInt();	
		}
		while(y<0 && y>8);
		
		if(emplacementValide(tab[x][y],joueur)==true) {
			tab[x][y]=joueur;
		}
		return tab;
	}
	//alternance entre les deux joueur
	public static int alternanceJoueur(int joueurEnCours) {
		int joueurNouveau;
		
		if(joueurEnCours==1) {
			joueurNouveau=2;  //est blanc
		}
		else {
			joueurNouveau=1; //est noir
		}
		return joueurNouveau;
	}
	
	/*Controle si une piece est encercl� 
	 * Pour cela on va v�rifier si on a des pions adverses en haut en bas a doite ET a gauche (4 voisins)
	 */
	public static void voisins4 (int[][]tab,int joueur,int x, int y, int ennemi ) {
		if(tab[x][y]==ennemi||tab[x][y]==0) {
			if(tab[x][y+1]==joueur && tab[x][y-1]==joueur && tab[x+1][y]==joueur && tab[x-1][y]==joueur) {//4 voisins pour noir
				if(ennemi==2) {
					tab[x][y]=3;
					score[0]++;
				}
				else {
					tab[x][y]=4;
					score[1]++;
				}
			}
		}	
	}
	
	/*Controle si une piece est encercl�
	 * Ensuite definir les cas en bordure cad lorsque x=0 ou x=8 ET quand y=8 ou y=0 (emplacement a 3 voisins)
	 */
	public static void voisin3gauche(int[][]tab,int joueur,int x,int y,int ennemi) {
		//3 voisins 
		if(tab[x][y]==ennemi||tab[x][y]==0) {
			if(tab[x][y+1]==joueur && tab[x+1][y]==joueur && tab[x][y-1]==joueur) {
				if(ennemi==2) {
					tab[x][y]=3;
					score[0]++;
				}
				else {
					tab[x][y]=4;
					score[1]++;
				}
			}
		}
	}
	public static void voisin3doite(int[][]tab,int joueur,int x,int y,int ennemi) {
		if(tab[x][y]==ennemi||tab[x][y]==0) {
			if( tab[x][y-1]==joueur && tab[x][y+1]==joueur && tab[x-1][y]==joueur) {
				if(ennemi==2) {
					tab[x][y]=3;
					score[0]++;
				}
				else {
					tab[x][y]=4;
					score[1]++;
				}
			}
		}
	}
	public static void voisin3Bas(int[][]tab,int joueur,int x,int y,int ennemi) {
		if(tab[x][y]==ennemi||tab[x][y]==0) {
			if(tab[x][y-1]==joueur && tab[x-1][y]==joueur && tab[x+1][y]==joueur) {
				if(ennemi==2) {
					tab[x][y]=3;
					score[0]++;
				}
				else {
					tab[x][y]=4;
					score[1]++;
				}
			}
		}
	}
	public static void voisin3Haut(int[][]tab,int joueur,int x,int y,int ennemi) {
		if(tab[x][y]==ennemi||tab[x][y]==0){
			if(tab[x+1][y]==joueur && tab[x-1][y]==joueur && tab[x][y+1]==joueur) { 
				if(ennemi==2) {
					tab[x][y]=3;
					score[0]++;
				}
				else {
					tab[x][y]=4;
					score[1]++;
				}
			}
		}
	}
	/*
	 * Controle si une piece est encercl� 
	 *  Et enfin verifier les coins les emplacement � deux voisins 
	 *  en (0,0)(0,8)(8,0)et(8,8)
	 */
	public static void voisin2HautGauche(int[][]tab,int joueur,int ennemi) { 
		if(tab[0][0]==ennemi||tab[0][0]==0) { //coin haut gauche
			if(tab[0][1]==joueur && tab[1][0]==joueur) {
				if(ennemi==2) {
					tab[0][0]=3;
					score[0]++;
				}
				else {
					tab[0][0]=4;
					score[1]++;
				}
			}
		}
	}
	public static void voisin2HautDroite(int[][]tab,int joueur,int ennemi){	//JE LACHE UN LIKE
		if(tab[tab.length-1][0]==ennemi||tab[tab.length-1][0]==0) { 
			if(tab[tab.length-2][0]==joueur && tab[tab.length-1][1]==joueur) {
				if(ennemi==2) {
					tab[tab.length-1][0]=3;
					score[0]++;
				}
				else {
					tab[tab.length-1][0]=4;
					score[1]++;
				}
			}
		}
	}	
	public static void voisin2BasGauche	(int[][]tab,int joueur,int ennemi) { //COEUR SUR TOI BG ...finalement pas du tout 
		if(tab[0][tab[tab.length-1].length-1]==ennemi||tab[0][tab[tab.length-1].length-1]==0) { //coin bas gauche
			if(tab[0][tab[tab.length-1].length-2]==joueur && tab[1][tab[tab.length-1].length-1]==joueur) {
				if(ennemi==2) {
					tab[0][tab[tab.length-1].length-1]=3;
					score[0]++;
				}
				else {
					tab[0][tab[tab.length-1].length-1]=4;
					score[1]++;
				}
			}	
		}
	}
	public static void voisin2BasDroite(int[][]tab,int joueur,int ennemi) { 
			
		if(tab[tab.length-1][tab[tab.length-1].length-1]==ennemi||tab[tab.length-1][tab[tab.length-1].length-1]==0) { //coin bas droite
			if(tab[tab.length-1][tab[tab.length-1].length-2]==joueur && tab[tab.length-2][tab[tab.length-1].length-1]==joueur) {
				if(ennemi==2) {
					tab[tab.length-1][tab[tab.length-1].length-1]=3;
					score[0]++;
				}
				else {
					tab[tab.length-1][tab[tab.length-1].length-1]=4;	
					score[1]++;
				}
			}
		}
	}
	
	public static boolean gameOver(int[][]tab) {
		for(int x=0;x<tab.length;x++) {
			for(int y=0;y<tab[x].length;y++) {
				if(tab[x][y]==0) {
			return false;
				}
			}
		}
		System.out.println("La partie est termin�e");
		return true;
	}

	public static void victoire () {
		if(score[0]>score[1]) {
			System.out.println("Le joueur noir a gagn�");
		}
		else if (score[1]>score[0]) {
			System.out.println("Le joueur blanc a gagn�");
		}
		else{
			System.out.println("Egalité, aucun joueur n'a perdu");
		}
		
	}
}