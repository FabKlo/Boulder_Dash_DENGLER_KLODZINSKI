package lescases;

import entitesvivantes.Personnage;

public class Diamant extends Case {

    public Diamant(int x, int y) {
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
                System.out.println("le personnage " + p.getClass().getSimpleName() + "peut être sur cette case qui est un diamant");
            }
            else
                System.out.println("le personnage " + p.getClass().getSimpleName() + "ne peut pas être sur cette case qui est un diamant");

        }
        else
            System.out.println("cette case est déjà occupé par " + this.getEstIci().getClass().getSimpleName());
    }
    
}