package lescases;

import entitesvivantes.Personnage;

public class Diamant extends Case {

    private boolean enMouvement;

    public Diamant() {
        setEstSoumisALaGravite(true);
        setEnMouvement(false);
    }

    public Diamant(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setEstSoumisALaGravite(true);
        setEnMouvement(false);
    }

    @Override
    public void mettrePersoSurCase(Personnage p) {

        if(!this.estOccupee()) {
            if(p.getCasesTraversables().contains(this.getClass().getSimpleName())) {
                p.setPositionX(this.getPositionX());
                p.setPositionY(this.getPositionY());
                this.setEstIci(p);
                System.out.println("le personnage " + p.getClass().getSimpleName() + " peut etre sur cette case qui est un diamant");
            }
            else
                System.out.println("le personnage " + p.getClass().getSimpleName() + " ne peut pas etre sur cette case qui est un diamant");

        }
        else
            System.out.println("cette case est deja occupe par " + this.getEstIci().getClass().getSimpleName());
    }

    public boolean isEnMouvement() {
        return enMouvement;
    }

    public void setEnMouvement(boolean enMouvement) {
        this.enMouvement = enMouvement;
    }

    @Override
    public int caseEnInt() {
        return 3;
    }
    
}