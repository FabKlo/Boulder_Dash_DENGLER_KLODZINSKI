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
        if(!this.estOccupee()) {
            if(p.getCasesTraversables().contains(this.getClass().getSimpleName())) {
                p.setPositionX(this.getPositionX());
                p.setPositionY(this.getPositionY());
                System.out.println("ton test assure de fou !");
            }
        }	
	}

} 