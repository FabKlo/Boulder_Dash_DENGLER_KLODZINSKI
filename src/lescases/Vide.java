package lescases;

import entitesvivantes.Personnage;

public class Vide extends Case {

    public Vide() {
        setEstSoumisALaGravite(false);
    }

    public Vide(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setEstSoumisALaGravite(false);
    }

    @Override
    public void mettrePersoSurCase(Personnage p) {
        if(!this.estOccupee()) {
            if(p.getCasesTraversables().contains(this.getClass().getSimpleName())) {
                p.setPositionX(this.getPositionX());
                p.setPositionY(this.getPositionY());
                this.setEstIci(p);
                /*System.out.println("Le personnage " + p.getClass().getSimpleName() + " est positionné sur la case " +
                this.getClass().getSimpleName() + " de coordonnée : x = " + p.getPositionX() + ", y = " + p.getPositionY());*/
            }
        }
    }
    
}