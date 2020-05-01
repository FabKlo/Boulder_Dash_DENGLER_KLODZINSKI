package lescases;

import entitesvivantes.Personnage;

public class Sortie extends Case {

    private boolean porteOuverte = false;

    public Sortie() {
        setEstSoumisALaGravite(false);
    }

    public Sortie(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setEstSoumisALaGravite(false);
    }

    @Override
    public void mettrePersoSurCase(Personnage p) {
        if(!porteOuverte) {
            System.out.println("Cette case est de type" + 
            getClass().getSimpleName() +", le personnage "  + 
            p.getClass().getSimpleName() + " ne peut pas etre ici !");
        }
        else {
            if(!this.estOccupee()) {
                if(p.getCasesTraversables().contains(this.getClass().getSimpleName())) {
                    p.setPositionX(this.getPositionX());
                    p.setPositionY(this.getPositionY());
                    this.setEstIci(p);
                    /*System.out.println("Le personnage " + p.getClass().getSimpleName() + " est positionne sur la case " +
                    this.getClass().getSimpleName() + " de coordonnee : x = " + p.getPositionX() + ", y = " + p.getPositionY());*/
                }
            }
        }
        
    }

    @Override
    public int caseEnInt() {
        if(!this.porteOuverte)
            return 2;
        else
            return 7;
    }

    public boolean isPorteOuverte() {
        return porteOuverte;
    }

    public void setPorteOuverte(boolean porteOuverte) {
        this.porteOuverte = porteOuverte;
    }
    
}