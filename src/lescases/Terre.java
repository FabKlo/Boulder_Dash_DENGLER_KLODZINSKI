package lescases;

import entitesvivantes.Personnage;

public class Terre extends Case {

    public Terre() {
        setEstSoumisALaGravite(false);
    }

    public Terre(int x, int y) {
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
                /*System.out.println("Le personnage " + p.getClass().getSimpleName() + " est positionne sur la case " +
                this.getClass().getSimpleName() + " de coordonnee : x = " + p.getPositionX() + ", y = " + p.getPositionY()); */
            }
        }	
    }
    
    @Override
    public int caseEnInt() {
        return 6;
    }

} 