package lescases;

import entitesvivantes.Personnage;

public class Acier extends Case {

    public Acier(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setPeutEtreTraverseeParMonstre(false);
        setPeutEtreTraverseeParRockford(false);

    }

    @Override
    public void mettrePersoSurCase(Personnage p) {
        System.out.println("Cette case est de type" + 
        getClass().getSimpleName() +", le personnage "  + 
        p.getClass().getSimpleName() + " ne peut pas être ici !");
    }
    
}