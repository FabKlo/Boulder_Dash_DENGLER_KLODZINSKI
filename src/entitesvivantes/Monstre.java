package entitesvivantes;

import lescases.Vide;

public class Monstre extends Personnage {

    private int vie;

    public Monstre(int x, int y){
        setPositionX(x);
        setPositionY(y);
        setVie(1);
        addCasesTraversables(Vide.class.getSimpleName());
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }


}