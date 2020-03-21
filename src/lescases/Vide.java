package lescases;

import entitesvivantes.Personnage;

public class Vide extends Case {

    public Vide(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setPeutEtreTraverseeParMonstre(true);
        setPeutEtreTraverseeParRockford(true);
    }

    @Override
    public void mettrePersoSurCase(Personnage p) {

    }
    
}