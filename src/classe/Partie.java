package classe;
public class Partie{
	
	public Jeu jeu;
	public String titre;
	public Personne mj;
	public Personne[] joueurs;
	
	public Partie(Jeu jeu, String titre,Personne mj,Personne[] joueurs){
		this.jeu=jeu;
		this.titre=titre;
		this.mj=mj;
		this.joueurs=joueurs;
	}
	    
}
