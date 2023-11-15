
public class Regle {
	
	public static void regle_jeu() {
			
		System.out.println("Vous voici dans la rubrique r�gle\n");
			
		System.out.println("Le but du jeu est simple: agrandir son territoire gr�ce � ses pions\n"
				
					+ "\nD�roulement:\n"
					+ "\tLe go se joue � deux.\n"
					+ "\tCelui qui commence joue avec les pierres noires et l'autre avec les blanches.\n"
					+ "\t� tour de r�le,les joueurs posent une pierre de leur couleur sur une intersection inoccup�e du goban ou bien ils passent.\n"
					
					+ "Capture:\n"
					+ "\tLorsqu'un joueur supprime la derni�re libert� d'un cha�ne adverse, il la capture en retirant du goban les pierres de cette cha�ne.\n"
					+ "\tDe plus, en posant une pierre, un joueur ne doit pas construire une cha�ne sans libert�, sauf si par ce coup il capture une cha�ne adverse.\n"
					
					+ "\tLorsqu'un pion n'a plus qu'une libert�, on dit qu'il est en atari.\n"
					+ "\tAtari est un terme qui d�signe une situation o� une pierre est entour�s par les pierres adverses \n"
					+" Cette version a �t� pens� pour des d�butants");
	}
		
}


