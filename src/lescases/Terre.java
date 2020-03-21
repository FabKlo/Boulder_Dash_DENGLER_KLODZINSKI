package lescases;

import entitesvivantes.Personnage;

public class Terre extends Case{

    public Terre(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setPeutEtreTraverseeParMonstre(false);
        setPeutEtreTraverseeParRockford(true);
    }

	@Override
	public void mettrePersoSurCase(Personnage p) {		
	}

} 