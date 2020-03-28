package lescases;

import entitesvivantes.Personnage;

public class Diamant extends Case {

    private boolean enMouvement;

    public Diamant() {
        setEstSoumisALaGravite(true);
    }

    public Diamant(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setEstSoumisALaGravite(true);
    }

    @Override
    public void mettrePersoSurCase(Personnage p) {

        if(!this.estOccupee()) {
            if(p.getCasesTraversables().contains(this.getClass().getSimpleName())) {
                p.setPositionX(this.getPositionX());
                p.setPositionY(this.getPositionY());
                this.setEstIci(p);
                System.out.println("le personnage " + p.getClass().getSimpleName() + " peut être sur cette case qui est un diamant");
            }
            else
                System.out.println("le personnage " + p.getClass().getSimpleName() + " ne peut pas être sur cette case qui est un diamant");

        }
        else
            System.out.println("cette case est déjà occupé par " + this.getEstIci().getClass().getSimpleName());
    }

    public boolean isEnMouvement() {
        return enMouvement;
    }

    public void setEnMouvement(boolean enMouvement) {
        this.enMouvement = enMouvement;
    }
    
}