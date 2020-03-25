package lescases;

import entitesvivantes.Personnage;

public class Terre extends Case {

    public Terre() {
        setPeutEtreTraverseeParMonstre(false);
        setPeutEtreTraverseeParRockford(true);
    }

    public Terre(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setPeutEtreTraverseeParMonstre(false);
        setPeutEtreTraverseeParRockford(true);
    }

    @Override
    public void mettrePersoSurCase(Personnage p) {
        if(!this.estOccupee()) {
            if(p.getCasesTraversables().contains(this.getClass().getSimpleName())) {
                p.setPositionX(this.getPositionX());
                p.setPositionY(this.getPositionY());
                this.setEstIci(p);
                System.out.println("Le personnage " + p.getClass().getSimpleName() + " est positionné sur la case " +
                this.getClass().getSimpleName() + " de coordonnée : x = " + p.getPositionX() + ", y = " + p.getPositionY());            }
        }	
	}

} 