package lescases;

import entitesvivantes.Personnage;

public class Rocher extends Case {

    public Rocher(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setPeutEtreTraverseeParMonstre(false);
        setPeutEtreTraverseeParRockford(false);
    }

    @Override
    public void mettrePersoSurCase(Personnage p) {

    }
    
}