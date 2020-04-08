package entitesvivantes;

import lescases.Vide;

public class Monstre extends Personnage {

    private String direction;

	public static final String GAUCHE = "gauche";
	public static final String DROITE = "droite";
	public static final String HAUT = "haut";
	public static final String BAS = "bas";

    /**
     * Création d'un monstre avec vie = 1
     */
    public Monstre(){
        setVie(1);
        addCasesTraversables(Vide.class.getSimpleName());
        setDirection(DROITE);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}