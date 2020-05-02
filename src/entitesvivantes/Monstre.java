package entitesvivantes;

import lescases.Vide;

public class Monstre extends Personnage {

    private String direction;

	public static final String GAUCHE = "gauche";
	public static final String DROITE = "droite";
	public static final String HAUT = "haut";
    public static final String BAS = "bas";
    
    private int nbrDiamantsDonne;

    /**
     * Creation d'un monstre avec vie = 1
     */
    public Monstre() {
        setVie(1);
        addCasesTraversables(Vide.class.getSimpleName());
        setDirection(GAUCHE);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getNbrDiamantsDonne() {
        return nbrDiamantsDonne;
    }

    public void setNbrDiamantsDonne(int nbrDiamantsDonne) {
        this.nbrDiamantsDonne = nbrDiamantsDonne;
    }

}