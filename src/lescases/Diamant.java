package lescases;

import entitesvivantes.Personnage;
import entitesvivantes.Rockford;

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
            //if() {
                p.setPositionX(this.getPositionX());
                p.setPositionY(this.getPositionY());
            //}

        }

    }
    
}