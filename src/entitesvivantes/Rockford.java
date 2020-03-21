package entitesvivantes;

public class Rockford extends Personnage {

    private int vie;
    private int compteurDiamant;

    public Rockford(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setVie(3);
        setCompteurDiamant(0);
    } 

public int getVie() {
	return vie;
}

public void setVie(int vie) {
	this.vie = vie;
}

public int getCompteurDiamant() {
	return compteurDiamant;
}

public void setCompteurDiamant(int compteurDiams) {
	this.compteurDiamant = compteurDiams;
}



}