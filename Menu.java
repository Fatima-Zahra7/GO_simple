import java.util.Scanner;

public class Menu {
	public static void choix (){
		Scanner saisie = new Scanner(System.in);
		int choix=0;
		
		do {
			System.out.println("1.Lancer une partie\n"
					+"2.Consulter les regles\n"
					+"3.quitter\n");
			
			System.out.println("Que souhaitez vous faire");
			choix=Integer.parseInt(saisie.nextLine());
		}
		while(choix>3);
		
		switch (choix){
			case 1:JeuGO.coordination();
			break;
			
			case 2:Regle.regle_jeu();
			break;
			
			case 3:System.out.println("Au revoir");
			break;
		}
	}
}